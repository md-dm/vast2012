package com.md.dm.infovis.vast.controller;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class MapKitController {

	private JXMapKit mapKit;

	public MapKitController(JXMapKit mapKit) {
		super();
		this.mapKit = mapKit;
	}

	public MapKitController() {
		mapKit = new JXMapKit();
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
	}

	public void addWaypoints() {

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
				dialog.add(new MapKitController().getMapKit(),
						BorderLayout.CENTER);

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
