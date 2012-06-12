/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.painter.Painter;

/**
 * @author diego
 * 
 */
public class PolygonPainter implements Painter<JXMapViewer> {

	private List<GeoPosition> region;

	public PolygonPainter(List<GeoPosition> region) {
		super();
		this.region = region;
	}

	public PolygonPainter() {
		this(Arrays.asList(new GeoPosition[] {
				new GeoPosition(77.68970041647046, -161.2380960077794),
				new GeoPosition(77.68970041647046, -45.65515496228814),
				new GeoPosition(-20.79269625176585, -45.65515496228814),
				new GeoPosition(-20.79269625176585, -161.2380960077794) }));
	}

	public List<GeoPosition> getRegion() {
		return region;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdesktop.swingx.painter.Painter#paint(java.awt.Graphics2D,
	 * java.lang.Object, int, int)
	 */
	@Override
	public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
		g = (Graphics2D) g.create();
		// convert from viewport to world bitmap
		Rectangle rect = map.getViewportBounds();
		System.out.println(rect);
		g.translate(-rect.x, -rect.y);

		// create a polygon
		Polygon poly = new Polygon();
		for (GeoPosition gp : region) {
			// convert geo to world bitmap pixel
			Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
			poly.addPoint((int) pt.getX(), (int) pt.getY());
		}

		// do the drawing
		g.setColor(new Color(255, 0, 0, 100));
		g.fill(poly);
		g.setColor(Color.RED);
		g.draw(poly);

		g.dispose();
	}

}
