package org.mirrentools.orion.controller;

import java.util.Map;

import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.entity.ProjectApi;
import org.mirrentools.orion.entity.ProjectApiGroup;
import org.mirrentools.orion.entity.ProjectApiTemplate;
import org.mirrentools.orion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * API分组与API管理的控制器
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@CrossOrigin(allowedHeaders = {"x-session", "content-type"}, methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.OPTIONS,
		RequestMethod.POST, RequestMethod.PUT,})
@RestController
public class ApiGroupController {
	/** 项目服务接口 */
	@Autowired
	private ProjectService proService;

	/**
	 * 获取指定项目的所有接口分组
	 * 
	 * @return
	 */
	@GetMapping(value = "/private/server/project/apiGroup/{projectId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getGroupsByProjectId(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "projectId") String projectId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.getApiGroupList(session, projectId);
	}

	/**
	 * 获取指定接口分组的数据
	 * 
	 * @param groupId
	 * @return
	 */
	@GetMapping(value = "/private/server/apiGroup/{groupId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getGroupsById(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "groupId") String groupId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.getApiGroup(session, groupId);
	}

	/**
	 * 保存接口分组
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/server/apiGroup", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> saveApiGroup(@RequestHeader(value = "x-session", required = false) String sessionId,
			@RequestBody ProjectApiGroup group) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.saveApiGroup(session, group);
	}

	/**
	 * 修改接口分组
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/apiGroup", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> updateApiGroup(@RequestHeader(value = "x-session", required = false) String sessionId,
			@RequestBody ProjectApiGroup group) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.updateApiGroup(session, group);
	}

	/**
	 * 接口分组向上移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/apiGroup/moveUp/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> moveUpApiGroup(@PathVariable(value = "id") String id) {
		return proService.moveUpApiGroup(id);
	}

	/**
	 * 接口分组向下移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/apiGroup/moveDown/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> moveDownApiGroup(@PathVariable(value = "id") String id) {
		return proService.moveDownApiGroup(id);
	}

	/**
	 * 删除接口分组
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping(value = "/private/server/apiGroup/{groupId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> daleteApiGroup(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "groupId") String groupId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.deleteApiGroup(session, groupId);
	}

	/**
	 * 获取分组的所有接口数据
	 * 
	 * @param groupId
	 * @return
	 */
	@GetMapping(value = "/private/server/apis/{groupId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> findApis(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "groupId") String groupId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.findApis(session, groupId);
	}

	/**
	 * 获取指定接口的数据
	 * 
	 * @param groupId
	 * @return
	 */
	@GetMapping(value = "/private/server/api/{apiId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getApiById(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "apiId") String apiId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.getApi(session, apiId);
	}

	/**
	 * 保存接口
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/server/api", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> saveApi(@RequestHeader(value = "x-session", required = false) String sessionId, @RequestBody ProjectApi api) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.saveApi(session, api);
	}
	/**
	 * 复制接口
	 * 
	 * @param groupId
	 * @return
	 */
	@PostMapping(value = "/private/server/api/{apiId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> copyApi(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "apiId") String apiId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.copyApi(session, apiId);
	}
	/**
	 * 修改接口
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/api", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> updateApi(@RequestHeader(value = "x-session", required = false) String sessionId,
			@RequestBody ProjectApi api) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.updateApi(session, api);
	}

	/**
	 * API排序向上移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/api/moveUp/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> moveUpApi(@PathVariable(value = "id") String id) {
		return proService.moveUpApi(id);
	}

	/**
	 * API排序向下移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/api/moveDown/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> moveDownApi(@PathVariable(value = "id") String id) {
		return proService.moveDownApi(id);
	}

	/**
	 * 获取隐藏的接口数据
	 * 
	 * @param groupId
	 * @return
	 */
	@GetMapping(value = "/private/server/hide/apis", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> findHideApis(@RequestHeader(value = "x-session", required = false) String sessionId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.findHideApis(session);
	}
	
	/**
	 * 隐藏(回收)API接口
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/api/hide/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> hideApi(@RequestHeader(value = "x-session", required = false) String sessionId,@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.hideApi(session,id);
	}

	/**
	 * 显示(还原)API接口
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/server/api/show/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> showApi(@RequestHeader(value = "x-session", required = false) String sessionId,@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.showApi(session,id);
	}

	@DeleteMapping(value = "/private/server/api/{apiId}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> daleteApi(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "apiId") String apiId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.deleteApi(session, apiId);
	}

	@GetMapping(value = "/private/server/api/template", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> findApiTemplateList(@RequestHeader(value = "x-session", required = false) String sessionId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.findApiTemplateList(session);
	}

	@GetMapping(value = "/private/server/api/template/{tid}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getApiTemplate(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "tid") String tid) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.getApiTemplate(session, tid);
	}

	@PostMapping(value = "/private/server/api/template", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> daleteApiTemplate(@RequestHeader(value = "x-session", required = false) String sessionId,
			@RequestBody ProjectApiTemplate template) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.postApiTemplate(session, template);
	}

	@DeleteMapping(value = "/private/server/api/template/{tid}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> daleteApiTemplate(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "tid") String tid) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.deleteApiTemplate(session, tid);
	}

}
