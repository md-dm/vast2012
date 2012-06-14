package com.md.dm.infovis.vast.controller;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

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
	 * > db.machine.find({statusList: {$ne:[]}}).count(); 
	 * 809216 >
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
		System.out.println(dataController.filter("region-1", "branch1").count());
	}

	@Test
	public void testGroup() throws Exception {
		DBObject group = dataController.group("region-1", "branch1");
		System.out.println(group);
	}
}
