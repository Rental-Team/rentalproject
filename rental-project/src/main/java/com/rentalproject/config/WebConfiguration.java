package com.rentalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rentalproject.interceptor.AdminInterceptor;
import com.rentalproject.interceptor.FreeBoardInterceptor;
import com.rentalproject.interceptor.ItemInterceptor;
import com.rentalproject.interceptor.ZzimInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.rentalproject.controller" })
@ComponentScan(basePackages = { "com.rentalproject.utils" })
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 관리자 인터셉터
		registry.addInterceptor(new AdminInterceptor()) // 사용할 인터셉터를 등록합니다.
                .addPathPatterns("/admin/**"); // 적용할 URL 패턴을 설정합니다.
        
        // 찜 인터셉터
        registry.addInterceptor(new ZzimInterceptor())
        		.addPathPatterns("/zzim/**") // 적용할 URL 패턴을 설정합니다.
        		.excludePathPatterns("/zzim/add");
        
        // 자유 게시판 인터셉터
        registry.addInterceptor(new FreeBoardInterceptor())
        		.addPathPatterns("/freeboard/**")
        		.excludePathPatterns("/freeboard/freeboardlist", "/freeboard/freeboarddetail", "/freeboard/download");
        
        // 아이템 인터셉터
        registry.addInterceptor(new ItemInterceptor())
				.addPathPatterns("/item/**")
				.excludePathPatterns("/item/list", "/item/search", "/item/detail");
    }
	
	
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	 
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxInMemorySize(2*1024*1024);
		multipartResolver.setMaxUploadSize(10*1024*1024);
		
		return multipartResolver;
	}
	
	



}
