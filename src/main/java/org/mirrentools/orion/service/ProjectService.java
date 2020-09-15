package org.mirrentools.orion.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.entity.Project;
import org.mirrentools.orion.entity.ProjectApi;
import org.mirrentools.orion.entity.ProjectApiGroup;

/**
 * 项目的服务接口
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface ProjectService {

	/**
	 * 获取项目列表
	 * 
	 * @return
	 */
	Map<String, Object> getProjectList();

	/**
	 * 获取项目
	 * 
	 * @param id 项目的id
	 * @return
	 */
	Map<String, Object> getProject(String id);

	/**
	 * 保存项目
	 * 
	 * @param project
	 * @return
	 */
	Map<String, Object> saveProject(LoginSession session, Project project);

	/**
	 * 保存一个项目,这个项目可能包含分组与API
	 * 
	 * @param json
	 * @return
	 */
	Map<String, Object> saveProjectfromJson(String json);

	/**
	 * 保存一个项目,并推送处理进度给前端WebSocket
	 * 
	 * @param json
	 * @param session
	 * @return
	 */
	void saveProjectfromJsonWebSocket(String json, Session session);

	/**
	 * 复制一份项目
	 * 
	 * @param key
	 * @return
	 */
	Map<String, Object> copyProject(String key);

	/**
	 * 更新项目
	 * 
	 * @param project
	 * @return
	 */
	Map<String, Object> updateProject(Project project);

	/**
	 * 项目排序上移
	 * 
	 * @param project
	 * @return
	 */
	Map<String, Object> projectMoveUp(String key);

	/**
	 * 项目排序下移
	 * 
	 * @param project
	 * @return
	 */
	Map<String, Object> projectMoveDown(String key);

	/**
	 * 更新项目
	 * 
	 * @param key
	 * @return
	 */
	Map<String, Object> deleteProject(String key);

	/**
	 * 获取指定Project的接口分组
	 * 
	 * @param projectId
	 * @return
	 */
	Map<String, Object> getApiGroupList(String projectId);

	/**
	 * 获取指定的接口分组
	 * 
	 * @param projectId
	 * @return
	 */
	Map<String, Object> getApiGroup(String groupId);

	/**
	 * 获取指定Project的接口分组
	 * 
	 * @param group
	 * @return
	 */
	Map<String, Object> saveApiGroup(ProjectApiGroup group);

	/**
	 * 修改指定Project的接口分组
	 * 
	 * @param group
	 * @return
	 */
	Map<String, Object> updateApiGroup(ProjectApiGroup group);

	/**
	 * 接口分组上移动
	 * 
	 * @param group
	 * @return
	 */
	Map<String, Object> moveUpApiGroup(String id);

	/**
	 * 接口分组下移动
	 * 
	 * @param group
	 * @return
	 */
	Map<String, Object> moveDownApiGroup(String id);

	/**
	 * 删除指定Project的接口分组
	 * 
	 * @param groupId
	 * @return
	 */
	Map<String, Object> deleteApiGroup(String groupId);

	/**
	 * 新增接口
	 * 
	 * @param api
	 * @return
	 */
	Map<String, Object> saveApi(ProjectApi api);

	/**
	 * 通过id获取接口
	 * 
	 * @param groupId
	 * @return
	 */
	Map<String, Object> findApis(String groupId);

	/**
	 * 通过id获取接口
	 * 
	 * @param apiId
	 * @return
	 */
	Map<String, Object> getApi(String apiId);

	/**
	 * 更新接口
	 * 
	 * @param api
	 * @return
	 */
	Map<String, Object> updateApi(ProjectApi api);

	/**
	 * API排序上移动
	 * 
	 * @param group
	 * @return
	 */
	Map<String, Object> moveUpApi(String id);

	/**
	 * API排序下移动
	 * 
	 * @param group
	 * @return
	 */
	Map<String, Object> moveDownApi(String id);

	/**
	 * 通过API删除指定接口
	 * 
	 * @param apiId
	 * @return
	 */
	Map<String, Object> deleteApi(String apiId);

	/**
	 * 获得的JSON字符串
	 * 
	 * @param projectId
	 * @return
	 */
	String getJson(String projectId);

	/**
	 * 下载JSON文件
	 * 
	 * @param response
	 * @param projectId 项目的id
	 */
	void downJson(HttpServletResponse response, String projectId);

}
