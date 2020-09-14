package org.mirrentools.orion.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.common.LoginRole;
import org.mirrentools.orion.common.LoginSession;
import org.mirrentools.orion.common.LoginSessionStore;
import org.mirrentools.orion.common.ResultCode;
import org.mirrentools.orion.common.ResultUtil;
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
		if (session == null || session.getUid() == null || session.getRole() == null) {
			response.addHeader("Content-Type", "application/json;charset=UTF-8");
			try {

				response.getWriter().write(ResultUtil.formatAsString(ResultCode.R401));
			} catch (Exception e) {
				response.sendError(401, ResultCode.R401.msg());
			}
			return false;
		} else {
			String uid = session.getUid();
			String path = request.getServletPath();
			LoginRole role = session.getRole();
			if (role == LoginRole.CLIENT && path.startsWith("/private/server")) {
				response.addHeader("Content-Type", "application/json;charset=UTF-8");
				try {
					response.getWriter().write(ResultUtil.formatAsString(ResultCode.R403));
				} catch (Exception e) {
					response.sendError(403, ResultCode.R403.msg());
				}
				return false;
			}
			LOG.info("\n请求记录-->User: " + uid + ", Role: " + role + ", Method: " + request.getMethod() + ", Path: " + path);
		}
		return true;
	}

}
