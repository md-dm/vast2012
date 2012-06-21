package com.md.dm.infovis.vast.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.GroupCommand;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;

/**
 * 
 * @author diego
 * @see http://www.mongodb.org/display/DOCS/Java+Tutorial
 */
public class DataController {

	private Mongo mongo;
	private DB db;
	private String groupFunction;

	private DBCollection collection;

	public DataController() throws Exception {
		this(new Mongo("localhost", 27022), "vast", "machine");
	}

	public DataController(Mongo mongo, String databaseName,
			String collectionName) throws Exception {
		super();
		this.mongo = mongo;
		this.db = mongo.getDB(databaseName);
		this.collection = db.getCollection(collectionName);
		this.groupFunction = this
				.readFileAsString("com/md/dm/infovis/vast/controller/group.js");
	}

	/**
	 * @param dbName
	 * @throws MongoException
	 * @see com.mongodb.Mongo#dropDatabase(java.lang.String)
	 */
	public void dropDatabase(String dbName) throws MongoException {
		mongo.dropDatabase(dbName);
	}

	/**
	 * @param dbname
	 * @return
	 * @see com.mongodb.Mongo#getDB(java.lang.String)
	 */
	public DB getDB(String dbname) {
		return mongo.getDB(dbname);
	}

	/**
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.Mongo#getDatabaseNames()
	 */
	public List<String> getDatabaseNames() throws MongoException {
		return mongo.getDatabaseNames();
	}

	public DBCollection getCollection(String collectionName) {
		return db.getCollection(collectionName);
	}

	public long count(String collectionName) {
		return db.getCollection(collectionName).count();
	}

	public long count(String collectionName, DBObject query) {
		return db.getCollection(collectionName).count(query);
	}

	/**
	 * @return
	 * @see com.mongodb.DBCollection#find()
	 */
	public final DBCursor find() {
		return collection.find();
	}

	/**
	 * @param ref
	 * @param keys
	 * @return
	 * @see com.mongodb.DBCollection#find(com.mongodb.DBObject,
	 *      com.mongodb.DBObject)
	 */
	public final DBCursor find(DBObject ref, DBObject keys) {
		return collection.find(ref, keys);
	}

	/**
	 * @param ref
	 * @return
	 * @see com.mongodb.DBCollection#find(com.mongodb.DBObject)
	 */
	public final DBCursor find(DBObject ref) {
		return collection.find(ref);
	}

	/**
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#findOne()
	 */
	public final DBObject findOne() throws MongoException {
		return collection.findOne();
	}

	/**
	 * @param o
	 * @param fields
	 * @param readPref
	 * @return
	 * @see com.mongodb.DBCollection#findOne(com.mongodb.DBObject,
	 *      com.mongodb.DBObject, com.mongodb.ReadPreference)
	 */
	public final DBObject findOne(DBObject o, DBObject fields,
			ReadPreference readPref) {
		return collection.findOne(o, fields, readPref);
	}

	/**
	 * @param o
	 * @param fields
	 * @return
	 * @see com.mongodb.DBCollection#findOne(com.mongodb.DBObject,
	 *      com.mongodb.DBObject)
	 */
	public final DBObject findOne(DBObject o, DBObject fields) {
		return collection.findOne(o, fields);
	}

	/**
	 * @param o
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#findOne(com.mongodb.DBObject)
	 */
	public final DBObject findOne(DBObject o) throws MongoException {
		return collection.findOne(o);
	}

	/**
	 * @param obj
	 * @param fields
	 * @return
	 * @see com.mongodb.DBCollection#findOne(java.lang.Object,
	 *      com.mongodb.DBObject)
	 */
	public final DBObject findOne(Object obj, DBObject fields) {
		return collection.findOne(obj, fields);
	}

	/**
	 * @param obj
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#findOne(java.lang.Object)
	 */
	public final DBObject findOne(Object obj) throws MongoException {
		return collection.findOne(obj);
	}

	/**
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#getCount()
	 */
	public long getCount() throws MongoException {
		return collection.getCount();
	}

	/**
	 * @param key
	 * @param cond
	 * @param initial
	 * @param reduce
	 * @param finalize
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#group(com.mongodb.DBObject,
	 *      com.mongodb.DBObject, com.mongodb.DBObject, java.lang.String,
	 *      java.lang.String)
	 */
	public DBObject group(DBObject key, DBObject cond, DBObject initial,
			String reduce, String finalize) throws MongoException {
		return collection.group(key, cond, initial, reduce, finalize);
	}

	/**
	 * @param key
	 * @param cond
	 * @param initial
	 * @param reduce
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#group(com.mongodb.DBObject,
	 *      com.mongodb.DBObject, com.mongodb.DBObject, java.lang.String)
	 */
	public DBObject group(DBObject key, DBObject cond, DBObject initial,
			String reduce) throws MongoException {
		return collection.group(key, cond, initial, reduce);
	}

	/**
	 * @param args
	 * @return
	 * @throws MongoException
	 * @deprecated
	 * @see com.mongodb.DBCollection#group(com.mongodb.DBObject)
	 */
	public DBObject group(DBObject args) throws MongoException {
		return collection.group(args);
	}

