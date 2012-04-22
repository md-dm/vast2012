/**
 * 
 */
package com.md.dm.vi.vast.model;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Simple repository interface to manage {@link Meta} instances.
 * 
 * @author diego
 * 
 */
@Repository
public class MetaRepository {

	@Inject
	private MongoTemplate mongoTemplate;

	static final Logger logger = LoggerFactory.getLogger(MetaRepository.class);

	public Meta create(Meta todo) {
		mongoTemplate.insert(todo);
		return todo;
	}

	public Meta update(Meta todo) {
		mongoTemplate.save(todo);
		return todo;
	}

	public Meta findById(String id) {
		return mongoTemplate.findById(id, Meta.class);
	}

	public List<Meta> findAll() {
		return mongoTemplate.findAll(Meta.class);
	}

	public void remove(String id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), Meta.class);
	}

	public void removeAll() {

		if (mongoTemplate.collectionExists(Meta.class)) {
			mongoTemplate.dropCollection(Meta.class);
			mongoTemplate.createCollection(Meta.class);
		}

	}
}
