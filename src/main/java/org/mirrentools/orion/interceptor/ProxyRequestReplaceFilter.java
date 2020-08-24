package org.mirrentools.orion.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.entity.MyServletRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 复制请求流的过滤器
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@WebFilter(urlPatterns = { "/proxy/server" })
public class ProxyRequestReplaceFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!(request instanceof MyServletRequestWrapper)) {
			request = new MyServletRequestWrapper(request);
		}
		filterChain.doFilter(request, response);
	}
}