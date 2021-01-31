package org.mirrentools.orion.service;

import java.util.Map;

/**
 * 通用服务
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface CommonService {
	/**
	 * 获取控制台配置
	 * 
	 * @param loginSession
	 *          用户的会话
	 * @return
	 */
	Map<String, Object> getConsoleConfig();
}