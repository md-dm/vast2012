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
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

import com.md.dm.vi.vast.model.Meta;
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
		mongoOps.indexOps(Meta.class).ensureIndex(
				new Index().on("ipAddr", Order.ASCENDING));
		mongoOps.indexOps(Meta.class).ensureIndex(
				new GeospatialIndex("location"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//mongoOps.dropCollection("meta");
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
		//Assert.assertFalse(metas.isEmpty());
	}
	
	/**
	 * db.meta.count({bussinesUnit:'headquarters', machineClass:'server');
	 * @throws Exception
	 */
	@Test
	public void testCountBaseUnitAndMachineClass() throws Exception {
		
	}

	/**
	 * db.meta.count(bussinesUnit:'headquarters');
	 * @throws Exception
	 */
	@Test
	public void testCountBaseUnit() throws Exception {
		
	}

	/**
	 * db.meta.distinct('bussinesUnit');
	 * @throws Exception
	 */
	@Test
	public void testDistinctBaseUnit() throws Exception {
		
	}

	/**
	 * db.meta.distinct('machineClass');
	 * @throws Exception
	 */
	@Test
	public void testDistinctMachineClass() throws Exception {
		
	}

}
