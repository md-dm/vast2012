/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

/**
 * @author diego
 * 
 */
public class PieChartWaypointRenderer implements WaypointRenderer {

	private Slice[] slices = { new Slice(5, Color.black),
			new Slice(33, Color.green), new Slice(20, Color.yellow),
			new Slice(15, Color.red) };

	public PieChartWaypointRenderer() {
	}
	
	public PieChartWaypointRenderer(Slice[] slices) {
		super();
		this.slices = slices;
	}

	@Override
	public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {

		drawPie((Graphics2D) g, new Rectangle(-10, -10, 8, 8), slices);

		return true;
	}

	private void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		double total = 0.0D;
		for (int i = 0; i < slices.length; i++) {
			total += slices[i].value;
		}

		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.length; i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices[i].value * 360 / total);

			g.setColor(slices[i].color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle,
					arcAngle);
			curValue += slices[i].value;
		}
	}

}

class Slice {
	double value;
	Color color;

	public Slice(double value, Color color) {
		this.value = value;
		this.color = color;
	}
}