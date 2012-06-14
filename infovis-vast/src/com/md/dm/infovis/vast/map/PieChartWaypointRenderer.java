/**
 * 
 */
package com.md.dm.infovis.vast.map;

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

		drawPie((Graphics2D) g,
				new Rectangle(-10, -10, map.getZoom() * 4, map.getZoom() * 4),
				pieWaypoint.getSlices());

		return true;
	}

	private void drawPie(Graphics2D g, Rectangle area, List<Slice> slices) {
		double total = 0.0D;
		
		for (int i = 0; i < slices.size(); i++) {
			total += slices.get(i).value;
		}

		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices.get(i).value * 360 / total);

			g.setColor(slices.get(i).color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle,
					arcAngle);
			curValue += slices.get(i).value;
		}
	}

}
