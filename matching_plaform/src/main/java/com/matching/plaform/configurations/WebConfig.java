package com.matching.plaform.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.matching.plaform.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/writeForm").setViewName("views/writeForm");
		registry.addViewController("/writeBoard").setViewName("views/writeForm");
		registry.addViewController("/loginView").setViewName("members/loginView");
		registry.addViewController("/joinView").setViewName("members/JoinView");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new LoginCheckInterceptor())
	.addPathPatterns("/detailView", "/write*", "/update*");

	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	registry.addResourceHandler("/resources/files/**")
	.addResourceLocations("file:./src/main/resources/static/files/")
	.setCachePeriod(1);
	}

}
