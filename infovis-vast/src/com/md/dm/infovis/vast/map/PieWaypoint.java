/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

/**
 * @author diego
 * 
 */
public class PieWaypoint extends Waypoint {

	private DBObject dbObject;

	public PieWaypoint(DBObject dbObject) {
		super();
		this.dbObject = dbObject;
	}

	public DBObject getDbObject() {
		return dbObject;
	}

	public List<Slice> getSlices() {
		return Arrays
				.asList(new Slice[] {
						new Slice((Double) dbObject.get("policyStatus1"),
								new Color(26, 150, 65, 100)),
						new Slice((Double) dbObject.get("policyStatus2"),
								new Color(166, 217, 106, 100)),
						new Slice((Double) dbObject.get("policyStatus3"),
								new Color(255, 255, 191, 100)),
						new Slice((Double) dbObject.get("policyStatus4"),
								new Color(253, 174, 97, 100)),
						new Slice((Double) dbObject.get("policyStatus5"),
								new Color(215, 25, 28, 100)) });
	}

	@Override
	public GeoPosition getPosition() {
		BasicDBList locations = (BasicDBList) dbObject.get("location");
		double latitude = (Double) locations.get(0);
		double longitude = (Double) locations.get(1);
		return new GeoPosition(latitude, longitude);
	}

	public static class Slice {
		double value;
		Color color;

		public Slice(double value, Color color) {
			this.value = value;
			this.color = color;
		}
	}
}
