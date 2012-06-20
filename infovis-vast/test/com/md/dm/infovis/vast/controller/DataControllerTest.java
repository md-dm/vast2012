package com.md.dm.infovis.vast.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteConcern;

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
		System.out.println(dataController.filter("region-1", "branch1").count());
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
		qb.put("statusList.policyStatus").in(new int[] { 4, 5 });

		// for (int i = 1; i < 20; i++) {
		// qb.or(new BasicDBObject("bussinesUnit",
		// "region-1").append("facility",
		// "branch" + i));
		// }

		System.out.println(qb.get());

		DBObject group = dataController.group(key, qb.get());

		System.out.println(group);
	}

	@Test
	public void testCreateNewCollectionFromGroup() throws Exception {

		BasicDBObject key = new BasicDBObject();
		key.append("bussinesUnit", true);
		key.append("facility", true);
		key.append("location", true);

		DataController mongolabDataController = new DataController(new Mongo(new MongoURI("mongodb://fulgura:Diego80911@ds035127.mongolab.com:35127/vast")), "vast", "region");

		DB db = dataController.getDB("vast");
		
		if(db.collectionExists("region")){
			DBCollection collection = db.getCollection("region");
			collection.drop();
		}
		
		
		QueryBuilder qb = new QueryBuilder();
		//qb.put("statusList").notEquals(new ArrayList());
		DBObject group = dataController.group(key, qb.get());

		DBCollection region = db.createCollection("region", null);
		
		BasicDBList basicDBList = (BasicDBList) group;
		for (Object object : basicDBList) {
			region.insert((DBObject)object);
		}
		
		region.ensureIndex(new BasicDBObject("bussinesUnit", 1).append("facility", 1));
		region.ensureIndex(new BasicDBObject("bussinesUnit", 1));
		region.ensureIndex(new BasicDBObject("facility", 1));
		region.ensureIndex(new BasicDBObject("location", "2d"));
		region.ensureIndex(new BasicDBObject("policyStatus1", 1));
		region.ensureIndex(new BasicDBObject("policyStatus2", 1));
		region.ensureIndex(new BasicDBObject("policyStatus3", 1));
		region.ensureIndex(new BasicDBObject("policyStatus4", 1));
		region.ensureIndex(new BasicDBObject("policyStatus5", 1));
		region.ensureIndex(new BasicDBObject("activityFlag1", 1));
		region.ensureIndex(new BasicDBObject("activityFlag2", 1));
		region.ensureIndex(new BasicDBObject("activityFlag3", 1));
		region.ensureIndex(new BasicDBObject("activityFlag4", 1));
		region.ensureIndex(new BasicDBObject("activityFlag5", 1));
		
		System.out.println(region.count());

	}
	
	@Test
	public void testFilterRegions() throws Exception {
		
		DataController machineDataController = new DataController(new Mongo("localhost:27022"), "vast", "region");
		System.out.println(machineDataController.find(QueryBuilder.start().get()).count());
	}
	
	@Test
	public void testConnectRemote() throws Exception {
		BasicDBObject key = new BasicDBObject();
		key.append("bussinesUnit", true);
		key.append("facility", true);
		key.append("location", true);

		DB db = dataController.getDB("vast");
		
		if(db.collectionExists("region")){
			DBCollection collection = db.getCollection("region");
			collection.drop();
		}
		
		
		QueryBuilder qb = new QueryBuilder();
		//qb.put("statusList").notEquals(new ArrayList());
		DBObject group = dataController.group(key, qb.get());

		DBCollection region = db.createCollection("region", null);
		
		BasicDBList basicDBList = (BasicDBList) group;
		for (Object object : basicDBList) {
			region.insert((DBObject)object);
		}
		
		region.ensureIndex(new BasicDBObject("bussinesUnit", 1).append("facility", 1));
		region.ensureIndex(new BasicDBObject("bussinesUnit", 1));
		region.ensureIndex(new BasicDBObject("facility", 1));
		region.ensureIndex(new BasicDBObject("location", "2d"));
		region.ensureIndex(new BasicDBObject("policyStatus1", 1));
		region.ensureIndex(new BasicDBObject("policyStatus2", 1));
		region.ensureIndex(new BasicDBObject("policyStatus3", 1));
		region.ensureIndex(new BasicDBObject("policyStatus4", 1));
		region.ensureIndex(new BasicDBObject("policyStatus5", 1));
		region.ensureIndex(new BasicDBObject("activityFlag1", 1));
		region.ensureIndex(new BasicDBObject("activityFlag2", 1));
		region.ensureIndex(new BasicDBObject("activityFlag3", 1));
		region.ensureIndex(new BasicDBObject("activityFlag4", 1));
		region.ensureIndex(new BasicDBObject("activityFlag5", 1));
		
		System.out.println(region.count());
		
	}

}
