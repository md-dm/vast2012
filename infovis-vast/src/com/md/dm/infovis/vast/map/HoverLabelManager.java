/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;

/**
 * @author diego
 * 
 */
public class HoverLabelManager implements MouseMotionListener {

	private final JLabel hoverLabel;
	private final JPanel hoverPanel;
	private PieChartWaypontPainter pieChartWaypontPainter;

	private final JXMapKit mapKit;

	public HoverLabelManager(JXMapKit mapKit) {
		super();
		this.hoverLabel = new JLabel("Java");
		this.hoverPanel = new JPanel();

		this.hoverPanel.add(hoverLabel);
		this.hoverPanel.add(new JButton("#"));

		this.hoverPanel.setSize(100, 100);
		this.hoverPanel.setBackground(Color.LIGHT_GRAY);

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

		if(this.pieChartWaypontPainter == null){
			return;
		}
		
		JXMapViewer map = mapKit.getMainMap();
		Rectangle rect = map.getViewportBounds();
		
		Set<Waypoint> waypoints = this.pieChartWaypontPainter.getWaypoints();
		for (Waypoint waypoint : waypoints) {

			PieWaypoint pieWaypoint = (PieWaypoint) waypoint;

			Point2D gp_pt = map.getTileFactory().geoToPixel(pieWaypoint.getPosition(),
					map.getZoom());

			if (rect.contains(gp_pt)) {

				Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY()
						- rect.y);
				System.out.println("Rect= " + rect + "------>Point:" + e.getPoint() + "-->Region:" + pieWaypoint.getLabel() + "==" + converted_gp_pt);

				// check if near the mouse
				if (converted_gp_pt.distance(e.getPoint()) < 10) {
					System.out.println("Match!!!!");
					hoverPanel.setLocation(converted_gp_pt);
					hoverPanel.setVisible(true);

				} else {
					hoverPanel.setVisible(false);
				}
			}

		}

	}

}
