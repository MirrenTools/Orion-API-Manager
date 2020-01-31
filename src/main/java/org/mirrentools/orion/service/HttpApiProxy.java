package org.mirrentools.orion.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.entity.RequestData;

/**
 * 请求别人API的代理服务器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public interface HttpApiProxy {
	/**
	 * get请求的代理
	 * 
	 * @param url
	 * @return
	 */
	Map<String, Object> getProxy(String url);

	/**
	 * 代理执行请求,API方式
	 * 
	 * @param request
	 * @return
	 */
	Map<String, Object> executeProxy(RequestData request);

	/**
	 * 代理执行,代理方式
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	void executeProxy(HttpServletRequest request, HttpServletResponse response);

}
