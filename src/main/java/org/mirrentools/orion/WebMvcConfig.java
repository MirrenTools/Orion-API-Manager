package org.mirrentools.orion;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mirrentools.orion.interceptor.ClientAllowsInterceptor;
import org.mirrentools.orion.interceptor.LoginSessionAuthInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * WebMvc的文件配置
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	/** 配置文件是否允许未登录的用户访问客户端 */
	@Value("${clientAllowUnauthorized}")
	private String clientAllowUnauthorized;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/console/**")
				.addResourceLocations("file:" + System.getProperty("user.dir") + "/Server-UI/")
				.setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
		registry.addResourceHandler("/client/**")
				.addResourceLocations("file:" + System.getProperty("user.dir") + "/Client-UI/")
				.setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:favicon.ico");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration privateServers = registry.addInterceptor(new LoginSessionAuthInterceptor());
		privateServers.addPathPatterns("/private/**");
		InterceptorRegistration client = registry.addInterceptor(new ClientAllowsInterceptor("true".equalsIgnoreCase(clientAllowUnauthorized)));
		client.addPathPatterns("/client/index.html");
		// 添加404页面跳转
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				if (response.getStatus() == 404) {
					response.sendRedirect("/console/index.html#/404");
					return false;
				}
				return super.preHandle(request, response, handler);
			}
		});
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
