package com.sample.camel.config;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.camel.dtos.SampleProcessor;

@Configuration
public class Config {

	
	
	
	@Bean
	public SampleProcessor getSampleProcessor() {
		return new SampleProcessor();
	}
}
