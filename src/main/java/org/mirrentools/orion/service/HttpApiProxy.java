package org.mirrentools.orion.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.common.LoginSession;

/**
 * 请求别人API的代理服务器
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
public interface HttpApiProxy {
	/**
	 * get请求的代理
	 * 
	 * @param loginSession 用户登录信息
	 * @param url
	 * @return
	 */
	Map<String, Object> getProxy(LoginSession loginSession, String url);

	/**
	 * 代理执行请求,API方式
	 * 
	 * @param request
	 * @return
	 */
//	Map<String, Object> executeProxy(RequestData request);

	/**
	 * 代理执行,代理方式
	 * 
	 * @param loginSession 用户登录信息
	 * @param request
	 * @param response
	 * @return
	 */
	void executeProxy(LoginSession loginSession, HttpServletRequest request, HttpServletResponse response);

}
