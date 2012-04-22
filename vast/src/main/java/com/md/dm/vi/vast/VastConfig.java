/**
 * 
 */
package com.md.dm.vi.vast;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

/**
 * @author diego
 * 
 */
@Configuration
public class VastConfig {

	@Bean
	public Mongo mongo() throws UnknownHostException {
		return new Mongo("localhost");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongo(), "vast");
	}

}