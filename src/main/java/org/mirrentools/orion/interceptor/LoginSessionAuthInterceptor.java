package org.mirrentools.orion.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.mirrentools.orion.common.LoginSessionStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 普通认证的拦截器
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
@CrossOrigin
public class LoginSessionAuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "x-session");
		String sessionId = request.getHeader("x-session");
		String uid = LoginSessionStore.get(sessionId);
		System.out.println("请求检查-->" + sessionId + ": " + uid);
		if (uid == null) {
			response.addHeader("Content-Type", "application/json;charset=UTF-8");
			try {
				response.getWriter().write("{\"code\":401,\"msg\":\"用户未登录或登录已超时!\"}");
			} catch (Exception e) {
				response.sendError(401, "用户未登录或登录已超时!");
			}
			return false;
		}
		return true;
	}

}
