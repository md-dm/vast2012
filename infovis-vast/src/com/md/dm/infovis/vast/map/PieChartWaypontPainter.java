/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.util.HashSet;
import java.util.Set;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

/**
 * @author diego
 * 
 */
public class PieChartWaypontPainter extends WaypointPainter<JXMapViewer> {

	public PieChartWaypontPainter(DBObject dbObject) {
		Set<Waypoint> points = new HashSet<Waypoint>();

		BasicDBList basicDBList = (BasicDBList) dbObject;

		for (int i = 0; i < basicDBList.size(); i++) {
			DBObject object = (DBObject) basicDBList.get(i);
			points.add(new PieWaypoint(object));
		}

		this.setWaypoints(points);

		this.setRenderer(new PieChartWaypointRenderer());
	}

}
