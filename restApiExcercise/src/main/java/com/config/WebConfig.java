package com.config;

import interceptors.TimeBasedAccessInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


	@Configuration	
	@ComponentScan(basePackages = {"com.*"})
	@EnableWebMvc
	@EnableJms
	@Import({DBConnectionConfig.class, AOPConfig.class})
	public class WebConfig extends WebMvcConfigurerAdapter {
	     
//	  @Autowired
//	  private Environment env;
	  
		@Bean
	    public ViewResolver getViewResolver(){
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/views/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	    }
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeBasedAccessInterceptor());
		}

}
