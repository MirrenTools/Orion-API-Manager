package org.mirrentools.orion.controller;

import java.util.Map;

import org.mirrentools.orion.entity.Tags;
import org.mirrentools.orion.entity.Users;
import org.mirrentools.orion.service.UsersService;
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
	public Map<String, Object> login(String id, String pwd, String index, String value) {
		Map<String, Object> result = userService.login(id, pwd, index, value);
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

	/**
	 * 获取用户列表
	 * 
	 * @param keywords 搜索关键字
	 * @param tid      标签的id
	 * @param page     获取第几页的数据
	 * @param size     每次获取多少条数据
	 * @return
	 */
	@GetMapping(value = "/private/server/find/users", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> findUsers(String keywords, String tid, Integer page, Integer size) {
		Map<String, Object> result = userService.findUsers(keywords, tid, page, size);
		return result;
	}

	/**
	 * 获取所有管理员用户
	 * 
	 * @return
	 */
	@GetMapping(value = "/private/server/find/server/users", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> findServerUsers() {
		Map<String, Object> result = userService.findServerUsers();
		return result;
	}

	/**
	 * 获取指定用户
	 * 
	 * @param uid
	 * @return
	 */
	@GetMapping(value = "/private/server/user/{uid}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getUser(@PathVariable("uid") String uid) {
		Map<String, Object> result = userService.getUser(uid);
		return result;
	}

	/**
	 * 新增用户
	 * 
	 * @param uid
	 * @return
	 */
	@PostMapping(value = "/private/server/user", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> postUser(@RequestHeader(value = "x-session", required = false) String sessionId, @RequestBody Users user) {
		Map<String, Object> result = userService.postUser(sessionId, user);
		return result;
	}

	/**
	 * 修改用户
	 * 
	 * @param uid
	 * @return
	 */
	@PutMapping(value = "/private/server/user", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> putUser(@RequestHeader(value = "x-session", required = false) String sessionId, @RequestBody Users user) {
		Map<String, Object> result = userService.putUser(sessionId, user);
		return result;
	}

	/**
	 * 修改用户
	 * 
	 * @param uid
	 * @return
	 */
	@DeleteMapping(value = "/private/server/user/{uid}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> deleteUser(@RequestHeader(value = "x-session", required = false) String sessionId, @PathVariable("uid") String uid) {
		Map<String, Object> result = userService.deleteUser(sessionId, uid);
		return result;
	}

	/**
	 * 获取所有标签
	 * 
	 * @return
	 */
	@GetMapping(value = "/private/server/find/tags", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> findTags() {
		Map<String, Object> result = userService.findTags();
		return result;
	}

	/**
	 * 获取指定标签
	 * 
	 * @param tid
	 * @return
	 */
	@GetMapping(value = "/private/server/tag/{tid}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> getTag(@PathVariable("tid") String tid) {
		Map<String, Object> result = userService.getTag(tid);
		return result;
	}

	/**
	 * 新增标签
	 * 
	 * @param tag
	 * @return
	 */
	@PostMapping(value = "/private/server/tag", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> postTag(@RequestBody Tags tag) {
		Map<String, Object> result = userService.postTag(tag);
		return result;
	}

	/**
	 * 修改标签
	 * 
	 * @param tag
	 * @return
	 */
	@PutMapping(value = "/private/server/tag", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> putTag(@RequestBody Tags tag) {
		Map<String, Object> result = userService.putTag(tag);
		return result;
	}

	/**
	 * 删除指定标签
	 * 
	 * @param tid
	 * @return
	 */
	@DeleteMapping(value = "/private/server/tag/{tid}", produces = { "application/json;charset=UTF-8" })
	public Map<String, Object> deleteTag(@PathVariable("tid") String tid) {
		Map<String, Object> result = userService.deleteTag(tid);
		return result;
	}

}
