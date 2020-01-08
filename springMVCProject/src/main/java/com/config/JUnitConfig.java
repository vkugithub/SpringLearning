package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;

import com.dao.StudentDao;
import com.service.StudentServiceImpl;

@Configuration	
//@ComponentScan(basePackages = "com.service.StudentServiceImpl, com.dao.StudentDao")
@EnableJms
@EnableAspectJAutoProxy
@Import({DBConnectionConfig.class, AOPConfig.class})
public class JUnitConfig {

	@Bean 
	public StudentServiceImpl studentService(){
		return new StudentServiceImpl();
	}
	
	@Bean 
	public StudentDao studentDao(){
		return new StudentDao();
	}
	
	
}
