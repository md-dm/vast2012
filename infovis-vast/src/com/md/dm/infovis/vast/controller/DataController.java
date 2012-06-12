package com.md.dm.infovis.vast.controller;

import java.util.List;

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

	private DBCollection collection;

	public DataController() throws Exception {
		this(new Mongo("localhost", 27022), "vast", "machine");
	}

	public DataController(Mongo mongo, String databaseName, String collectionName) {
		super();
		this.mongo = mongo;
		this.db = mongo.getDB(databaseName);
		this.collection = db.getCollection(collectionName);
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
	public final DBObject findOne(DBObject o, DBObject fields, ReadPreference readPref) {
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
	public DBObject group(DBObject key, DBObject cond, DBObject initial, String reduce,
			String finalize) throws MongoException {
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
	public DBObject group(DBObject key, DBObject cond, DBObject initial, String reduce)
			throws MongoException {
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
	public MapReduceOutput mapReduce(MapReduceCommand command) throws MongoException {
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
	public MapReduceOutput mapReduce(String map, String reduce, String outputTarget, DBObject query)
			throws MongoException {
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
	public MapReduceOutput mapReduce(String map, String reduce, String outputTarget,
			OutputType outputType, DBObject query) throws MongoException {
		return collection.mapReduce(map, reduce, outputTarget, outputType, query);
	}

}
