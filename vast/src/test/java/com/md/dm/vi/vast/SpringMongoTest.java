/**
 * 
 */
package com.md.dm.vi.vast;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.md.dm.vi.vast.data.Meta;
import com.mongodb.Mongo;

/**
 * @author diego
 * 
 */
public class SpringMongoTest {

	private MongoOperations mongoOps;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mongoOps = new MongoTemplate(new Mongo(), "vast");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		mongoOps.dropCollection("meta");
	}

	@Test
	public void testSaveInstance() {
		mongoOps.insert(Meta
				.build("172.8.238.33,server,file server,region-1,branch3,40.031174,-141.19032"));

		Meta meta = mongoOps.findOne(
				new Query(Criteria.where("ipAddr").is("172.8.238.33")),
				Meta.class);

		Assert.assertEquals(meta.getIpAddr(), "172.8.238.33");
	}

	@Test
	public void testFindByBoundingBox() throws Exception {
		mongoOps.insert(Meta
				.build("172.8.238.33,server,file server,region-1,branch3,40.031174,-141.19032"));

		// lower-left then upper-right
		Box box = new Box(new Point(-73.99756, 40.73083), new Point(-73.988135,
				40.741404));
		List<Meta> metas = mongoOps.find(new Query(Criteria.where("location")
				.within(box)), Meta.class);
		Assert.assertFalse(metas.isEmpty());
	}

}
