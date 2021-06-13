package com.aureus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@ComponentScan({"com.aureus"})
public class WebMvcInitializer extends WebMvcConfigurerAdapter
{
//	@Bean
//	public LoggerInterceptor loggerinterceptor()
//	{
//		return new LoggerInterceptor();
//	}
	
	public void addInterceptors(InterceptorRegistry registry) 
	{
		//System.out.println("in addinterceptor");
		//registry.addInterceptor(new LoggerInterceptor());
	}
	

}
