package com.md.dm.infovis.vast.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class DataControllerTest {

	private DataController dataController;

	@Before
	public void setUp() throws Exception {
		dataController = new DataController();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetCollection() {
		DBCollection collection = dataController.getCollection("meta");
		Assert.assertNotNull(collection);
		Assert.assertEquals("meta", collection.getName());
	}

	@Test
	public void testCountCollection() throws Exception {
		long count = dataController.count("meta");
		Assert.assertTrue(count >= 0);
	}

	/**
	 * db.machine.find({}).count(); 888977 >
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindAll() throws Exception {
		System.out.println(dataController.find().count());
	}

	/**
	 * > db.machine.find({statusList: {$ne:[]}}).count(); 809216 >
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindAllNonEmpty() throws Exception {
		BasicDBObject query = new BasicDBObject();
		query.append("statusList", new BasicDBObject("$ne", new ArrayList()));
		System.out.println(dataController.find(query).count());
	}

	@Test
	public void testFilter() throws Exception {
		System.out
				.println(dataController.filter("region-1", "branch1").count());
	}

	@Test
	public void testGroup() throws Exception {
		DBObject group = dataController.group("region-1", "branch1");
		System.out.println(group);
	}

	@Test
	public void testGroupWithParameter() throws Exception {

		BasicDBObject key = new BasicDBObject();
		key.append("bussinesUnit", true);
		key.append("facility", true);
		key.append("location", true);

		BasicDBObject cond = new BasicDBObject();
		cond.append("bussinesUnit", "region-1");
		cond.append("facility", "branch1");
		cond.append("statusList", new BasicDBObject("$ne", new ArrayList()));
		// cond.append("statusList.policyStatus", 1);

		DBObject group = dataController.group(key, cond);
		System.out.println(group);
	}

	@Test
	public void testGroupWithOrParameter() throws Exception {

		BasicDBObject key = new BasicDBObject();
		key.append("bussinesUnit", true);
		key.append("facility", true);
		key.append("location", true);

		QueryBuilder qb = new QueryBuilder();
		qb.put("statusList").notEquals(new ArrayList());
		qb.put("statusList.policyStatus").in(new int[]{4,5});
		
//		for (int i = 1; i < 20; i++) {
//			qb.or(new BasicDBObject("bussinesUnit", "region-1").append("facility",
//					"branch" + i));
//		}
		
		System.out.println(qb.get());
		
		DBObject group = dataController.group(key, qb.get());

		System.out.println(group);
	}

}
