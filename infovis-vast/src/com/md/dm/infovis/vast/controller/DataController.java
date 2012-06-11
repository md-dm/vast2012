package com.md.dm.infovis.vast.controller;

import com.mongodb.Mongo;

public class DataController {

	private Mongo mongo;

	public DataController() throws Exception {
		mongo = new Mongo("localhost", 27017);
	}

	
}
