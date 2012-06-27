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
	private String pieType;

	public PieWaypoint(DBObject dbObject, String pieType) {
		super();
		this.dbObject = dbObject;
		this.pieType = pieType;
	}

	public DBObject getDbObject() {
		return dbObject;
	}

	public List<Slice> getSlices() {
		if (pieType.startsWith("PolicyStatus")) {
			return Arrays.asList(new Slice[] {
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
		if (pieType.startsWith("ActivityFlag")) {
			return Arrays.asList(new Slice[] {
					new Slice((Double) dbObject.get("activityFlag1"),
							new Color(27, 158, 119, 100)),
					new Slice((Double) dbObject.get("activityFlag2"),
							new Color(217, 95, 2, 100)),
					new Slice((Double) dbObject.get("activityFlag3"),
							new Color(117, 112, 179, 100)),
					new Slice((Double) dbObject.get("activityFlag4"),
							new Color(231, 41, 138, 100)),
					new Slice((Double) dbObject.get("activityFlag5"),
							new Color(102, 166, 30, 100)) });
		}
		if (pieType.startsWith("MachineClass")) {
			return Arrays.asList(new Slice[] {
					new Slice((Double) dbObject.get("atm"), new Color(27, 158,
							119, 100)),
					new Slice((Double) dbObject.get("server"), new Color(217,
							95, 2, 100)),
					new Slice((Double) dbObject.get("workstation"), new Color(
							117, 112, 179, 100)) });
		}
		return Arrays.asList(new Slice[] {
				new Slice((Double) dbObject.get("empty"), new Color(166, 206, 227, 100)),
				new Slice((Double) dbObject.get("compute"), new Color(31, 120, 180, 100)),
				new Slice((Double) dbObject.get("email"), new Color(178, 223, 138, 100)),
				new Slice((Double) dbObject.get("fileserver"), new Color(51, 160, 44, 100)),
				new Slice((Double) dbObject.get("loan"), new Color(251, 154, 153, 100)),
				new Slice((Double) dbObject.get("fileserver"), new Color(227, 26, 28, 100)),
				new Slice((Double) dbObject.get("multiple"), new Color(253, 191, 111, 100)),
				new Slice((Double) dbObject.get("office"), new Color(253, 191, 111, 100)),
				new Slice((Double) dbObject.get("teller"), new Color(255, 127, 0, 100)),
				new Slice((Double) dbObject.get("web"), new Color(202, 178, 214, 100)) });
	}

	public String getLabel() {
		String bussinedUnit = (String) dbObject.get("bussinesUnit");
		String shortBU = "";
		if (bussinedUnit.equals("headquarters")) {
			shortBU = "hq";
		} else {
			shortBU = "rg";
			String[] split = bussinedUnit.split("-");
			if (split.length > 1) {
				shortBU += split[1];
			}
		}
		String facility = (String) dbObject.get("facility");
		String shortFa = "";
		if (facility.startsWith("datacenter")) {
			shortFa = "dc";
			String[] split = facility.split("-");
			if (split.length > 1) {
				shortFa += split[1];
			}
		} else {
			shortFa = "br";
			String substring = facility.substring(6);
			shortFa += substring;
		}

		return shortBU + "-" + shortFa;
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
