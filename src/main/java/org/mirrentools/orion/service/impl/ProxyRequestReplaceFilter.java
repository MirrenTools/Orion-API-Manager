package org.mirrentools.orion.service.impl;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.entity.MyServletRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

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