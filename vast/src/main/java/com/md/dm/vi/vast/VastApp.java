/**
 * 
 */
package com.md.dm.vi.vast;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

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

	private Map<String, Machine> machinesMap = new HashMap<String, Machine>();

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				VastConfig.class);

		VastApp vastApp = context.getBean(VastApp.class);

		// vastApp.createDatabase();
		// vastApp.populateMeta();
		// vastApp.populateMetaStatus();
		vastApp.populateMachines();
		vastApp.populateMachineStatus();
		// vastApp.bost();
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

		// if (mongoOperations.collectionExists(Machine.class)) {
		// mongoOperations.dropCollection(Machine.class);
		// mongoOperations.createCollection(Machine.class);
		// }
		//
		// mongoOperations.indexOps(Machine.class).ensureIndex(
		// new Index().on("ipAddr", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("machineClass", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("machineFunction", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("bussinesUnit", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("facility", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("statusList.healthTime", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("statusList.fixedHealthTime", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("statusList.policyStatus", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(
				new Index().on("statusList.activityFlag", Order.ASCENDING));
		mongoOperations.indexOps(Machine.class).ensureIndex(new GeospatialIndex("location"));

		Scanner scanner = new Scanner(new FileInputStream(
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/meta-3-7.csv"), "UTF-8");
		try {
			List<Machine> machines = new ArrayList<Machine>();

			System.out.println(scanner.nextLine());
			while (scanner.hasNextLine()) {
				Machine machine = Machine.build(scanner.nextLine());

				machines.add(machine);
				machinesMap.put(machine.getIpAddr(), machine);

				if (machines.size() == 100000) {
					System.out.println("Insert!");
					Collections.shuffle(machines);
					mongoOperations.insert(machines, Machine.class);
					machines = new ArrayList<Machine>();
				}
			}

			if (!machines.isEmpty()) {
				Collections.shuffle(machines);
				mongoOperations.insert(machines, Machine.class);
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
				"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/metaStatus-3-7.csv"),
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
				if (Integer.parseInt(data[4].trim()) == 3 || Integer.parseInt(data[4].trim()) == 4
						|| Integer.parseInt(data[4].trim()) == 5) {
					Query query = new Query(Criteria.where("ipAddr").is(data[1].trim()));

					Machine machine = machinesMap.get(data[1]);

					Double longitude = machine.getLocation()[1];

					int offset = (int) (Math.floor(Math.floor(Math.abs(Math
							.round(longitude * 1000000.)) / 1000000) / 15) * -1);
					Date healthTime = formatter.parse(data[2].trim());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(healthTime);
					calendar.add(Calendar.HOUR, offset);
					Date fixedDate = calendar.getTime();
					Status status = new Status(Long.parseLong(data[0]), healthTime,
							Integer.parseInt(data[3]), Integer.parseInt(data[4]),
							Integer.parseInt(data[5]), fixedDate);

					System.out.println(status);

					mongoOperations.findAndModify(query, new Update().push("statusList", status),
							Machine.class);
				}

			}

		} finally {
			scanner.close();
		}

	}

	private void bost() throws Exception {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Query query = new Query(Criteria.where("ipAddr").is("172.1.3.83"));

		Machine machine = mongoOperations.findOne(query, Machine.class);

		String line = "259723,172.1.3.83,2012-02-03 14:30:00,36,1,1";
		String[] data = line.split(",");

		Double longitude = machine.getLocation()[1];

		int offset = (int) (Math
				.floor(Math.floor(Math.abs(Math.round(longitude * 1000000.)) / 1000000) / 15) * -1);
		Date healthTime = formatter.parse(data[2].trim());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(healthTime);
		calendar.add(Calendar.HOUR, offset);
		Date fixedDate = calendar.getTime();
		Status status = new Status(Long.parseLong(data[0]), healthTime, Integer.parseInt(data[3]),
				Integer.parseInt(data[4]), Integer.parseInt(data[5]), fixedDate);

		System.out.println(status);

		mongoOperations
				.findAndModify(query, new Update().push("statusList", status), Machine.class);

	}
}
