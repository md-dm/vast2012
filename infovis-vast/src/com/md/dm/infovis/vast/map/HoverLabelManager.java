/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Set;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

import com.mongodb.DBObject;

/**
 * @author diego
 * 
 */
public class HoverLabelManager implements MouseMotionListener {

	private final DetailPanel hoverPanel;
	private PieChartWaypontPainter pieChartWaypontPainter;

	private final JXMapKit mapKit;

	public HoverLabelManager(JXMapKit mapKit) {
		super();

		this.hoverPanel = new DetailPanel();
		hoverPanel.setVisible(false);
		this.mapKit = mapKit;
		this.mapKit.getMainMap().add(hoverPanel);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		JXMapViewer map = mapKit.getMainMap();
		if (this.pieChartWaypontPainter != null) {

			Set<Waypoint> waypoints = this.pieChartWaypontPainter.getWaypoints();
			for (Waypoint waypoint : waypoints) {

				PieWaypoint pieWaypoint = (PieWaypoint) waypoint;

				Point2D gp_pt = map.getTileFactory().geoToPixel(pieWaypoint.getPosition(),
						map.getZoom());
				Rectangle rect = map.getViewportBounds();

				if (rect.contains(gp_pt)) {
					System.out.println("Entra: " + pieWaypoint.getLabel());
				}

			}
		}

	}

	/**
	 * @return the pieChartWaypontPainter
	 */
	public PieChartWaypontPainter getPieChartWaypontPainter() {
		return pieChartWaypontPainter;
	}

	/**
	 * @param pieChartWaypontPainter
	 *            the pieChartWaypontPainter to set
	 */
	public void setPieChartWaypontPainter(PieChartWaypontPainter pieChartWaypontPainter) {
		this.pieChartWaypontPainter = pieChartWaypontPainter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		if (this.pieChartWaypontPainter == null) {
			return;
		}

		JXMapViewer map = mapKit.getMainMap();
		Rectangle rect = map.getViewportBounds();

		Set<Waypoint> waypoints = this.pieChartWaypontPainter.getWaypoints();
		for (Waypoint waypoint : waypoints) {

			PieWaypoint pieWaypoint = (PieWaypoint) waypoint;
			System.out.println(this.contains(pieWaypoint.getPosition(), e));

			Point2D gp_pt = map.getTileFactory().geoToPixel(pieWaypoint.getPosition(),
					map.getZoom());

			if (rect.contains(gp_pt)) {

				Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY()
						- rect.y);

				// System.out.println("Distancia: " + (int)
				// e.getPoint().distance(gp_pt)
				// + " | evento(" + (int) e.getX() + ", " + (int) e.getY() + ")"
				// + "geo("
				// + (int) gp_pt.getX() + ", " + (int) gp_pt.getY() +
				// ") -- rect:[ ("
				// + (int) rect.getX() + ", " + (int) rect.getY() + ")-w:"
				// + (int) rect.getWidth() + ", h:" + (int) rect.getHeight() +
				// "] -  -"
				// + converted_gp_pt);

				// check if near the mouse
				if (converted_gp_pt.distance(e.getPoint()) < 10) {
					// System.out.println("Match!!!!");

					DBObject dbObject = pieWaypoint.getDbObject();
					hoverPanel.setDBObject(dbObject);
					hoverPanel.setLocation(converted_gp_pt);
					hoverPanel.setVisible(true);
				} else {
					hoverPanel.setVisible(false);
				}
			}

		}

	}

	public boolean contains(GeoPosition gp, MouseEvent e) {
		JXMapViewer map = mapKit.getMainMap();
		// convert to world bitmap
		Point2D gp_pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
		// convert to screen
		Rectangle rect = map.getViewportBounds();
		Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY() - rect.y);
		// check if near the mouse
		if (converted_gp_pt.distance(e.getPoint()) < 10) {
			return true;
		} else {
			return false;
		}
	}
}
