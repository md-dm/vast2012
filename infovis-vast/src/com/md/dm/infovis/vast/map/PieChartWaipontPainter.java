/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author diego
 * 
 */
public class PieChartWaipontPainter extends WaypointPainter<JXMapViewer> {

	private Set<Waypoint> waypoints;

	public PieChartWaipontPainter(Set<Waypoint> waypoints) {
		super();
		this.waypoints = waypoints;
		this.setRenderer(new PieChartWaypointRenderer());
	}

	public PieChartWaipontPainter(DBCursor cursor) {
		waypoints = new HashSet<Waypoint>();
		for (DBObject dbObject : cursor) {
			System.out.println(dbObject.get("location"));
			
		}
		this.setRenderer(new PieChartWaypointRenderer());
	}

	/**
	 * Generates with random points!
	 */
	public PieChartWaipontPainter() {
		waypoints = new HashSet<Waypoint>();
		Random rn = new Random();
		for (int i = 0; i < 4000; i++) {

			double latitude = (rn.nextDouble() * -180.0) + 90.0;
			double longitude = (rn.nextDouble() * -360.0) + 180.0;
			waypoints.add(new Waypoint(latitude, longitude));
		}
		this.setRenderer(new PieChartWaypointRenderer());
	}

	public Set<Waypoint> getWaypoints() {
		return waypoints;
	}
}
