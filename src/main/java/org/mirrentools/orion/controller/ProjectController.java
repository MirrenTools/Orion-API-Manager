package org.mirrentools.orion.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.entity.Project;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目,代理,下载等的控制器
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@CrossOrigin(allowedHeaders = { "x-url", "x-type", "x-header", "x-session", "content-type" }, methods = {
		RequestMethod.DELETE, RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.TRACE, })
@RestController
public class ProjectController {
	@Autowired
	private ProjectService proService;
	@Autowired
	private HttpApiProxy apiProxy;

	@RequestMapping(value = { "/", "/index.html" }, produces = { "text/html;charset=UTF-8" })
	public void index(HttpServletResponse response) {
		try {
			response.sendRedirect("/console/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 代理执行
	 * 
	 * @return
	 */
//	@PostMapping(value = "/proxy", produces = { "application/json;charset=UTF-8" })
//	public Map<String, Object> proxy(@RequestBody RequestData data) {
//		Map<String, Object> result = apiProxy.executeProxy(data);
//		return result;
//	}

	/**
	 * 代理执行
	 * 
	 * @return
	 */
	@RequestMapping(value = "/proxy/server")
	public void proxy(@RequestHeader(value = "x-session", required = false) String sessionId, HttpServletRequest request,
			HttpServletResponse response) {
		LoginSession session = LoginSessionStore.get(sessionId);
		apiProxy.executeProxy(session, request, response);
	}

	/**
	 * 代理获取获取所有项目
	 * 
	 * @return
	 */
	@GetMapping(value = "/proxy/project", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> proxyGetProject(
			@RequestHeader(value = "x-session", required = false) String sessionId,
			String token, String url) {
		LoginSession session = LoginSessionStore.get(sessionId);
		if (session == null) {
			session = LoginSessionStore.get(token);
		}
		Map<String, Object> result = apiProxy.getProxy(session, url);
		return result;
	}

	/**
	 * 获取所有项目
	 * 
	 * @return
	 */
	@GetMapping(value = { "/private/project", "/project" }, produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getProjectList(@RequestHeader(value = "x-session", required = false) String sessionId) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.getProjectList(session);
	}

	/**
	 * 获取指定项目
	 * 
	 * @return
	 */
	@GetMapping(value = "/private/project/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getProject(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.getProject(session, id);
	}

	/**
	 * 添加一个项目
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/server/project/fromJson", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> postProjectfromJson(@RequestHeader(value = "x-session", required = false) String sessionId,
			String project) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.saveProjectfromJson(session, project);
	}

	/**
	 * 添加一个项目
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/server/project", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> postProject(@RequestHeader(value = "x-session", required = false) String sessionId,
			@RequestBody Project project) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.saveProject(session, project);
	}

	/**
	 * 修改一个项目
	 * 
	 * @return
	 */
	@PutMapping(value = "/private/server/project", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> updateProject(@RequestHeader(value = "x-session", required = false) String sessionId,
			@RequestBody Project project) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.updateProject(session, project);
	}

	/**
	 * 项目排序上移
	 * 
	 * @return
	 */
	@PutMapping(value = "/private/server/project/moveUp/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> projectMoveUp(@PathVariable(value = "id") String id) {
		return proService.projectMoveUp(id);
	}

	/**
	 * 项目排序下移
	 * 
	 * @return
	 */
	@PutMapping(value = "/private/server/project/moveDown/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> projectMoveDown(@PathVariable(value = "id") String id) {
		return proService.projectMoveDown(id);
	}

	/**
	 * 复制项目
	 * 
	 * @return
	 */
	@PostMapping(value = "/private/server/project/copy/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> copyProject(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.copyProject(session, id);
	}

	/**
	 * 删除项目
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/private/server/project/{id}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> deleteProject(@RequestHeader(value = "x-session", required = false) String sessionId,
			@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		return proService.deleteProject(session, id);
	}

	/**
	 * 下载项目Json文件
	 * 
	 * @param response
	 * @param id
	 */
	@GetMapping(value = "/private/download/{id}")
	public void downProject(HttpServletResponse response,
			@RequestHeader(value = "x-session", required = false) String sessionId, String token,
			@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		if (session == null) {
			session = LoginSessionStore.get(token);
		}
		proService.downJson(response, session, id);

	}

	/**
	 * 获取项目Json文件
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/private/json/{id}", produces = { "application/json;charset=UTF-8" })
	public String getProjectJson(@RequestHeader(value = "x-session", required = false) String sessionId, String token,
			@PathVariable(value = "id") String id) {
		LoginSession session = LoginSessionStore.get(sessionId);
		if (session == null) {
			session = LoginSessionStore.get(token);
		}
		return proService.getJson(session, id);
	}

}
