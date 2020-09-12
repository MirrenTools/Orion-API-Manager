package org.mirrentools.orion.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.common.LoginRole;
import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 普通认证的拦截器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class LoginSessionAuthInterceptor implements HandlerInterceptor {
	private Logger LOG = LoggerFactory.getLogger(LoginSessionAuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "x-session,content-type");
		String sessionId = request.getHeader("x-session");
		if (sessionId == null) {
			sessionId = request.getParameter("x-session");
		}
		LoginSession session = LoginSessionStore.get(sessionId);
		if (session == null || session.getUid() == null) {
			response.addHeader("Content-Type", "application/json;charset=UTF-8");
			try {
				response.getWriter().write("{\"code\":401,\"msg\":\"用户未登录或登录已超时!\"}");
			} catch (Exception e) {
				response.sendError(401, "用户未登录或登录已超时!");
			}
			return false;
		} else {
			String uid = session.getUid();
			LoginRole role = session.getRole();
			LOG.info("\n请求记录-->User: " + uid + ", Role: " + role + ", Method: " + request.getMethod() + ", Path: "
					+ request.getServletPath());
		}
		return true;
	}

}
