package org.mirrentools.orion.service;

import java.util.Map;

/**
 * 用户相关的服务接口
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public interface UsersService {
	/**
	 * 登录
	 * 
	 * @return
	 */
	Map<String, Object> login(String id, String pwd);

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	Map<String, Object> logout(String sessionId);
	
}
