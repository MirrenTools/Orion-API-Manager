package org.mirrentools.orion.controller;

import java.util.Map;

import org.mirrentools.orion.entity.ProjectApi;
import org.mirrentools.orion.entity.ProjectApiGroup;
import org.mirrentools.orion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
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
	@GetMapping(value = "/private/project/apiGroup/{projectId}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getGroupsByProjectId(@PathVariable(value = "projectId") String projectId) {
		return proService.getApiGroupList(projectId);
	}

	/**
	 * 获取指定接口分组的数据
	 * 
	 * @param groupId
	 * @return
	 */
	@GetMapping(value = "/private/apiGroup/{groupId}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getGroupsById(@PathVariable(value = "groupId") String groupId) {
		return proService.getApiGroup(groupId);
	}

	/**
	 * 保存接口分组
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/apiGroup", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> saveApiGroup(ProjectApiGroup group) {
		return proService.saveApiGroup(group);
	}

	/**
	 * 修改接口分组
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/apiGroup", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> updateApiGroup(ProjectApiGroup group) {
		return proService.updateApiGroup(group);
	}

	/**
	 * 接口分组向上移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/apiGroup/moveUp/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> moveUpApiGroup(@PathVariable(value = "id") String id) {
		return proService.moveUpApiGroup(id);
	}

	/**
	 * 接口分组向下移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/apiGroup/moveDown/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> moveDownApiGroup(@PathVariable(value = "id") String id) {
		return proService.moveDownApiGroup(id);
	}

	/**
	 * 删除接口分组
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping(value = "/private/apiGroup/{groupId}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> daleteApiGroup(@PathVariable(value = "groupId") String groupId) {
		return proService.deleteApiGroup(groupId);
	}

	/**
	 * 获取指定接口的数据
	 * 
	 * @param groupId
	 * @return
	 */
	@GetMapping(value = "/private/api/{apiId}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getApiById(@PathVariable(value = "apiId") String apiId) {
		return proService.getApi(apiId);
	}

	/**
	 * 保存接口分组
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/api", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> saveApi(ProjectApi api) {
		return proService.saveApi(api);
	}

	/**
	 * 修改接口分组
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/api", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> updateApi(ProjectApi api) {
		return proService.updateApi(api);
	}

	/**
	 * API排序向上移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/api/moveUp/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> moveUpApi(@PathVariable(value = "id") String id) {
		return proService.moveUpApi(id);
	}

	/**
	 * API排序向下移动
	 * 
	 * @param group
	 * @return
	 */
	@PutMapping(value = "/private/api/moveDown/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> moveDownApi(@PathVariable(value = "id") String id) {
		return proService.moveDownApi(id);
	}

	@DeleteMapping(value = "/private/api/{apiId}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> daleteApi(@PathVariable(value = "apiId") String apiId) {
		return proService.deleteApi(apiId);
	}

}
