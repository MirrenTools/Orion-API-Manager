package org.mirrentools.orion.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

import io.swagger.parser.OpenAPIParser;
import io.swagger.util.Json;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

/**
 * 将Swagger2与OpenAPI转换为OrionAM的数据格式
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public class OpenApiConverter {
	static final String STRING = "string";
	static final Parser MARKDOWN_PARSER;
	static final HtmlRenderer HTML_RENDERER;
	static {
		MARKDOWN_PARSER = Parser.builder().build();
		HTML_RENDERER = HtmlRenderer.builder().build();
	}

	/**
	 * 将Swagger2与OpenAPI转换为OrionAM的数据格式
	 * 
	 * @param docs
	 * @return
	 * @throws Exception
	 */
	public static JSONObject convert(String docs) throws Exception {
		SwaggerParseResult spr = new OpenAPIParser().readContents(docs, null, null);
		OpenAPI openAPI = spr.getOpenAPI();
		if (openAPI == null) {
			throw new Exception(Optional.ofNullable(spr.getMessages()).orElse(new ArrayList<>()).toString());
		}

		JSONObject result = new JSONObject();
		result.put("orionApi", OrionApiManager.VERSION);
		String projectId = "pid_" + System.currentTimeMillis();
		result.put("key", projectId);
		Info info = Optional.ofNullable(openAPI.getInfo()).orElse(new Info());
		result.put("name", info.getTitle());
		result.put("versions", info.getVersion());

		StringBuilder description = new StringBuilder();
		if (info.getDescription() != null) {
			description.append(HTML_RENDERER.render(MARKDOWN_PARSER.parse(info.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
		}
		if (info.getTermsOfService() != null) {
			description.append("\n");
			description.append(String.format("<div><a href=\"%s\" target=\"_blank\">Terms of service</a></div>", info.getTermsOfService()));
		}
		License license = info.getLicense();
		if (license != null) {
			description.append("\n");
			description.append(String.format("<div><a href=\"%s\" target=\"_blank\">License %s</a></div>",
					Optional.ofNullable(license.getUrl()).orElse("#"), Optional.ofNullable(license.getName()).orElse("")));
		}
		result.put("description", description);

		if (info.getContact() != null) {
			Contact contact = info.getContact();
			if (contact.getName() != null) {
				result.put("contactName", contact.getName());
			}
			StringBuilder contactInfo = new StringBuilder();
			if (contact.getEmail() != null) {
				contactInfo.append(String.format("Email: <a href=\"mailto:%s\">%s</a> ", contact.getEmail(), contact.getEmail()));
				contactInfo.append("　");
			}
			if (contact.getUrl() != null) {
				contactInfo.append(String.format("URL: <a href=\"%s\" target=\"_blank\">%s</a>", contact.getUrl(), contact.getUrl()));
			}
			result.put("contactInfo", contactInfo.toString());
		}
		if (openAPI.getExtensions() != null) {
			result.put("extensions", new JSONObject(openAPI.getExtensions()));
		}
		JSONArray servers = new JSONArray();
		if (openAPI.getServers() != null) {
			for (Server server : openAPI.getServers()) {
				String url = server.getUrl();
				StringBuilder serverDesc = new StringBuilder();
				serverDesc.append(Optional
						.ofNullable(HTML_RENDERER.render(MARKDOWN_PARSER.parse(server.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""))
						.orElse(""));
				if (url.contains("{") && url.contains("}")) {
					ServerVariables variables = server.getVariables() == null ? new ServerVariables() : server.getVariables();
					Matcher matcher = Pattern.compile("(\\{)(\\w*)(\\})").matcher(url);
					while (matcher.find()) {
						String param = matcher.group();
						ServerVariable variable = variables.get(matcher.group(2));
						if (variable == null || variable.getDefault() == null || variable.getDefault().isEmpty()) {
							continue;
						}
						String def = variable.getDefault();
						url = url.replace(param, def);
						if (variable.getEnum() != null) {
							serverDesc.append(String.format("<br>%s default: %s,enums: ", param, def));
							for (String e : variable.getEnum()) {
								if (e == null || e.isEmpty() || Objects.equals(def, e)) {
									continue;
								}
								serverDesc.append(" " + e);
							}
						}
					}
					JSONObject value = new JSONObject();
					value.put("url", url);
					value.put("description", serverDesc.toString());
					servers.put(value);
				} else {
					JSONObject value = new JSONObject();
					value.put("url", url);
					value.put("description", serverDesc.toString());
					servers.put(value);
				}
			}
		}
		result.put("servers", servers);
		if (openAPI.getExternalDocs() != null) {
			ExternalDocumentation externalDocs = openAPI.getExternalDocs();
			JSONObject d = new JSONObject();
			d.put("url", externalDocs.getUrl());
			d.put("description",
					HTML_RENDERER.render(MARKDOWN_PARSER.parse(externalDocs.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
			result.put("externalDocs", d);
		}

		Map<String, JSONObject> groups = new LinkedHashMap<>();
		for (Tag tag : openAPI.getTags()) {
			String name = tag.getName();
			JSONObject g = groups.get(name);
			if (g == null) {
				g = new JSONObject().put("groupId", "gid_" + System.currentTimeMillis()).put("projectId", projectId).put("name", name)
						.put("summary", name).put("description", "").put("apis", new JSONArray());
				groups.put(name, g);
			}
			g.put("description", g.getString("description")
					+ HTML_RENDERER.render(MARKDOWN_PARSER.parse(tag.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
			if (tag.getExtensions() != null) {
				g.put("extensions", new JSONObject(tag.getExtensions()));
			}
			if (tag.getExternalDocs() != null) {
				ExternalDocumentation externalDocs = tag.getExternalDocs();
				JSONObject d = new JSONObject();
				d.put("url", externalDocs.getUrl());
				d.put("description",
						HTML_RENDERER.render(MARKDOWN_PARSER.parse(externalDocs.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
				g.put("externalDocs", d);
			}
		}
		if (openAPI.getPaths() != null) {
			Paths paths = openAPI.getPaths();
			paths.forEach((path, v) -> {
				try {
					if (v.getGet() != null) {
						addApiToGroups("get", path, v.getGet(), groups, openAPI);
					}
					if (v.getPut() != null) {
						addApiToGroups("put", path, v.getPut(), groups, openAPI);
					}
					if (v.getHead() != null) {
						addApiToGroups("head", path, v.getHead(), groups, openAPI);
					}
					if (v.getPost() != null) {
						addApiToGroups("post", path, v.getPost(), groups, openAPI);
					}
					if (v.getDelete() != null) {
						addApiToGroups("delete", path, v.getDelete(), groups, openAPI);
					}
					if (v.getPatch() != null) {
						addApiToGroups("patch", path, v.getPatch(), groups, openAPI);
					}
					if (v.getOptions() != null) {
						addApiToGroups("options", path, v.getOptions(), groups, openAPI);
					}
					if (v.getTrace() != null) {
						addApiToGroups("trace", path, v.getTrace(), groups, openAPI);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		JSONArray content = new JSONArray();
		for (JSONObject g : groups.values()) {
			content.put(g);
		}
		result.put("content", content);
		return result;
	}

	/**
	 * 将API添加到分组中
	 * 
	 * @param path
	 * @param item
	 * @param groups
	 */
	@SuppressWarnings("rawtypes")
	private static void addApiToGroups(String method, String path, Operation item, Map<String, JSONObject> groups, OpenAPI openAPI) {
		Components components = openAPI.getComponents();
		Map<String, Schema> schemaMap = new HashMap<>();
		Map<String, Parameter> parametersMap = new HashMap<>();
		Map<String, String> refs = new HashMap<>();
		if (components == null) {
			components = new Components();
		}

		List<String> tags = item.getTags();
		JSONObject group;
		if (tags == null || tags.size() < 1) {
			group = groups.get("Unnamed");
		} else {
			group = groups.get(tags.get(0));
		}
		if (group == null) {
			group = new JSONObject();
			group.put("groupId", "Unnamed");
			group.put("name", "Unnamed");
			group.put("summary", "This group has not been named");
			group.put("apis", new JSONArray());
			groups.put("Unnamed", group);
		}
		JSONObject api = new JSONObject();
		api.put("apiId", item.getOperationId());
		api.put("groupId", group.get("groupId"));
		api.put("title", Optional.ofNullable(item.getSummary()).orElse(path));
		api.put("method", method);
		api.put("path", path);
		api.put("description", HTML_RENDERER.render(MARKDOWN_PARSER.parse(item.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
		api.put("deprecated", item.getDeprecated());
		JSONArray parameters = new JSONArray();
		Set<String> consumes = new LinkedHashSet<>();
		if (item.getRequestBody() != null) {
			String body = getRequestBody(item, components, schemaMap, refs, consumes);
			if (body == null) {
				api.put("body", JSONObject.NULL);
			} else {
				api.put("body", body);
			}
		}
		if (item.getParameters() != null) {
			loadParameters(item.getParameters(), parameters, components, parametersMap, schemaMap);
		}

		Set<String> produces = new LinkedHashSet<>();
		JSONArray responses = new JSONArray();
		if (item.getResponses() != null) {
			ApiResponses apiResponses = item.getResponses();
			loadResponse(apiResponses, responses, produces, components, schemaMap);
		}
		api.put("consumes", new JSONArray(consumes));
		api.put("parameters", parameters);
		api.put("produces", new JSONArray(produces));
		api.put("responses", responses);
		if (item.getExtensions() != null) {
			api.put("extensions", new JSONObject(item.getExtensions()));
		}

		if (item.getExternalDocs() != null) {
			ExternalDocumentation externalDocs = item.getExternalDocs();
			JSONObject d = new JSONObject();
			d.put("url", externalDocs.getUrl());
			d.put("description",
					HTML_RENDERER.render(MARKDOWN_PARSER.parse(externalDocs.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
			api.put("externalDocs", d);
		}
		group.getJSONArray("apis").put(api);
	}

	/**
	 * 加载请求的body
	 * 
	 * @param api
	 *          OpenAPI的API
	 * @param components
	 *          OpenAPI的components
	 * @param schemaMap
	 *          OpenAPI已解析的schema
	 * @param consumes
	 *          需要添加到Orion的请求类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String getRequestBody(Operation api, Components components, Map<String, Schema> schemaMap, Map<String, String> refs,
			Set<String> consumes) {
		RequestBody body = api.getRequestBody();
		if (body != null && body.get$ref() != null) {
			String ref = body.get$ref();
			String refname = ref.substring(ref.lastIndexOf("/") + 1);
			Map<String, RequestBody> bodies = components.getRequestBodies();
			if (bodies != null && bodies.get(refname) != null) {
				body = bodies.get(refname);
			}
		}
		if (body == null || body.getContent() == null || body.getContent().isEmpty()) {
			return null;
		}

		consumes.addAll(body.getContent().keySet());
		String next = body.getContent().keySet().iterator().next();
		MediaType mediaType = body.getContent().get(next);
		if (mediaType != null && mediaType.getSchema() != null) {
			return getFullSchema(mediaType.getSchema(), components, schemaMap, refs);
			// if (mediaType.getSchema().get$ref() != null) {
			// StringBuilder result = new StringBuilder();
			// String schemaTxt = getFullSchema(mediaType.getSchema(), components,
			// schemaMap, refs);
			// return schemaTxt;
			// Schema schema = getSchema(mediaType.getSchema().get$ref(), components,
			// schemaMap);
			// if (schema != null) {
			// boolean arrays = false;
			// if (schema instanceof ArraySchema) {
			// arrays = true;
			// ArraySchema s = (ArraySchema) schema;
			// if (s.getItems() != null && s.getItems().get$ref() != null) {
			// schema = getSchema(s.getItems().get$ref(), components, schemaMap);
			// }
			// }
			// if (arrays) {
			// result.append("[");
			// }
			// if (schema.getProperties() != null) {
			// Map<String, Schema> properties = schema.getProperties();
			// JSONObject params = new JSONObject();
			// properties.forEach((k, v) -> {
			// System.out.println("K: "+k);
			// String type = Optional.ofNullable(getDataType(v)).orElse(STRING);
			// if (v instanceof ArraySchema) {
			// ArraySchema array = (ArraySchema) v;
			// if (array.getItems() != null && array.getItems().get$ref() != null) {
			// Schema vr = getSchema(array.getItems().get$ref(), components,
			// schemaMap);
			// if (vr != null) {
			// if (vr.getProperties() != null) {
			// JSONObject items = new JSONObject();
			// Map<String, Schema> vrp = vr.getProperties();
			// vrp.forEach((vrk, vrv) -> {
			// String dataType = getDataType(vrv);
			// if (STRING.equals(dataType)) {
			// params.put(vrk, "\"" + vrk + "\"");
			// } else {
			// params.put(vrk, vrk);
			// }
			// });
			// params.put(k, "[" + items.toString()
			// .replace(":\"{", ":{").replace(":\"\\\"{", ":\"{")
			// .replace("}\",", "},").replace("}\\\"\",", "}\",")
			// .replace("}\"}", "}}").replace("}\\\"\"}", "}\"}")
			// + "]");
			// } else {
			// params.put(k, "[" + k + "]");
			// }
			// } else {
			// params.put(k, "[" + k + "]");
			// }
			// } else {
			// params.put(k, "[" + k + "]");
			// }
			// } else {
			// if (STRING.equals(type)) {
			// params.put(k, "\"{" + k + "}\"");
			// } else {
			// params.put(k, "{" + k + "}");
			// }
			// }
			// });
			// System.exit(0);
			// if (params.length() > 0) {
			// result.append(params.toString()
			// .replace(":\"{", ":{").replace(":\"\\\"{", ":\"{")
			// .replace("}\",", "},").replace("}\\\"\",", "}\",")
			// .replace("}\"}", "}}").replace("}\\\"\"}", "}\"}")
			// );
			// }
			// }
			// if (arrays) {
			// result.append("]");
			// }
			// result.append("\n/*****************Schema delete before
			// request*********************/\n");
			// result.append(schemaTxt);
			// }
			// return result.toString();
			// } else {
			// return getFullSchema(mediaType.getSchema(), components, schemaMap,
			// refs);
			// }
		}
		return null;
	}

	/**
	 * 将scheme转换为请求参数
	 * 
	 * @param schema
	 * @param components
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void loadParameters(List<Parameter> parameters, JSONArray items, Components components,
			Map<String, Parameter> parametersMap, Map<String, Schema> schemaMap) {
		for (Parameter p : parameters) {
			JSONObject param = new JSONObject();
			if (p.get$ref() != null) {
				p = getParameter(p.get$ref(), components, parametersMap);
			}
			Schema schema = p.getSchema();
			if (schema != null && schema.get$ref() != null) {
				Schema s = getSchema(schema.get$ref(), components, schemaMap);
				if (s != null) {
					schema = s;
				}
			}
			param.put("in", Optional.ofNullable(p.getIn()).orElse("query"));
			param.put("name", p.getName());
			param.put("required", p.getRequired());
			StringBuilder description = new StringBuilder();
			String type = Optional.ofNullable(getDataType(schema)).orElse(STRING);
			param.put("type", type);
			if ("array".equals(type) && schema instanceof ArraySchema) {
				ArraySchema s = (ArraySchema) schema;
				if (s.getItems() != null) {
					if (s.getItems().getType() != null) {
						description.append("format:" + s.getItems().getType() + "\n");
					}
				}
			}
			if (p.getDescription() != null) {
				description.append(HTML_RENDERER.render(MARKDOWN_PARSER.parse(p.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
			}
			param.put("description", description.toString());
			if (schema != null) {
				if (schema.getDefault() != null) {
					param.put("def", schema.getDefault());
				}
				if (schema.getEnum() != null) {
					JSONArray enums = new JSONArray();
					schema.getEnum().forEach(e -> {
						enums.put(e);
					});
					param.put("enums", enums);
				}
				if (schema.getMaxLength() != null) {
					param.put("maxLength", schema.getMaxLength());
				}
				if (schema.getMinLength() != null) {
					param.put("minLength", schema.getMinLength());
				}
				if (schema.getMaximum() != null) {
					param.put("maximum", schema.getMaximum());
				}
				if (schema.getMinimum() != null) {
					param.put("minimum", schema.getMinimum());
				}
				if (schema.getPattern() != null) {
					param.put("pattern", schema.getPattern());
				}
			}
			items.put(param);
		}
	}

	/**
	 * 转换response
	 * 
	 * @param resp
	 * @param responses
	 * @param produces
	 * @param components
	 * @param schemaMap
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private static void loadResponse(ApiResponses resp, JSONArray responses, Set<String> produces, Components components,
			Map<String, Schema> schemaMap) {
		if (resp != null) {
			resp.forEach((k, v) -> {
				JSONObject res = new JSONObject();
				res.put("status", k);
				if (v.getDescription() != null) {
					res.put("msg", HTML_RENDERER.render(MARKDOWN_PARSER.parse(v.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
				} else {
					res.put("msg", "");
				}
				JSONArray data = new JSONArray();
				if (v.getHeaders() != null) {
					v.getHeaders().forEach((hk, hv) -> {
						JSONObject h = new JSONObject();
						h.put("in", "header");
						h.put("name", hk);
						if (hv.getDescription() != null) {
							h.put("description",
									HTML_RENDERER.render(MARKDOWN_PARSER.parse(hv.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
						} else {
							h.put("description", "");
						}
						h.put("type", getDataType(hv.getSchema()));
						data.put(h);
					});
				}
				if (v.getContent() != null && !v.getContent().isEmpty()) {
					produces.addAll(v.getContent().keySet());
					String next = v.getContent().keySet().iterator().next();
					MediaType type = v.getContent().get(next);
					Schema schema = type.getSchema();
					if (schema instanceof ArraySchema) {
						ArraySchema arrs = (ArraySchema) schema;
						if (arrs.getItems() != null && arrs.getItems().get$ref() != null) {
							schema = getSchema(arrs.getItems().get$ref(), components, schemaMap);
						}
					} else {
						if (schema != null && schema.get$ref() != null) {
							schema = getSchema(schema.get$ref(), components, schemaMap);
						}
					}
					if (schema != null) {
						String dataType = Optional.ofNullable(getDataType(schema)).orElse(STRING);
						if ("object".equalsIgnoreCase(dataType)) {
							JSONArray items = new JSONArray();
							if (schema.getProperties() != null) {
								Map<String, Schema> properties = schema.getProperties();
								properties.forEach((pk, pv) -> {
									JSONObject item = new JSONObject();
									item.put("name", pk);
									Schema pvs = pv;
									if (pvs instanceof ArraySchema) {
										ArraySchema arrs = (ArraySchema) pvs;
										if (arrs.getItems() != null && arrs.getItems().get$ref() != null) {
											Schema s = getSchema(arrs.getItems().get$ref(), components, schemaMap);
											if (s != null) {
												pvs = s;
											}
										}
									} else if (pvs.get$ref() != null) {
										Schema s = getSchema(pvs.get$ref(), components, schemaMap);
										if (s != null) {
											pvs = s;
										}
									}

									String itemType = Optional.ofNullable(getDataType(pvs)).orElse(STRING);
									item.put("type", itemType);
									if (pvs.getDescription() != null) {
										item.put("description",
												HTML_RENDERER.render(MARKDOWN_PARSER.parse(pvs.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
									}
									if (pvs.getProperties() != null) {
										Map<String, Schema> cps = pvs.getProperties();
										JSONArray citems = new JSONArray();
										cps.forEach((cpk, cpv) -> {
											JSONObject citem = new JSONObject();
											citem.put("name", cpk);
											Schema cpvs = cpv;
											if (cpvs.get$ref() != null) {
												Schema s = getSchema(cpvs.get$ref(), components, schemaMap);
												if (s != null) {
													cpvs = s;
												}
											}
											String citemType = Optional.ofNullable(getDataType(cpvs)).orElse(STRING);
											citem.put("type", citemType);
											if (cpvs.getDescription() != null) {
												citem.put("description", HTML_RENDERER.render(MARKDOWN_PARSER.parse(cpvs.getDescription()))
														.replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
											}
											citems.put(citem);
										});
										item.put("items", citems);
									}
									items.put(item);
								});
							}
							JSONObject r = new JSONObject();
							r.put("in", "body");
							r.put("name", schema.getName());
							r.put("type", dataType);
							if (schema.getDescription() != null) {
								r.put("description",
										HTML_RENDERER.render(MARKDOWN_PARSER.parse(schema.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
							}
							r.put("items", items);
							data.put(r);

						} else {
							JSONObject r = new JSONObject();
							r.put("in", "body");
							r.put("name", schema.getName());
							r.put("type", dataType);
							if (schema.getDescription() != null) {
								r.put("description",
										HTML_RENDERER.render(MARKDOWN_PARSER.parse(schema.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
							}
							data.put(r);
						}
					}
				}
				res.put("data", data);
				responses.put(res);
			});
		}
	}

	/**
	 * 获取请求参数
	 * 
	 * @param key
	 * @param components
	 * @param schemaMap
	 * @return
	 */
	private static Parameter getParameter(String key, Components components, Map<String, Parameter> schemaMap) {
		Parameter schema = schemaMap.get(key);
		if (schema == null) {
			if (components.getSchemas() == null) {
				return null;
			}
			String refname = key.substring(key.lastIndexOf("/") + 1);
			schema = components.getParameters().get(refname);
			schemaMap.put(key, schema);
			return schema;
		}
		return schema;

	}

	/**
	 * 
	 * 获取所有schema 字符串,使用正则匹配$ref并将其替换
	 * 
	 * @param schema
	 *          要转换的对象
	 * @param components
	 *          OpenAPI的对象
	 * @param schemaMap
	 *          已经转换好的schema
	 * @param refs
	 *          已经转换好的schema字符串
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String getFullSchema(Schema schema, Components components, Map<String, Schema> schemaMap, Map<String, String> refs) {
		if (schema == null) {
			return null;
		}
		if (refs.get(schema.get$ref()) != null) {
			return refs.get(schema.get$ref());
		}
		String pretty = Json.pretty(schema);
		Matcher matcher = Pattern.compile("(\"\\$ref\" : \")(#/.*)\"+").matcher(pretty);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			String key = matcher.group(2);
			String ref = refs.get(key);
			if (ref == null) {
				Schema s = getSchema(key, components, schemaMap);
				ref = getFullSchema(s, components, schemaMap, refs);
				refs.put(key, ref);
			}
			if (ref != null) {
				String replace = group.replace(key, ref);
				matcher.appendReplacement(buffer, Matcher.quoteReplacement(replace));
			}
		}
		matcher.appendTail(buffer);
		String result = buffer.toString();
		if (refs.get(schema.get$ref()) != null) {
			refs.put(schema.get$ref(), result);
		}
		return result;
	}

	/**
	 * 获取schema对象
	 * 
	 * @param key
	 * @param components
	 * @param schemaMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Schema getSchema(String key, Components components, Map<String, Schema> schemaMap) {
		Schema schema = schemaMap.get(key);
		if (schema == null) {
			if (components.getSchemas() == null) {
				return null;
			}
			String refname = key.substring(key.lastIndexOf("/") + 1);
			schema = components.getSchemas().get(refname);
			schemaMap.put(key, schema);
			return schema;
		}
		return schema;
	}

	/**
	 * 转换OpenAPI的数据类型为orion的类型
	 * 
	 * @param schema
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String getDataType(Schema schema) {
		if (schema == null || schema.getType() == null) {
			return "";
		}
		if (Objects.equals("integer", schema.getType()) && Objects.equals("int64", schema.getFormat())) {
			return "long";
		} else if (Objects.equals("integer", schema.getType())) {
			return "int";
		} else if (Objects.equals("number", schema.getType()) && Objects.equals("float", schema.getFormat())) {
			return "float";
		} else if (Objects.equals("number", schema.getType()) && Objects.equals("double", schema.getFormat())) {
			return "double";
		}
		return schema.getType();
	}

}