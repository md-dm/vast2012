/**
 * 
 */
package com.md.dm.vi.vast.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

/**
 * Spring MongoDB configuration file
 * 
 * @author diego
 */
@Configuration
public class SpringMongoConfig {

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), "vast");
	}

	public @Bean
	Mongo mongo() throws Exception {
		return new Mongo("localhost");
	}

	public String getDatabaseName() {
		return "vast";
	}

}
