/**
 * 
 */
package com.md.dm.vi.vast;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.md.dm.vi.vast.model.Machine;
import com.md.dm.vi.vast.model.Meta;
import com.md.dm.vi.vast.model.MetaStatus;
import com.md.dm.vi.vast.model.Status;

/**
 * @author diego
 * 
 */
public class VastApp {

	private static final Log log = LogFactory.getLog(VastApp.class);

	@Inject
	private MongoOperations mongoOperations;

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				VastConfig.class);

		VastApp vastApp = context.getBean(VastApp.class);

//		vastApp.createDatabase();
//		vastApp.populateMeta();
//		vastApp.populateMetaStatus();
		vastApp.populateMachines();
		vastApp.populateMachineStatus();

		// MongoOperations mongoOps = new MongoTemplate(new Mongo(), "vast");
		//
		// mongoOps.indexOps(Meta.class).ensureIndex(
		// new Index().on("ipAddr", Order.ASCENDING));
		// mongoOps.indexOps(Meta.class).ensureIndex(
		// new GeospatialIndex("location"));
		//
		// mongoOps.insert(Meta
		// .build("172.8.238.33,server,file server,region-1,branch3,40.031174,-141.19032"));
		//
		// log.info(mongoOps.findOne(
		// new Query(Criteria.where("ipAddr").is("172.8.238.33")),
		// Meta.class));
		//
		// // lower-left then upper-right
		// Box box = new Box(new Point(-73.99756, 40.73083), new
		// Point(-73.988135,
		// 40.741404));
		//
		// List<Meta> metas = mongoOps.find(new Query(Criteria.where("location")
		// .within(box)), Meta.class);
		//
		// // mongoOps.dropCollection("meta");

	}

	private void createDatabase() {
		if (mongoOperations.collectionExists(Meta.class)) {
			mongoOperations.dropCollection(Meta.class);
			mongoOperations.createCollection(Meta.class);
		}
		mongoOperations.indexOps(Meta.class).ensureIndex(new Index().on("ipAddr", Order.ASCENDING));
		mongoOperations.indexOps(Meta.class).ensureIndex(
				new Index().on("machineClass", Order.ASCENDING));
		mongoOperations.indexOps(Meta.class).ensureIndex(
				new Index().on("machineFunction", Order.ASCENDING));
		mongoOperations.indexOps(Meta.class).ensureIndex(
				new Index().on("bussinesUnit", Order.ASCENDING));
		mongoOperations.indexOps(Meta.class).ensureIndex(
				new Index().on("facility", Order.ASCENDING));
		mongoOperations.indexOps(Meta.class).ensureIndex(new GeospatialIndex("location"));

		if (mongoOperations.collectionExists(MetaStatus.class)) {
			mongoOperations.dropCollection(MetaStatus.class);
			mongoOperations.createCollection(MetaStatus.class);
		}
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("tkey", Order.ASCENDING));
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("ipAddr", Order.ASCENDING));

		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("firstOctect", Order.ASCENDING));
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("secondOctect", Order.ASCENDING));
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("thirdOctect", Order.ASCENDING));
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("fourthOctect", Order.ASCENDING));

		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("numConnections", Order.ASCENDING));
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("policyStatus", Order.ASCENDING));
		mongoOperations.indexOps(MetaStatus.class).ensureIndex(
				new Index().on("activityFlag", Order.ASCENDING));

	}

	private void populateMeta() throws Exception {

		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/meta-3-7.csv"), "UTF-8");

		try {
			System.out.println(scanner.nextLine());
			while (scanner.hasNextLine()) {
				Meta meta = Meta.build(scanner.nextLine());
				System.out.println(meta);
				mongoOperations.save(meta);
			}
		} finally {
			scanner.close();
		}

	}

	private void populateMetaStatus() throws Exception {

		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/metaStatus-3-7.csv"),
				"UTF-8");

		try {
			System.out.println(scanner.nextLine());
			while (scanner.hasNextLine()) {
				MetaStatus metaStatus = MetaStatus.build(scanner.nextLine());
				System.out.println(metaStatus);
				mongoOperations.save(metaStatus);
			}

		} finally {
			scanner.close();
		}

	}

	private void populateMachines() throws Exception {

		if (mongoOperations.collectionExists(Machine.class)) {
			mongoOperations.dropCollection(Machine.class);
			mongoOperations.createCollection(Machine.class);
		}

		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("ipAddr", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("machineClass", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("machineFunction", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("bussinesUnit", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("facility", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(new GeospatialIndex("location"));

//		Machine machine = Machine
//				.build("172.1.1.47,workstation,office,headquarters,headquarters,0.8173708,-70.259796");
//		System.out.println(machine);
//		mongoOperations.save(machine);
//		machine = Machine
//				.build("172.1.1.49,workstation,office,headquarters,headquarters,0.8173708,-70.259796");
//		System.out.println(machine);
//		mongoOperations.save(machine);
//
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		mongoOperations.findAndModify(new Query(Criteria.where("ipAddr").is("172.1.1.47")),
//				new Update().push(
//						"statusList",
//						new Status(Long.parseLong("2448776"), formatter
//								.parse("2012-02-02 14:00:00"), Integer.parseInt("83"), Integer
//								.parseInt("1"), Integer.parseInt("1"))), Machine.class);
//		
//		mongoOperations.findAndModify(new Query(Criteria.where("ipAddr").is("172.1.1.49")),
//				new Update().push(
//						"statusList",
//						new Status(Long.parseLong("2448777"), formatter
//								.parse("2012-02-02 14:00:00"), Integer.parseInt("83"), Integer
//								.parseInt("3"), Integer.parseInt("2"))), Machine.class);

		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/meta-3-7.csv"), "UTF-8");
		try {
			System.out.println(scanner.nextLine());
			while (scanner.hasNextLine()) {
				Machine machine = Machine.build(scanner.nextLine());
				System.out.println(machine);
				mongoOperations.save(machine);
			}
		} finally {
			scanner.close();
		}
	}

	private void populateMachineStatus() throws Exception {

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/windowOneSingle.csv"),
				"UTF-8");

		try {
			System.out.println(scanner.nextLine());
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();
				String[] data = line.split(",");

				if (data.length != 6) {
					throw new RuntimeException("Invalid parameters");
				}
				// find
				Query query = new Query(Criteria.where("ipAddr").is(data[1].trim()));
				List<Machine> find = mongoOperations.find(query, Machine.class);
				Double longitude = find.get(0).getLocation()[1];
				int offset = (int) (Math.floor(Math.floor(Math.abs(Math.round(longitude * 1000000.))/1000000) / 15) * -1);
				Date healthTime = formatter.parse(data[2].trim());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(healthTime);
				calendar.add(Calendar.HOUR, offset);
				Date fixedDate = calendar.getTime();
				Status status = new Status(Long.parseLong(data[0]),
						healthTime, Integer.parseInt(data[3]),
						Integer.parseInt(data[4]), Integer.parseInt(data[5]), fixedDate);
				System.out.println(status);
				mongoOperations.findAndModify(query, new Update().push("statusList", status),
						Machine.class);
			}

		} finally {
			scanner.close();
		}

	}
}
