package org.mirrentools.orion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.common.OrionApiManager;
import org.mirrentools.orion.entity.Project;
import org.mirrentools.orion.entity.RequestData;
import org.mirrentools.orion.service.HttpApiProxy;
import org.mirrentools.orion.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目的控制器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
@CrossOrigin(allowedHeaders = {"x-url", "x-type", "x-header", "x-session","content-type"}, methods = {RequestMethod.DELETE, RequestMethod.GET,
		RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.TRACE,})
@RestController
public class ProjectController {

	/** 项目服务接口 */
	@Autowired
	private ProjectService proService;
	@Autowired
	private HttpApiProxy apiProxy;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/", "/index.html"}, produces = {"text/html;charset=UTF-8"})
	public String index(HttpServletResponse response) {
		response.addHeader("title", OrionApiManager.NAME);
		return "<h1 style='text-align: center;'>欢迎使用" + OrionApiManager.MIN_NAME + "</h1>" + "	<h1 style='text-align: center;'>"
				+ OrionApiManager.NAME_VERSION + "</h1>"
				+ "	<h2 style='text-align: center;'><a href='/Server-UI/index.html'>服务端UI</a> <a style='margin-left:10px' href='/Client-UI/index.html'>客户端UI</a></h2>";
	}

	@PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> login(String id, String pwd) {
		Map<String, Object> result = proService.login(id, pwd);
		return result;
	}

	@PostMapping(value = "/logout", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> logout(String sessionId) {
		Map<String, Object> result = proService.logout(sessionId);
		return result;
	}

	/**
	 * 代理执行
	 * 
	 * @return
	 */
	@PostMapping(value = "/proxy", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> proxy(@RequestBody RequestData data) {
		Map<String, Object> result = apiProxy.executeProxy(data);
		return result;
	}

	/**
	 * 代理执行
	 * 
	 * @return
	 */
	@RequestMapping(value = "/proxy/server")
	public void proxy(HttpServletRequest request, HttpServletResponse response) {
		apiProxy.executeProxy(request, response);
	}

	/**
	 * 代理获取获取所有项目
	 * 
	 * @return
	 */
	@GetMapping(value = "/proxy/project", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> proxyGetProject(String url) {
		Map<String, Object> result = apiProxy.getProxy(url);
		return result;
	}

	/**
	 * 获取所有项目
	 * 
	 * @return
	 */
	@GetMapping(value = {"/private/project", "/project"}, produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getProjectList() {
		return proService.getProjectList();
	}

	/**
	 * 获取指定项目
	 * 
	 * @return
	 */
	@GetMapping(value = "/private/project/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> getProject(@PathVariable(value = "id") String id) {
		return proService.getProject(id);
	}

	/**
	 * 添加一个项目
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/project/fromJson", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> postProjectfromJson(String project) {
		return proService.saveProjectfromJson(project);
	}

	/**
	 * 添加一个项目
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/project", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> postProject(Project project) {
		return proService.saveProject(project);
	}

	/**
	 * 修改一个项目
	 * 
	 * @return
	 */
	@PutMapping(value = "/private/project", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> updateProject(Project project) {
		return proService.updateProject(project);
	}
	/**
	 * 项目排序上移
	 * 
	 * @return
	 */
	@PutMapping(value = "/private/project/moveUp/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> projectMoveUp(@PathVariable(value = "id") String id) {
		return proService.projectMoveUp(id);
	}
	/**
	 * 项目排序下移
	 * 
	 * @return
	 */
	@PutMapping(value = "/private/project/moveDown/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> projectMoveDown(@PathVariable(value = "id") String id) {
		return proService.projectMoveDown(id);
	}

	/**
	 * 复制项目
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/project/copy/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> copyProject(@PathVariable(value = "id") String id) {
		return proService.copyProject(id);
	}

	/**
	 * 删除项目
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/private/project/{id}", produces = {"application/json;charset=UTF-8"})
	public Map<String, Object> postProject(@PathVariable(value = "id") String id) {
		return proService.deleteProject(id);
	}

	/**
	 * 下载项目Json文件
	 * 
	 * @param response
	 * @param id
	 */
	@GetMapping(value = "/project/downJson/{id}")
	public void downProject(HttpServletResponse response, @PathVariable(value = "id") String id) {
		proService.downJson(response, id);
	}

	/**
	 * 获取项目Json文件
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/project/getJson/{id}", produces = {"application/json;charset=UTF-8"})
	public String getProjectJson(@PathVariable(value = "id") String id) {
		return proService.getJson(id);
	}

}
