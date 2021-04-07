package org.mirrentools.orion.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
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
		Parser mdParser = Parser.builder().build();
		HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

		JSONObject result = new JSONObject();
		result.put("orionApi", OrionApiManager.VERSION);
		String projectId = "pid_" + System.currentTimeMillis();
		result.put("key", projectId);
		Info info = Optional.ofNullable(openAPI.getInfo()).orElse(new Info());
		result.put("name", info.getTitle());
		result.put("versions", info.getVersion());

		StringBuilder description = new StringBuilder();
		if (info.getDescription() != null) {
			description.append(htmlRenderer.render(mdParser.parse(info.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
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
				serverDesc.append(
						Optional.ofNullable(htmlRenderer.render(mdParser.parse(server.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""))
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
			d.put("description", htmlRenderer.render(mdParser.parse(externalDocs.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
			result.put("externalDocs", d);
		}
		Map<String, JSONObject> groups = new LinkedHashMap<>();
		for (Tag tag : openAPI.getTags()) {
			String name = tag.getName();
			JSONObject g = groups.get(name);
			if (g == null) {
				g = new JSONObject().put("name", name).put("summary", name).put("description", "").put("apis", new JSONArray());
				groups.put(name, g);
			}
			g.put("description", g.getString("description")
					+ htmlRenderer.render(mdParser.parse(tag.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
			if (tag.getExtensions() != null) {
				g.put("extensions", new JSONObject(tag.getExtensions()));
			}
			if (tag.getExternalDocs() != null) {
				ExternalDocumentation externalDocs = tag.getExternalDocs();
				JSONObject d = new JSONObject();
				d.put("url", externalDocs.getUrl());
				d.put("description",
						htmlRenderer.render(mdParser.parse(externalDocs.getDescription())).replaceAll("^(\\s|<p>)+|(\\s|<\\/p>)+$", ""));
				g.put("externalDocs", d);
			}
		}
		if (openAPI.getPaths() != null) {
			Paths paths = openAPI.getPaths();
			
		}
		JSONArray content = new JSONArray();
		for (JSONObject g : groups.values()) {
			content.put(g);
		}
		result.put("content", content);
		return result;
	}

	/**
	 * 转换OpenAPI的数据类型为orion的类型
	 * 
	 * @param schema
	 * @return
	 */
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
