/**
 * 
 */
package com.md.dm.vi.vast.web;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mongodb.Mongo;

/**
 * @author diego
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.md.dm.vi.vast")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("**").addResourceLocations("/");
	}

	@Bean
	public Mongo mongo() throws UnknownHostException {
		return new Mongo("localhost:27022");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongo(), "vast");
	}

}