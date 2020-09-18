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
	 * @param loginSession 用户的会话
	 * @return
	 */
	Map<String, Object> getProjectList(LoginSession loginSession);

	/**
	 * 获取项目
	 * 
	 * @param loginSession 用户的会话
	 * @param id           项目的id
	 * @return
	 */
	Map<String, Object> getProject(LoginSession loginSession, String id);

	/**
	 * 保存项目
	 * 
	 * @param loginSession 用户的会话
	 * @param project      项目
	 * @return
	 */
	Map<String, Object> saveProject(LoginSession loginSession, Project project);

	/**
	 * 保存一个项目,这个项目可能包含分组与API
	 * 
	 * @param loginSession 用户的会话
	 * @param json         项目的json
	 * @return
	 */
	Map<String, Object> saveProjectfromJson(LoginSession loginSession, String json);

	/**
	 * 保存一个项目,并推送处理进度给前端WebSocket
	 * 
	 * @param loginSession 用户的会话
	 * @param json         项目
	 * @param session      websocket的会话
	 */
	void saveProjectfromJsonWebSocket(LoginSession loginSession, String json, Session session);

	/**
	 * 复制一份项目
	 * 
	 * 
	 * @param loginSession 用户的会话
	 * @param key          项目的id
	 * @return
	 */
	Map<String, Object> copyProject(LoginSession loginSession, String key);

	/**
	 * 更新项目
	 * 
	 * @param loginSession 用户的会话
	 * @param project      项目的信息
	 * @return
	 */
	Map<String, Object> updateProject(LoginSession loginSession, Project project);

	/**
	 * 项目排序上移
	 * 
	 * @param key 项目的id
	 * @return
	 */
	Map<String, Object> projectMoveUp(String key);

	/**
	 * 项目排序下移
	 * 
	 * @param key 项目的id
	 * @return
	 */
	Map<String, Object> projectMoveDown(String key);

	/**
	 * 删除项目
	 * 
	 * @param loginSession 用户的会话
	 * @param key          项目的id
	 * @return
	 */
	Map<String, Object> deleteProject(LoginSession loginSession, String key);

	/**
	 * 获取指定Project的接口分组
	 * 
	 * @param projectId
	 * @return
	 */
	Map<String, Object> getApiGroupList(LoginSession loginSession, String projectId);

	/**
	 * 获取指定的接口分组
	 * 
	 * 
	 * @param groupId 分组的id
	 * @return
	 */
	Map<String, Object> getApiGroup(LoginSession loginSession, String groupId);

	/**
	 * 获取指定Project的接口分组
	 * 
	 * @param loginSession 用户的会话
	 * @param group        分组的信息
	 * @return
	 */
	Map<String, Object> saveApiGroup(LoginSession loginSession, ProjectApiGroup group);

	/**
	 * 修改指定Project的接口分组
	 * 
	 * @param loginSession 用户的会话
	 * @param group        分组的信息
	 */
	Map<String, Object> updateApiGroup(LoginSession loginSession, ProjectApiGroup group);

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
	 * @param loginSession 用户的会话
	 * @param group        分组的id
	 * @return
	 */
	Map<String, Object> deleteApiGroup(LoginSession loginSession, String groupId);

	/**
	 * 新增接口
	 * 
	 * 
	 * @param loginSession 用户的会话
	 * @param api          接口的信息
	 * @return
	 */
	Map<String, Object> saveApi(LoginSession loginSession, ProjectApi api);

	/**
	 * 通过id获取接口
	 * 
	 * @param groupId 分组的id
	 * @return
	 */
	Map<String, Object> findApis(LoginSession loginSession, String groupId);

	/**
	 * 通过id获取接口
	 * 
	 * @param apiId 接口的id
	 * @return
	 */
	Map<String, Object> getApi(LoginSession loginSession, String apiId);

	/**
	 * 更新接口
	 * 
	 * @param loginSession 用户的会话
	 * @param api          接口的信息
	 * @return
	 */
	Map<String, Object> updateApi(LoginSession loginSession, ProjectApi api);

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
	 * 删除指定接口
	 * 
	 * @param loginSession 用户的会话
	 * @param apiId        接口的id
	 * @return
	 */
	Map<String, Object> deleteApi(LoginSession loginSession, String apiId);

	/**
	 * 获得的JSON字符串
	 * 
	 * @param projectId
	 * @return
	 */
	String getJson(LoginSession loginSession,String projectId);

	/**
	 * 下载JSON文件
	 * 
	 * @param response
	 * @param projectId 项目的id
	 */
	void downJson(HttpServletResponse response,LoginSession loginSession, String projectId);

}
