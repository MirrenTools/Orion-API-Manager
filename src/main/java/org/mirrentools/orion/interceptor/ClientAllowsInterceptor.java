package org.mirrentools.orion.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.common.ResultCode;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 是否允许访问客户端拦截器
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 *
 */
public class ClientAllowsInterceptor implements HandlerInterceptor {
	private static final Logger LOG = LogManager.getLogger(ClientAllowsInterceptor.class);
	/** 是否允许未登录的用户访问客户端 */
	private boolean clientAllowUnauthorized;

	/**
	 * 初始化客户端拦截器
	 * 
	 * @param clientAllowUnauthorized 是否允许未登录的用户访问
	 */
	public ClientAllowsInterceptor(boolean clientAllowUnauthorized) {
		super();
		this.clientAllowUnauthorized = clientAllowUnauthorized;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!clientAllowUnauthorized) {
			String token = request.getParameter("token");
			LoginSession session = LoginSessionStore.get(token);
			if (session == null) {
				String sessionId = request.getHeader("x-session");
				session = LoginSessionStore.get(sessionId);
			}
			if (session == null || session.getUid() == null || session.getRole() == null) {
				LOG.info("\n未登录用户请求客户端-->检查不通过!");
				JSONObject result = new JSONObject();
				result.put("code", ResultCode.R403.code());
				result.put("msg", ResultCode.R403.msg());
				result.put("explain", ResultCode.R403.explain());
				try {
					response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
					response.getOutputStream().write(result.toString().getBytes("utf-8"));
					response.flushBuffer();
				} catch (IOException e1) {
				}
				return false;
			}
		}
		return true;
	}

}
