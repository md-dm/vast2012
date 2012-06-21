/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

import com.md.dm.infovis.vast.map.PieWaypoint.Slice;

/**
 * @author diego
 * 
 */
public class PieChartWaypointRenderer implements WaypointRenderer {

	@Override
	public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {

		PieWaypoint pieWaypoint = (PieWaypoint) wp;

		drawPie((Graphics2D) g, map.getZoom(), pieWaypoint.getSlices(), pieWaypoint.getLabel());

		return true;
	}

	private void drawPie(Graphics2D g, int zoom, List<Slice> slices, String label) {
		double total = 0.0D;

		for (int i = 0; i < slices.size(); i++) {
			total += slices.get(i).value;
		}
		// System.out.println(total);
		int ratio = 1;// branchs
		if (total > 100) {
			ratio = 2;// small region headquarters
		}
		if (total > 5000) {
			ratio = 4;// small regions
		}
		if (total > 15000) {
			ratio = 6;// headquarters
		}
		if (total > 20000) {
			ratio = 7;// large regions, and large regions headquartes
		}
		if (total > 50000) {
			ratio = 10;// datacenters headquarters
		}
		
		int width = (15 - (zoom - 1)) * 4 * ratio;
		int height = (15 - (zoom - 1)) * 4 * ratio;
		Rectangle area = new Rectangle(-1 * width / 2, -1 * height / 2, width, height);

		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices.get(i).value * 360 / total);

			g.setColor(slices.get(i).color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices.get(i).value;
		}

		if (zoom <= 10) {
			g.setColor(Color.DARK_GRAY);
			g.drawString(label + "-#" + (int)total, -1 * width / 2, height / 2 + g.getFontMetrics().getHeight());
		}
	}

}
