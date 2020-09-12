package org.mirrentools.orion.controller;

import java.util.Map;

import org.mirrentools.orion.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关的控制器
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
@CrossOrigin(allowedHeaders = { "x-session" }, methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.HEAD,
		RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PUT, RequestMethod.TRACE, })
@RestController
public class UsersController {
	@Autowired
	private UsersService userService;

	/**
	 * 登录
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	@PostMapping(value = "/login", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> login(String id, String pwd) {
		Map<String, Object> result = userService.login(id, pwd);
		return result;
	}

	/**
	 * 退出登录
	 * 
	 * @param sessionId
	 * @return
	 */
	@PostMapping(value = "/logout", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> logout(String sessionId) {
		Map<String, Object> result = userService.logout(sessionId);
		return result;
	}

}
