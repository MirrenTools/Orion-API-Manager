package org.mirrentools.orion.service.impl;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mirrentools.orion.common.ConfigUtil;
import org.mirrentools.orion.common.OrionApiManager;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.common.MD5Util;
import org.mirrentools.orion.common.ResultUtil;
import org.mirrentools.orion.common.StringUtil;
import org.mirrentools.orion.entity.Project;
import org.mirrentools.orion.entity.ProjectApi;
import org.mirrentools.orion.entity.ProjectApiGroup;
import org.mirrentools.orion.entity.ProjectInfo;
import org.mirrentools.orion.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class DefaultProjectServiceImpl implements ProjectService {

	@Override
	public Map<String, Object> login(String id, String pwd) {
		if (StringUtil.isNullOrEmpty(id, pwd)) {
			return ResultUtil.failed("登录失败,账号与密码不能为空!", 0);
		}
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/config/user.json"));
			JSONObject users = new JSONObject(new String(bytes));
			if (users.has(id)) {
				JSONObject user = users.getJSONObject(id);
				if (user.has("pwd") && user.get("pwd").equals(pwd)) {
					String sessionId = MD5Util.encode(UUID.randomUUID().toString(), 3);
					LoginSessionStore.save(sessionId, id);
					Map<String, String> result = new HashMap<>();
					result.put("uid", id);
					result.put("sessionId", sessionId);
					return ResultUtil.succeed(result);
				} else {
					return ResultUtil.failed("登录失败,账号或密码错误!", 0);
				}
			} else {
				return ResultUtil.failed("登录失败,账号或密码错误!", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage(), 0);
		}
	}

	@Override
	public Map<String, Object> logout(String sessionId) {
		LoginSessionStore.remove(sessionId);
		return ResultUtil.succeed(1);
	}

	@Override
	public Map<String, Object> getProjectList() {
		try {
			List<ProjectInfo> list = ConfigUtil.getProjectList();
			return ResultUtil.succeed(list);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getProject(String id) {
		try {
			Project project = ConfigUtil.getProject(id);
			return ResultUtil.succeed(project);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}

	}

	@Override
	public Map<String, Object> saveProject(Project project) {
		try {
			if (StringUtil.isNullOrEmpty(project.getServers(), project.getName())) {
				return ResultUtil.failed("存在空值,name与host都为必填");
			}
			if (project.getSorts() == null) {
				project.setSorts(0);
			}
			ConfigUtil.saveProject(project);
			return ResultUtil.succeed(1);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> saveProjectfromJson(String json) {
		try {
			if (StringUtil.isNullOrEmpty(json)) {
				return ResultUtil.failed("project不能为空,应为项目的json字符串!");
			}
			JSONObject data = new JSONObject(json);
			if (StringUtil.isNullOrEmpty(data.getString("name"), data.getString("servers"))) {
				return ResultUtil.failed("项目名称,服务集不能为空!");
			}
			Project project = new Project();
			project.setKey(UUID.randomUUID().toString());
			project.setName(data.getString("name"));
			project.setServers(data.getString("servers"));
			project.setSorts(data.has("sorts") ? data.getInt("sorts") : 0);
			project.setVersions(data.has("versions") ? data.getString("versions") : null);
			project.setDescription(data.has("description") ? data.getString("description") : null);
			project.setExternalDocs(data.has("externalDocs") ? data.getString("externalDocs") : null);
			project.setContactName(data.has("contactName") ? data.getString("contactName") : null);
			project.setContactInfo(data.has("contactInfo") ? data.getString("contactInfo") : null);
			project.setLastTime(System.currentTimeMillis());
			int saveProject = ConfigUtil.saveProject(project);
			if (saveProject > 0) {
				if (data.has("content")) {
					JSONArray array = data.getJSONArray("content");
					for (int i = 0; i < array.length(); i++) {
						JSONObject gdata = array.getJSONObject(i);
						ProjectApiGroup group = new ProjectApiGroup();
						group.setProjectId(project.getKey());
						group.setGroupId(UUID.randomUUID().toString());
						group.setSorts(gdata.has("sort") ? gdata.getInt("sort") : 0);
						group.setName(gdata.has("name") ? gdata.getString("name") : "group");
						group.setSummary(gdata.has("summary") ? gdata.getString("summary") : "summary");
						group.setDescription(gdata.has("description") ? gdata.getString("description") : null);
						group.setExternalDocs(gdata.has("externalDocs") ? gdata.getString("externalDocs") : null);
						int saveGroup = ConfigUtil.saveProjectApiGroup(group);
						if (saveGroup > 0 && gdata.has("apis")) {
							JSONArray apis = gdata.getJSONArray("apis");
							for (int j = 0; j < apis.length(); j++) {
								JSONObject adata = apis.getJSONObject(j);
								ProjectApi api = new ProjectApi();
								api.setGroupId(group.getGroupId());
								api.setSorts(adata.has("sorts") ? adata.getInt("sorts") : 0);
								api.setMethod(adata.has("method") ? adata.getString("method") : "get");
								api.setTitle(adata.has("title") ? adata.getString("title") : "title");
								api.setPath(adata.has("path") ? adata.getString("path") : "path");
								api.setDescription(adata.has("description") ? adata.getString("description") : null);
								api.setConsumes(adata.has("consumes") ? adata.getString("consumes") : null);
								api.setParameters(adata.has("parameters") ? adata.getString("parameters") : null);
								api.setProduces(adata.has("produces") ? adata.getString("produces") : null);
								api.setResponses(adata.has("responses") ? adata.getString("responses") : null);
								api.setExternalDocs(adata.has("externalDocs") ? adata.getString("externalDocs") : null);
								ConfigUtil.saveProjectApi(api);
							}
						}
					}
				}
				return ResultUtil.succeed(1);
			} else {
				return ResultUtil.failed("请返回检查项目是否已经新增成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> copyProject(String key) {
		try {
			if (StringUtil.isNullOrEmpty(key)) {
				return ResultUtil.failed("存在空值,项目的id为必填");
			}
			Project project = ConfigUtil.getProject(key);
			project.setKey(UUID.randomUUID().toString());
			project.setName(project.getName() + "_副本");
			List<ProjectApiGroup> groups = ConfigUtil.getProjectApiGroups(key);
			if (groups != null && !groups.isEmpty()) {
				for (ProjectApiGroup g : groups) {
					String gid = new String(g.getGroupId());
					g.setProjectId(project.getKey());
					g.setGroupId(UUID.randomUUID().toString());
					ConfigUtil.saveProjectApiGroup(g);
					List<ProjectApi> apiList = ConfigUtil.getProjectApiList(gid);
					if (apiList != null && !apiList.isEmpty()) {
						for (ProjectApi api : apiList) {
							api.setGroupId(g.getGroupId());
							api.setApiId(UUID.randomUUID().toString());
							ConfigUtil.saveProjectApi(api);
						}
					}
				}
			}
			ConfigUtil.saveProject(project);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> updateProject(Project project) {
		try {
			if (StringUtil.isNullOrEmpty(project.getKey(), project.getServers(), project.getName())) {
				System.out.println(project);
				return ResultUtil.failed("存在空值,host,name,key都为必填");
			}
			if (project.getSorts() == null) {
				project.setSorts(0);
			}
			project.setLastTime(System.currentTimeMillis());
			ConfigUtil.updateProject(project);
			return ResultUtil.succeed(1);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> projectMoveUp(String key) {
		try {
			if (StringUtil.isNullOrEmpty(key)) {
				return ResultUtil.failed("存在空值,项目的id不能为空");
			}
			ConfigUtil.updateProjectMoveUp(key);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> projectMoveDown(String key) {
		try {
			if (StringUtil.isNullOrEmpty(key)) {
				return ResultUtil.failed("存在空值,项目的id不能为空");
			}
			ConfigUtil.updateProjectMoveDown(key);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> deleteProject(String key) {
		try {
			if (StringUtil.isNullOrEmpty(key)) {
				return ResultUtil.failed("存在空值,项目的id不能为空");
			}
			ConfigUtil.delectProject(key);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getApiGroupList(String projectId) {
		try {
			if (StringUtil.isNullOrEmpty(projectId)) {
				return ResultUtil.failed("存在空值,项目的id不能为空");
			}
			List<ProjectApiGroup> result = ConfigUtil.getProjectApiGroupList(projectId);
			return ResultUtil.succeed(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getApiGroup(String groupId) {
		try {
			if (StringUtil.isNullOrEmpty(groupId)) {
				return ResultUtil.failed("存在空值,项目的id不能为空");
			}
			ProjectApiGroup result = ConfigUtil.getProjectApiGroup(groupId);
			return ResultUtil.succeed(result);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> saveApiGroup(ProjectApiGroup group) {
		try {
			if (group.getSorts() == null) {
				group.setSorts(0);
			}
			ConfigUtil.saveProjectApiGroup(group);
			return ResultUtil.succeed(1);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}

	}

	@Override
	public Map<String, Object> updateApiGroup(ProjectApiGroup group) {
		try {
			if (StringUtil.isNullOrEmpty(group.getGroupId())) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			if (group.getSorts() == null) {
				group.setSorts(0);
			}
			ConfigUtil.updateProjectApiGroup(group);
			return ResultUtil.succeed(1);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> moveUpApiGroup(String id) {
		try {
			if (StringUtil.isNullOrEmpty(id)) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			ConfigUtil.updateProjectApiGroupMoveUp(id);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> moveDownApiGroup(String id) {
		try {
			if (StringUtil.isNullOrEmpty(id)) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			ConfigUtil.updateProjectApiGroupMoveDown(id);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> deleteApiGroup(String groupId) {
		try {
			if (StringUtil.isNullOrEmpty(groupId)) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			ConfigUtil.delectProjectApiGroup(groupId);
			return ResultUtil.succeed(1);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> saveApi(ProjectApi api) {
		try {
			if (StringUtil.isNullOrEmpty(api.getGroupId())) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			if (api.getVersion() == null) {
				api.setVersion(1L);
			}
			if (api.getSorts() == null) {
				api.setSorts(0);
			}
			ConfigUtil.saveProjectApi(api);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> getApi(String apiId) {
		try {
			if (StringUtil.isNullOrEmpty(apiId)) {
				return ResultUtil.failed("存在空值,id不能为空");
			}
			ProjectApi result = ConfigUtil.getProjectApi(apiId);
			return ResultUtil.succeed(result);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> updateApi(ProjectApi api) {
		try {
			if (api.getVersion() == null) {
				api.setVersion(1L);
			}
			ConfigUtil.updateProjectApi(api);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> moveUpApi(String id) {
		try {
			if (StringUtil.isNullOrEmpty(id)) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			ConfigUtil.updateProjectApiMoveUp(id);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> moveDownApi(String id) {
		try {
			if (StringUtil.isNullOrEmpty(id)) {
				return ResultUtil.failed("存在空值,分组的id不能为空");
			}
			ConfigUtil.updateProjectApiMoveDown(id);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public Map<String, Object> deleteApi(String apiId) {
		try {
			if (StringUtil.isNullOrEmpty(apiId)) {
				return ResultUtil.failed("存在空值,id不能为空");
			}
			ConfigUtil.deleteProjectApi(apiId);
			return ResultUtil.succeed(1);
		} catch (Throwable e) {
			e.printStackTrace();
			return ResultUtil.failed(e.getMessage());
		}
	}

	@Override
	public String getJson(String projectId) {
		try {
			Project project = ConfigUtil.getProject(projectId);
			JSONObject result = new JSONObject();
			result.put("orionApi", OrionApiManager.VERSION);
			result.put("key", project.getKey());
			result.put("name", project.getName());
			result.put("versions", project.getVersions());
			result.put("description", project.getDescription());
			try {
				result.put("servers", new JSONArray(project.getServers()));
			} catch (Exception e) {
				result.put("servers", project.getServers());
			}
			result.put("contactName", project.getContactName());
			result.put("contactInfo", project.getContactInfo());
			if (project.getLastTime() != null) {
				result.put("lastTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(project.getLastTime())));
			}
			if (project.getExternalDocs() != null) {
				try {
					result.put("externalDocs", new JSONObject(project.getExternalDocs()));
				} catch (Exception e) {
					result.put("externalDocs", project.getExternalDocs());
					e.printStackTrace();
				}
			}
			if (project.getExtensions() != null) {
				try {
					result.put("extensions", new JSONObject(project.getExtensions()));
				} catch (Exception e) {
					try {
						result.put("extensions", new JSONArray(project.getExtensions()));
					} catch (Exception e1) {
						result.put("extensions", project.getExtensions());
					}
				}
			}
			List<ProjectApiGroup> list = ConfigUtil.getProjectApiGroupList(projectId);
			if (list != null && !list.isEmpty()) {
				JSONArray content = new JSONArray();
				for (ProjectApiGroup pag : list) {
					JSONObject group = new JSONObject();
					group.put("groupId", pag.getGroupId());
					group.put("projectId", pag.getProjectId());
					group.put("name", pag.getName());
					group.put("summary", pag.getSummary());
					group.put("description", pag.getDescription());
					if (pag.getExternalDocs() != null) {
						try {
							group.put("externalDocs", new JSONObject(pag.getExternalDocs()));
						} catch (Exception e) {
							group.put("externalDocs", pag.getExternalDocs());
							e.printStackTrace();
						}
					}
					if (pag.getExtensions() != null) {
						try {
							group.put("extensions", new JSONObject(pag.getExtensions()));
						} catch (Exception e) {
							group.put("extensions", pag.getExtensions());
							e.printStackTrace();
						}
					}
					JSONArray apis = new JSONArray();
					if (pag.getApis() != null) {
						for (ProjectApi pa : pag.getApis()) {
							JSONObject api = new JSONObject();
							api.put("apiId", pa.getApiId());
							api.put("groupId", pa.getGroupId());
							api.put("method", pa.getMethod());
							api.put("path", pa.getPath());
							api.put("title", pa.getTitle());
							api.put("description", pa.getDescription());
							api.put("deprecated", "true".equals(pa.getDeprecated()) ? true : false);
							if (pa.getConsumes() != null) {
								try {
									api.put("consumes", new JSONArray(pa.getConsumes()));
								} catch (Exception e) {
									api.put("consumes", pa.getConsumes());
									e.printStackTrace();
								}
							}
							if (pa.getParameters() != null) {
								try {
									api.put("parameters", new JSONArray(pa.getParameters()));
								} catch (Exception e) {
									api.put("parameters", pa.getParameters());
									e.printStackTrace();
								}
							}
							if (pa.getProduces() != null) {
								try {
									api.put("produces", new JSONArray(pa.getProduces()));
								} catch (Exception e) {
									api.put("produces", pa.getProduces());
									e.printStackTrace();
								}
							}

							if (pa.getResponses() != null) {
								try {
									api.put("responses", new JSONArray(pa.getResponses()));
								} catch (Exception e) {
									api.put("responses", pa.getResponses());
									e.printStackTrace();
								}
							}
							if (pa.getAdditional() != null) {
								try {
									api.put("additional", new JSONArray(pa.getAdditional()));
								} catch (Exception e) {
									api.put("additional", pa.getAdditional());
									e.printStackTrace();
								}
							}
							if (pa.getExternalDocs() != null) {
								try {
									api.put("externalDocs", new JSONObject(pa.getExternalDocs()));
								} catch (Exception e) {
									api.put("externalDocs", pa.getExternalDocs());
									e.printStackTrace();
								}
							}
							if (pa.getExtensions() != null) {
								try {
									api.put("extensions", new JSONObject(pa.getExtensions()));
								} catch (Exception e) {
									try {
										api.put("extensions", new JSONArray(pa.getExtensions()));
									} catch (Exception e1) {
										api.put("extensions", pa.getExtensions());
										e1.printStackTrace();
									}
								}
							}
							apis.put(api);
						}
					}
					group.put("apis", apis);
					content.put(group);
				}
				result.put("content", content);
			}
			return result.toString(2);
		} catch (Throwable e) {
			e.printStackTrace();
			String msg = e == null ? "无法追踪错误" : e.getMessage();
			return "{\"error\":\"" + msg + "\"}";
		}
	}

	@Override
	public void downJson(HttpServletResponse response, String projectId) {
		try {
			String result = getJson(projectId);
			response.setContentType("application/force-download;charset=UTF-8");
			String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hhmmss"));
			String fileName = "Orion-API-" + time + ".json";
			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
			try (PrintWriter writer = response.getWriter()) {
				writer.write(result);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
