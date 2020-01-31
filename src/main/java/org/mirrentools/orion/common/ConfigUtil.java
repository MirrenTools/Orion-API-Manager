package org.mirrentools.orion.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.mirrentools.orion.entity.Project;
import org.mirrentools.orion.entity.ProjectApi;
import org.mirrentools.orion.entity.ProjectApiGroup;
import org.mirrentools.orion.entity.ProjectInfo;

public class ConfigUtil {
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/config/ConfigDB.db";
	/**
	 * 获得数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL);
		return conn;
	}

	/**
	 * 执行查询
	 * 
	 * @param sql
	 *          SQL语句
	 * @param handler
	 *          返回结果
	 */
	public static <R> FunctionResult<R> query(String sql, Function<ResultSet, FunctionResult<R>> handler) throws Exception {
		try (Connection conn = getConnection()) {
			try (Statement stat = conn.createStatement()) {
				try (ResultSet set = stat.executeQuery(sql)) {
					return handler.apply(set);
				}
			}
		}
	}
	/**
	 * 执行查询
	 * 
	 * @param sql
	 *          SQL语句
	 * @param params
	 *          参数 不能为null
	 * @return
	 * @throws Exception
	 */
	public static <R> FunctionResult<R> query(String sql, List<Object> params, Function<ResultSet, FunctionResult<R>> handler)
			throws Exception {
		try (Connection conn = getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(sql)) {
				for (int i = 0; i < params.size(); i++) {
					stat.setObject((i + 1), params.get(i));
				}
				try (ResultSet set = stat.executeQuery()) {
					return handler.apply(set);
				}
			}
		}
	}
	/**
	 * 执行更新操作
	 * 
	 * @param sql
	 *          SQL语句
	 * @return
	 * @throws Exception
	 */
	public static int update(String sql) throws Exception {
		try (Connection conn = getConnection()) {
			try (Statement stat = conn.createStatement()) {
				return stat.executeUpdate(sql);
			}
		}
	}
	/**
	 * 执行更新操作
	 * 
	 * @param sql
	 *          SQL语句
	 * @param params
	 *          参数,不能为null
	 * @return
	 * @throws Exception
	 */
	public static int update(String sql, List<Object> params) throws Exception {
		try (Connection conn = getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(sql)) {
				for (int i = 0; i < params.size(); i++) {
					stat.setObject((i + 1), params.get(i));
				}
				return stat.executeUpdate();
			}
		}
	}
	/**
	 * 执行更新并返回主键
	 * 
	 * @param sql
	 *          SQL语句
	 * @param params
	 *          参数,不能为null
	 * @return
	 * @throws Exception
	 */
	public static UpdateResult updateGeneratedKeys(String sql, List<Object> params) throws Exception {
		try (Connection conn = getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				for (int i = 0; i < params.size(); i++) {
					stat.setObject((i + 1), params.get(i));
				}
				int executeUpdate = stat.executeUpdate();
				try (ResultSet set = stat.getGeneratedKeys()) {
					List<Object> keys = new ArrayList<>();
					while (set.next()) {
						keys.add(set.getObject(1));
					}
					return new UpdateResult(executeUpdate, keys);
				}
			}
		}
	}

	/**
	 * 获取项目
	 * 
	 * @param id
	 *          项目的id
	 * @return
	 * @throws Throwable
	 */
	public static Project getProject(String id) throws Throwable {
		String sql = String.format("select * from project where %s = ?", ColumnsProject.KEY);
		FunctionResult<Project> execute = query(sql, StringUtil.asList(id), resultSet -> {
			Project project = new Project();
			try {
				if (resultSet.next()) {
					project.setKey(resultSet.getString(ColumnsProject.KEY));
					project.setName(resultSet.getString(ColumnsProject.NAME));
					project.setVersions(resultSet.getString(ColumnsProject.VERSIONS));
					project.setDescription(resultSet.getString(ColumnsProject.DESCRIPTION));
					project.setHost(resultSet.getString(ColumnsProject.HOST));
					project.setBasePath(resultSet.getString(ColumnsProject.BASE_PATH));
					project.setSchemes(resultSet.getString(ColumnsProject.SCHEMES));
					project.setExternalDocs(resultSet.getString(ColumnsProject.EXTERNAL_DOCS));
					project.setContactName(resultSet.getString(ColumnsProject.CONTACT_NAME));
					project.setContactInfo(resultSet.getString(ColumnsProject.CONTACT_INFO));
					project.setExtensions(resultSet.getString(ColumnsProject.EXTENSIONS));
					project.setLastTime(resultSet.getLong(ColumnsProject.LAST_TIME));
				}
				return new FunctionResult<>(project);
			} catch (SQLException e) {
				return new FunctionResult<>(e);
			}
		});

		if (execute.succeeded()) {
			return execute.result();
		} else {
			throw execute.cause();
		}

	}

	/**
	 * 获取所有项目
	 * 
	 * @param id
	 * @return
	 * @throws Throwable
	 */
	public static List<ProjectInfo> getProjectList() throws Throwable {
		String sql = String.format("select %s,%s,%s from project order by %s desc", ColumnsProject.KEY, ColumnsProject.NAME,
				ColumnsProject.VERSIONS, ColumnsProject.LAST_TIME);
		FunctionResult<List<ProjectInfo>> execute = query(sql, resultSet -> {
			try {
				List<ProjectInfo> result = new ArrayList<>();
				while (resultSet.next()) {
					ProjectInfo projectInfo = new ProjectInfo();
					projectInfo.setKey(resultSet.getString(ColumnsProject.KEY));
					projectInfo.setName(resultSet.getString(ColumnsProject.NAME));
					projectInfo.setVersion(resultSet.getString(ColumnsProject.VERSIONS));
					result.add(projectInfo);
				}
				return new FunctionResult<>(result);
			} catch (Exception e) {
				return new FunctionResult<>(e);
			}
		});

		if (execute.succeeded()) {
			return execute.result();
		} else {
			throw execute.cause();
		}
	}

	/**
	 * 保存项目
	 * 
	 * @param project
	 * @throws Exception
	 */
	public static void saveProject(Project project) throws Exception {
		project.setLastTime(System.currentTimeMillis());
		String sql = String.format("insert into project (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?,?,?,?,?,?,?)",
				ColumnsProject.KEY, ColumnsProject.NAME, ColumnsProject.VERSIONS, ColumnsProject.DESCRIPTION, ColumnsProject.HOST,
				ColumnsProject.BASE_PATH, ColumnsProject.SCHEMES, ColumnsProject.EXTERNAL_DOCS, ColumnsProject.CONTACT_NAME,
				ColumnsProject.CONTACT_INFO, ColumnsProject.EXTENSIONS, ColumnsProject.LAST_TIME);
		List<Object> params = new ArrayList<>();
		params.add(StringUtil.isNullOrEmpty(project.getKey()) ? UUID.randomUUID().toString() : project.getKey());
		params.add(project.getName());
		params.add(project.getVersions());
		params.add(project.getDescription());
		params.add(project.getHost());
		params.add(project.getBasePath());
		params.add(project.getSchemes());
		params.add(project.getExternalDocs());
		params.add(project.getContactName());
		params.add(project.getContactInfo());
		params.add(project.getExtensions());
		params.add(project.getLastTime());
		update(sql, params);
	}

	/**
	 * 更新项目
	 * 
	 * @param project
	 * @throws Exception
	 */
	public static void updateProject(Project project) throws Exception {
		project.setLastTime(System.currentTimeMillis());
		StringBuilder set = new StringBuilder("set ");
		List<Object> params = new ArrayList<>();
		if (project.getName() != null) {
			set.append(ColumnsProject.NAME + " = ? ,");
			params.add(project.getName());
		}
		if (project.getVersions() != null) {
			set.append(ColumnsProject.VERSIONS + " = ? ,");
			params.add(project.getVersions());
		}
		if (project.getDescription() != null) {
			set.append(ColumnsProject.DESCRIPTION + " = ? ,");
			params.add(project.getDescription());
		}
		if (project.getHost() != null) {
			set.append(ColumnsProject.HOST + " = ? ,");
			params.add(project.getHost());
		}
		if (project.getBasePath() != null) {
			set.append(ColumnsProject.BASE_PATH + " = ? ,");
			params.add(project.getBasePath());
		}
		if (project.getSchemes() != null) {
			set.append(ColumnsProject.SCHEMES + " = ? ,");
			params.add(project.getSchemes());
		}
		if (project.getExternalDocs() != null) {
			set.append(ColumnsProject.EXTERNAL_DOCS + " = ? ,");
			params.add(project.getExternalDocs());
		}

		if (project.getContactName() != null) {
			set.append(ColumnsProject.CONTACT_NAME + " = ? ,");
			params.add(project.getContactName());
		}
		if (project.getContactInfo() != null) {
			set.append(ColumnsProject.CONTACT_INFO + " = ? ,");
			params.add(project.getContactInfo());
		}
		if (project.getExtensions() != null) {
			set.append(ColumnsProject.EXTENSIONS + " = ? ,");
			params.add(project.getExtensions());
		}
		set.append(ColumnsProject.LAST_TIME + " = ? ");
		params.add(project.getLastTime());

		String sql = "update project " + set.toString() + " where key = ?";
		params.add(project.getKey());
		update(sql, params);

	}
	/**
	 * 删除项目
	 * 
	 * @param key
	 * @throws Throwable
	 */
	public static void delectProject(String key) throws Throwable {
		List<Object> params = new ArrayList<>();
		params.add(key);
		String groupSql = String.format("select %s from project_api_group where %s=?", ColumnsApiGroup.GROUP_ID, ColumnsApiGroup.PROJECT_ID);
		FunctionResult<List<String>> groupIds = query(groupSql, params, res -> {
			try {
				List<String> result = new ArrayList<>();
				while (res.next()) {
					result.add(res.getString(ColumnsApiGroup.GROUP_ID));
				}
				return new FunctionResult<>(result);
			} catch (SQLException e) {
				return new FunctionResult<>(e);
			}
		});
		if (groupIds.succeeded()) {
			for (String gid : groupIds.result()) {
				delectProjectApiGroup(gid);
			}
		} else {
			throw groupIds.cause();
		}

		String sql = String.format("delete from project where %s = ? ", ColumnsProject.KEY);
		update(sql, params);
	}

	/**
	 * 获取指定项目的所有分组,包括分组的接口
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public static List<ProjectApiGroup> getProjectApiGroupList(String projectId) throws Exception {
		try (Connection conn = getConnection()) {
			String sql = String.format("select * from project_api_group where %s=?", ColumnsApiGroup.PROJECT_ID);
			try (PreparedStatement stat = conn.prepareStatement(sql)) {
				stat.setString(1, projectId);
				try (ResultSet resultSet = stat.executeQuery()) {
					List<ProjectApiGroup> result = new ArrayList<>();
					while (resultSet.next()) {
						ProjectApiGroup group = new ProjectApiGroup();
						group.setGroupId(resultSet.getString(ColumnsApiGroup.GROUP_ID));
						group.setProjectId(resultSet.getString(ColumnsApiGroup.PROJECT_ID));
						group.setName(resultSet.getString(ColumnsApiGroup.NAME));
						group.setSummary(resultSet.getString(ColumnsApiGroup.SUMMARY));
						group.setDescription(resultSet.getString(ColumnsApiGroup.DESCRIPTION));
						group.setExternalDocs(resultSet.getString(ColumnsApiGroup.EXTERNAL_DOCS));
						group.setExtensions(resultSet.getString(ColumnsApiGroup.EXTENSIONS));
						String apiSql = String.format("select * from project_api where %s=?", ColumnsApiGroup.GROUP_ID);
						try (PreparedStatement statSub = conn.prepareStatement(apiSql)) {
							statSub.setString(1, group.getGroupId());
							try (ResultSet apiResult = statSub.executeQuery()) {
								List<ProjectApi> apiList = new ArrayList<>();
								while (apiResult.next()) {
									ProjectApi api = new ProjectApi();
									api.setApiId(apiResult.getString(ColumnsAPI.API_ID));
									api.setGroupId(apiResult.getString(ColumnsAPI.GROUP_ID));
									api.setMethod(apiResult.getString(ColumnsAPI.METHOD));
									api.setPath(apiResult.getString(ColumnsAPI.PATH));
									api.setTitle(apiResult.getString(ColumnsAPI.TITLE));
									api.setDescription(apiResult.getString(ColumnsAPI.DESCRIPTION));
									api.setConsumes(apiResult.getString(ColumnsAPI.CONSUMES));
									api.setParameters(apiResult.getString(ColumnsAPI.PARAMETERS));
									api.setProduces(apiResult.getString(ColumnsAPI.PRODUCES));
									api.setResponses(apiResult.getString(ColumnsAPI.RESPONSES));
									api.setDeprecated(apiResult.getString(ColumnsAPI.DEPRECATED));
									api.setAdditional(apiResult.getString(ColumnsAPI.ADDITIONAL));
									api.setExternalDocs(apiResult.getString(ColumnsAPI.EXTERNAL_DOCS));
									api.setExtensions(apiResult.getString(ColumnsAPI.EXTENSIONS));
									apiList.add(api);
								}
								group.setApis(apiList);
							}
						}
						result.add(group);
					}
					return result;
				}
			}
		}
	}

	/**
	 * 获取指定项目的所有接口分组,该方法只放回分组本身,不返回分组的接口
	 * 
	 * @param groupId
	 *          分组的id
	 * @return
	 */
	public static List<ProjectApiGroup> getProjectApiGroups(String projectId) throws Throwable {
		String sql = String.format("select * from project_api_group where %s=?", ColumnsApiGroup.PROJECT_ID);
		FunctionResult<List<ProjectApiGroup>> execute = query(sql, StringUtil.asList(projectId), res -> {
			List<ProjectApiGroup> result = new ArrayList<>();
			try {
				while (res.next()) {
					ProjectApiGroup group = new ProjectApiGroup();
					group.setGroupId(res.getString(ColumnsApiGroup.GROUP_ID));
					group.setProjectId(res.getString(ColumnsApiGroup.PROJECT_ID));
					group.setName(res.getString(ColumnsApiGroup.NAME));
					group.setSummary(res.getString(ColumnsApiGroup.SUMMARY));
					group.setDescription(res.getString(ColumnsApiGroup.DESCRIPTION));
					group.setExternalDocs(res.getString(ColumnsApiGroup.EXTERNAL_DOCS));
					group.setExtensions(res.getString(ColumnsApiGroup.EXTENSIONS));
					result.add(group);
				}
				return new FunctionResult<>(result);
			} catch (SQLException e) {
				return new FunctionResult<>(e);
			}
		});
		if (execute.succeeded()) {
			return execute.result();
		} else {
			throw execute.cause();
		}
	}
	/**
	 * 获取指定接口分组的数据
	 * 
	 * @param groupId
	 *          分组的id
	 * @return
	 */
	public static ProjectApiGroup getProjectApiGroup(String groupId) throws Throwable {
		String sql = String.format("select * from project_api_group where %s=?", ColumnsApiGroup.GROUP_ID);
		FunctionResult<ProjectApiGroup> execute = query(sql, StringUtil.asList(groupId), res -> {
			ProjectApiGroup group = new ProjectApiGroup();
			try {
				if (res.next()) {
					group.setGroupId(res.getString(ColumnsApiGroup.GROUP_ID));
					group.setProjectId(res.getString(ColumnsApiGroup.PROJECT_ID));
					group.setName(res.getString(ColumnsApiGroup.NAME));
					group.setSummary(res.getString(ColumnsApiGroup.SUMMARY));
					group.setDescription(res.getString(ColumnsApiGroup.DESCRIPTION));
					group.setExternalDocs(res.getString(ColumnsApiGroup.EXTERNAL_DOCS));
					group.setExtensions(res.getString(ColumnsApiGroup.EXTENSIONS));
				}
				return new FunctionResult<>(group);
			} catch (SQLException e) {
				return new FunctionResult<>(e);
			}
		});
		if (execute.succeeded()) {
			return execute.result();
		} else {
			throw execute.cause();
		}
	}

	/**
	 * 保存接口分组
	 * 
	 * @param project
	 * @throws Exception
	 */
	public static void saveProjectApiGroup(ProjectApiGroup group) throws Exception {
		String sql = String.format("insert into project_api_group (%s,%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?,?)", ColumnsApiGroup.GROUP_ID,
				ColumnsApiGroup.PROJECT_ID, ColumnsApiGroup.NAME, ColumnsApiGroup.SUMMARY, ColumnsApiGroup.DESCRIPTION,
				ColumnsApiGroup.EXTERNAL_DOCS, ColumnsApiGroup.EXTENSIONS);
		List<Object> params = new ArrayList<>();
		params.add(StringUtil.isNullOrEmpty(group.getGroupId()) ? UUID.randomUUID().toString() : group.getGroupId());
		params.add(group.getProjectId());
		params.add(group.getName());
		params.add(group.getSummary());
		params.add(group.getDescription());
		params.add(group.getExternalDocs());
		params.add(group.getExtensions());
		update(sql, params);
	}

	/**
	 * 删除接口分组
	 * 
	 * @param key
	 * @throws Exception
	 */
	public static void updateProjectApiGroup(ProjectApiGroup group) throws Exception {
		StringBuilder set = new StringBuilder("set ");
		List<Object> params = new ArrayList<>();
		if (group.getName() != null) {
			set.append(ColumnsApiGroup.NAME + " = ? ,");
			params.add(group.getName());
		}
		if (group.getSummary() != null) {
			set.append(ColumnsApiGroup.SUMMARY + " = ? ,");
			params.add(group.getSummary());
		}
		if (group.getDescription() != null) {
			set.append(ColumnsApiGroup.DESCRIPTION + " = ? ,");
			params.add(group.getDescription());
		}
		if (group.getExternalDocs() != null) {
			set.append(ColumnsApiGroup.EXTERNAL_DOCS + " = ? ,");
			params.add(group.getExternalDocs());
		}
		if (group.getExtensions() != null) {
			set.append(ColumnsApiGroup.EXTENSIONS + " = ? ,");
			params.add(group.getExtensions());
		}
		String sql = "update project_api_group " + set.substring(0, set.length() - 1)
				+ String.format(" where %s = ? ", ColumnsApiGroup.GROUP_ID);
		params.add(group.getGroupId());
		update(sql, params);
	}

	/**
	 * 删除接口分组
	 * 
	 * @param key
	 * @throws Exception
	 */
	public static void delectProjectApiGroup(String groupId) throws Exception {
		List<Object> params = new ArrayList<>();
		params.add(groupId);
		String apiSql = String.format("delete from project_api where %s = ? ", ColumnsAPI.GROUP_ID);
		update(apiSql, params);
		String groupSql = String.format("delete from project_api_group where %s = ? ", ColumnsApiGroup.GROUP_ID);
		update(groupSql, params);
	}
	/**
	 * 新增接口
	 * 
	 * @param api
	 * @throws Exception
	 */
	public static void saveProjectApi(ProjectApi api) throws Exception {
		String sql = String.format(
				"insert into project_api (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", ColumnsAPI.API_ID,
				ColumnsAPI.GROUP_ID, ColumnsAPI.METHOD, ColumnsAPI.PATH, ColumnsAPI.TITLE, ColumnsAPI.DESCRIPTION, ColumnsAPI.CONSUMES,
				ColumnsAPI.PARAMETERS, ColumnsAPI.PRODUCES, ColumnsAPI.RESPONSES, ColumnsAPI.DEPRECATED, ColumnsAPI.ADDITIONAL,
				ColumnsAPI.EXTERNAL_DOCS, ColumnsAPI.EXTENSIONS);
		List<Object> params = new ArrayList<>();
		params.add(StringUtil.isNullOrEmpty(api.getApiId()) ? UUID.randomUUID().toString() : api.getApiId());
		params.add(api.getGroupId());
		params.add(api.getMethod());
		params.add(api.getPath());
		params.add(api.getTitle());
		params.add(api.getDescription());
		params.add(api.getConsumes());
		params.add(api.getParameters());
		params.add(api.getProduces());
		params.add(api.getResponses());
		params.add(api.getDeprecated());
		params.add(api.getAdditional());
		params.add(api.getExternalDocs());
		params.add(api.getExtensions());
		update(sql, params);
	}

	/**
	 * 获取指定分组下的所有接口信息
	 * 
	 * @param groupsId
	 * @return
	 * @throws Throwable
	 */
	public static List<ProjectApi> getProjectApiList(String groupsId) throws Throwable {
		String sql = String.format("select * from project_api where %s=?", ColumnsAPI.GROUP_ID);
		FunctionResult<List<ProjectApi>> result = query(sql, StringUtil.asList(groupsId), res -> {
			try {
				List<ProjectApi> apis = new ArrayList<>();
				while (res.next()) {
					ProjectApi api = new ProjectApi();
					api.setApiId(res.getString(ColumnsAPI.API_ID));
					api.setGroupId(res.getString(ColumnsAPI.GROUP_ID));
					api.setMethod(res.getString(ColumnsAPI.METHOD));
					api.setPath(res.getString(ColumnsAPI.PATH));
					api.setTitle(res.getString(ColumnsAPI.TITLE));
					api.setDescription(res.getString(ColumnsAPI.DESCRIPTION));
					api.setConsumes(res.getString(ColumnsAPI.CONSUMES));
					api.setParameters(res.getString(ColumnsAPI.PARAMETERS));
					api.setProduces(res.getString(ColumnsAPI.PRODUCES));
					api.setResponses(res.getString(ColumnsAPI.RESPONSES));
					api.setDeprecated(res.getString(ColumnsAPI.DEPRECATED));
					api.setAdditional(res.getString(ColumnsAPI.ADDITIONAL));
					api.setExternalDocs(res.getString(ColumnsAPI.EXTERNAL_DOCS));
					api.setExtensions(res.getString(ColumnsAPI.EXTENSIONS));
					apis.add(api);
				}
				return new FunctionResult<>(apis);
			} catch (SQLException e) {
				return new FunctionResult<>(e);
			}
		});
		if (result.succeeded()) {
			return result.result();
		} else {
			throw result.cause();
		}
	}
	/**
	 * 获取指定接口的信息
	 * 
	 * @param apiId
	 * @return
	 * @throws Throwable
	 */
	public static ProjectApi getProjectApi(String apiId) throws Throwable {
		String sql = String.format("select * from project_api where %s=?", ColumnsAPI.API_ID);
		FunctionResult<ProjectApi> result = query(sql, StringUtil.asList(apiId), res -> {
			try {
				ProjectApi api = new ProjectApi();
				while (res.next()) {
					api.setApiId(res.getString(ColumnsAPI.API_ID));
					api.setGroupId(res.getString(ColumnsAPI.GROUP_ID));
					api.setMethod(res.getString(ColumnsAPI.METHOD));
					api.setPath(res.getString(ColumnsAPI.PATH));
					api.setTitle(res.getString(ColumnsAPI.TITLE));
					api.setDescription(res.getString(ColumnsAPI.DESCRIPTION));
					api.setConsumes(res.getString(ColumnsAPI.CONSUMES));
					api.setParameters(res.getString(ColumnsAPI.PARAMETERS));
					api.setProduces(res.getString(ColumnsAPI.PRODUCES));
					api.setResponses(res.getString(ColumnsAPI.RESPONSES));
					api.setDeprecated(res.getString(ColumnsAPI.DEPRECATED));
					api.setAdditional(res.getString(ColumnsAPI.ADDITIONAL));
					api.setExternalDocs(res.getString(ColumnsAPI.EXTERNAL_DOCS));
					api.setExtensions(res.getString(ColumnsAPI.EXTENSIONS));
				}
				return new FunctionResult<>(api);
			} catch (SQLException e) {
				return new FunctionResult<>(e);
			}
		});
		if (result.succeeded()) {
			return result.result();
		} else {
			throw result.cause();
		}
	}
	/**
	 * 更新接口
	 * 
	 * @param api
	 * @throws Exception
	 */
	public static void updateProjectApi(ProjectApi api) throws Exception {
		StringBuilder set = new StringBuilder("set ");
		List<Object> params = new ArrayList<>();
		if (api.getMethod() != null) {
			set.append(ColumnsAPI.METHOD + " = ? ,");
			params.add(api.getMethod());
		}
		if (api.getPath() != null) {
			set.append(ColumnsAPI.PATH + " = ? ,");
			params.add(api.getPath());
		}
		if (api.getTitle() != null) {
			set.append(ColumnsAPI.TITLE + " = ? ,");
			params.add(api.getTitle());
		}
		if (api.getDescription() != null) {
			set.append(ColumnsAPI.DESCRIPTION + " = ? ,");
			params.add(api.getDescription());
		}
		if (api.getConsumes() != null) {
			set.append(ColumnsAPI.CONSUMES + " = ? ,");
			params.add(api.getConsumes());
		}
		if (api.getParameters() != null) {
			set.append(ColumnsAPI.PARAMETERS + " = ? ,");
			params.add(api.getParameters());
		}
		if (api.getProduces() != null) {
			set.append(ColumnsAPI.PRODUCES + " = ? ,");
			params.add(api.getProduces());
		}
		if (api.getResponses() != null) {
			set.append(ColumnsAPI.RESPONSES + " = ? ,");
			params.add(api.getResponses());
		}
		if (api.getDeprecated() != null) {
			set.append(ColumnsAPI.DEPRECATED + " = ? ,");
			params.add(api.getDeprecated());
		}
		if (api.getAdditional() != null) {
			set.append(ColumnsAPI.ADDITIONAL + " = ? ,");
			params.add(api.getAdditional());
		}
		if (api.getExternalDocs() != null) {
			set.append(ColumnsAPI.EXTERNAL_DOCS + " = ? ,");
			params.add(api.getExternalDocs());
		}
		if (api.getExtensions() != null) {
			set.append(ColumnsAPI.EXTENSIONS + " = ? ,");
			params.add(api.getExtensions());
		}
		String sql = "update project_api " + set.substring(0, set.length() - 1) + String.format(" where %s = ? ", ColumnsAPI.API_ID);
		params.add(api.getApiId());
		update(sql, params);
	}
	/**
	 * 删除接口
	 * 
	 * @param apiId
	 * @throws Exception
	 */
	public static void deleteProjectApi(String apiId) throws Exception {
		String sql = String.format("delete from project_api where %s=?", ColumnsAPI.API_ID);
		update(sql, StringUtil.asList(apiId));
	}

}