	/**
	 * @param cmd
	 * @return
	 * @see com.mongodb.DBCollection#group(com.mongodb.GroupCommand)
	 */
	public DBObject group(GroupCommand cmd) {
		return collection.group(cmd);
	}

	/**
	 * @param command
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#mapReduce(com.mongodb.DBObject)
	 */
	public MapReduceOutput mapReduce(DBObject command) throws MongoException {
		return collection.mapReduce(command);
	}

	/**
	 * @param command
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#mapReduce(com.mongodb.MapReduceCommand)
	 */
	public MapReduceOutput mapReduce(MapReduceCommand command)
			throws MongoException {
		return collection.mapReduce(command);
	}

	/**
	 * @param map
	 * @param reduce
	 * @param outputTarget
	 * @param query
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#mapReduce(java.lang.String,
	 *      java.lang.String, java.lang.String, com.mongodb.DBObject)
	 */
	public MapReduceOutput mapReduce(String map, String reduce,
			String outputTarget, DBObject query) throws MongoException {
		return collection.mapReduce(map, reduce, outputTarget, query);
	}

	/**
	 * @param map
	 * @param reduce
	 * @param outputTarget
	 * @param outputType
	 * @param query
	 * @return
	 * @throws MongoException
	 * @see com.mongodb.DBCollection#mapReduce(java.lang.String,
	 *      java.lang.String, java.lang.String,
	 *      com.mongodb.MapReduceCommand.OutputType, com.mongodb.DBObject)
	 */
	public MapReduceOutput mapReduce(String map, String reduce,
			String outputTarget, OutputType outputType, DBObject query)
			throws MongoException {
		return collection.mapReduce(map, reduce, outputTarget, outputType,
				query);
	}

	/**
	 * //dataController.filterBy("bussinesUnit:'region-1', facility:'branch1'}")
	 * 
	 * @param string
	 * @param string2
	 * @return
	 */
	public DBCursor filter(String bussinesUnit, String facility) {

		BasicDBObject query = new BasicDBObject();
		query.append("bussinesUnit", bussinesUnit);
		query.append("facility", facility);

		return collection.find(query);

	}

	public DBCursor filter(DBObject query) {
		return collection.find(query);
	}

	public DBObject group(BasicDBObject key, DBObject cond) {

		BasicDBObject initial = new BasicDBObject();
		initial.append("count", 0);
		initial.append("policyStatus1", 0);
		initial.append("policyStatus2", 0);
		initial.append("policyStatus3", 0);
		initial.append("policyStatus4", 0);
		initial.append("policyStatus5", 0);
		initial.append("activityFlag1", 0);
		initial.append("activityFlag2", 0);
		initial.append("activityFlag3", 0);
		initial.append("activityFlag4", 0);
		initial.append("activityFlag5", 0);
		initial.append("atm", 0);
		initial.append("server", 0);
		initial.append("workstation", 0);

		initial.append("empty", 0);
		initial.append("compute", 0);
		initial.append("email", 0);
		initial.append("fileserver", 0);
		initial.append("loan", 0);
		initial.append("multiple", 0);
		initial.append("office", 0);
		initial.append("teller", 0);
		initial.append("web", 0);

		// initial.append("ipAddr", new ArrayList());

		DBObject dbObject = collection.group(key, cond, initial,
				this.groupFunction);

		return dbObject;
	}

	/**
	 * //dataController.filterBy("bussinesUnit:'region-1', facility:'branch1'}")
	 * 
	 * @param string
	 * @param string2
	 * @return
	 */
	public DBObject group(String bussinesUnit, String facility) {

		BasicDBObject key = new BasicDBObject();
		key.append("bussinesUnit", true);
		key.append("facility", true);
		key.append("location", true);

		BasicDBObject cond = new BasicDBObject();
		cond.append("bussinesUnit", bussinesUnit);
		cond.append("facility", facility);
		// Filter non empty statusList
		cond.append("statusList", new BasicDBObject("$ne", new ArrayList()));
		// cond.append("statusList.policyStatus", 1);

		BasicDBObject initial = new BasicDBObject();
		initial.append("count", 0);
		initial.append("policyStatus1", 0);
		initial.append("policyStatus2", 0);
		initial.append("policyStatus3", 0);
		initial.append("policyStatus4", 0);
		initial.append("policyStatus5", 0);
		initial.append("activityFlag1", 0);
		initial.append("activityFlag2", 0);
		initial.append("activityFlag3", 0);
		initial.append("activityFlag4", 0);
		initial.append("activityFlag5", 0);
		initial.append("atm", 0);
		initial.append("server", 0);
		initial.append("workstation", 0);

		initial.append("empty", 0);
		initial.append("compute", 0);
		initial.append("email", 0);
		initial.append("fileserver", 0);
		initial.append("loan", 0);
		initial.append("multiple", 0);
		initial.append("office", 0);
		initial.append("teller", 0);
		initial.append("web", 0);

		DBObject dbObject = collection.group(key, cond, initial,
				this.groupFunction);

		return dbObject;
	}

	/**
	 * @param filePath
	 *            name of file to open. The file can reside anywhere in the
	 *            classpath
	 */
	private String readFileAsString(String filePath) throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new InputStreamReader(this
				.getClass().getClassLoader().getResourceAsStream(filePath)));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

}
