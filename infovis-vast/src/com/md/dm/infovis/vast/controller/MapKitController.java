package com.md.dm.infovis.vast.controller;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.Painter;

import com.md.dm.infovis.vast.map.PieChartWaipontPainter;
import com.md.dm.infovis.vast.map.PolygonPainter;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MapKitController {

	private JXMapKit mapKit;

	// TODO: Remove this and inject
	private DataController dataController;

	public MapKitController(JXMapKit mapKit) throws Exception {
		super();
		this.mapKit = mapKit;
		dataController = new DataController();
	}

	public MapKitController() throws Exception {
		mapKit = new JXMapKit();
		dataController = new DataController();
		mapKit.setDefaultProvider(org.jdesktop.swingx.JXMapKit.DefaultProviders.OpenStreetMaps);
		mapKit.setAddressLocationShown(false);
		mapKit.setDataProviderCreditShown(false);
		// jXMapKit.setCenterPosition(new GeoPosition(0, 0));
		mapKit.setMiniMapVisible(true);
		mapKit.setZoomSliderVisible(true);
		mapKit.setZoomButtonsVisible(true);
		mapKit.setAddressLocationShown(true);
		mapKit.setCenterPosition(new GeoPosition(45, -90));
		// jXMapKit.setAddressLocation(new GeoPosition(1, 1));

		mapKit.setZoom(8);
		((DefaultTileFactory) mapKit.getMainMap().getTileFactory())
				.setThreadPoolSize(8);

		CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter(
				new PieChartWaipontPainter(dataController.filter("region-1", "branch1")), new PolygonPainter());
		
		compoundPainter.setCacheable(false);

		
		
		this.setOverlayPainter(compoundPainter);
	}

	public void addWaypoints() {

	}

	private void setOverlayPainter(Painter<JXMapViewer> painter) {
		mapKit.getMainMap().setOverlayPainter(painter);
	}

	public JXMapKit getMapKit() {
		return mapKit;
	}

	public void setMapKit(JXMapKit mapKit) {
		this.mapKit = mapKit;
	}

	@Override
	public String toString() {
		return "MapKitController [mapKit=" + mapKit + "]";
	}

	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JDialog dialog = new JDialog(new javax.swing.JFrame(), true);
				dialog.getContentPane().setLayout(new BorderLayout());
				try {
					dialog.add(new MapKitController().getMapKit(),
							BorderLayout.CENTER);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				dialog.setSize(600, 400);
				dialog.setLocationRelativeTo(null);

				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
}
