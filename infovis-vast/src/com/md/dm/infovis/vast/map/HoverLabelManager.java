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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 * @author diego
 * 
 */
public class HoverLabelManager implements MouseMotionListener {

	private final JLabel hoverLabel;
	private final JPanel hoverPanel;

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
		System.out.println("public void mouseDragged(MouseEvent e)" + e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		JXMapViewer map = mapKit.getMainMap();
		// location of Java
		GeoPosition gp = new GeoPosition(1, 1);
		// convert to world bitmap
		Point2D gp_pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
		// convert to screen
		Rectangle rect = map.getViewportBounds();
		Point converted_gp_pt = new Point((int) gp_pt.getX() - rect.x, (int) gp_pt.getY() - rect.y);

		// check if near the mouse
		if (converted_gp_pt.distance(e.getPoint()) < 10) {
			hoverPanel.setLocation(converted_gp_pt);
			hoverPanel.setVisible(true);
		} else {
			//hoverPanel.setVisible(false);
		}

	}

}
