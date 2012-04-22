/**
 * 
 */
package com.md.dm.vi.vast;

import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
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
@Configuration
public class VastApp {

	private static final Log log = LogFactory.getLog(VastApp.class);

	public static void main(String[] args) throws Exception {

		MongoOperations mongoOps = new MongoTemplate(new Mongo(), "vast");
		
		mongoOps.indexOps(Meta.class).ensureIndex(
				new Index().on("ipAddr", Order.ASCENDING));
		mongoOps.indexOps(Meta.class).ensureIndex(
				new GeospatialIndex("location"));
		
		mongoOps.insert(Meta
				.build("172.8.238.33,server,file server,region-1,branch3,40.031174,-141.19032"));

		log.info(mongoOps.findOne(
				new Query(Criteria.where("ipAddr").is("172.8.238.33")),
				Meta.class));

		// lower-left then upper-right
		Box box = new Box(new Point(-73.99756, 40.73083), new Point(-73.988135,
				40.741404));
		
		List<Meta> metas = mongoOps.find(new Query(Criteria.where("location")
				.within(box)), Meta.class);

		// mongoOps.dropCollection("meta");

	}

	private void populateMeta() throws Exception {

		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(
				new FileInputStream(
						"/Users/diego/Documents/Maestria/VI/vast2012/metaDB-csv-3-7/meta-3-7.csv"),
				"UTF-8");

		try {
			System.out.println(scanner.nextLine());
			while (scanner.hasNextLine()) {

				Meta meta = Meta.build(scanner.nextLine());

				System.out.println(meta);
			}
		} finally {
			scanner.close();
		}

	}
}
