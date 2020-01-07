package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.aop.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {

    @Bean
    public LoggingAspect logging() {
        return new LoggingAspect();
    }
}
