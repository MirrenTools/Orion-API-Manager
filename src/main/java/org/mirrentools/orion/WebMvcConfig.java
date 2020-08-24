package org.mirrentools.orion;

import org.mirrentools.orion.interceptor.LoginSessionAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc的文件配置
 * 
 * @author <a href="https://mirrentools.org">Mirren</a>
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/Server-UI/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/Server-UI/");
		registry.addResourceHandler("/Client-UI/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/Client-UI/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration interceptor = registry.addInterceptor(new LoginSessionAuthInterceptor());
		interceptor.addPathPatterns("/private/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
