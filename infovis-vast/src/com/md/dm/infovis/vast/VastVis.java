/**
 * 
 */
package com.md.dm.infovis.vast;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;
import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.Painter;

public class VastVis extends javax.swing.JDialog implements ActionListener {

	// Variables declaration - do not modify
	private javax.swing.JLabel titleLbl;
	private javax.swing.JPanel buttonPnl;
	private javax.swing.JButton closeBtn;
	private javax.swing.JLabel dummyLbl;
	private javax.swing.JPanel mapContainer;
	private javax.swing.JPanel titlePnl;
	private javax.swing.JLabel usernameLbl;

	private JXMapKit jXMapKit;

	// End of variables declaration

	/** Creates new form AddServiceDlg */
	public VastVis(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** Creates new form AddServiceDlg */
	public VastVis() {
		setModal(true);
		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		titlePnl = new javax.swing.JPanel();
		titleLbl = new javax.swing.JLabel();
		buttonPnl = new javax.swing.JPanel();
		closeBtn = new javax.swing.JButton();
		dummyLbl = new javax.swing.JLabel();
		mapContainer = new javax.swing.JPanel();
		usernameLbl = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridBagLayout());

		titlePnl.setLayout(new java.awt.GridBagLayout());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
		titlePnl.add(titleLbl, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
		gridBagConstraints.weightx = 1.0;
		getContentPane().add(titlePnl, gridBagConstraints);

		buttonPnl.setLayout(new java.awt.GridBagLayout());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		buttonPnl.add(closeBtn, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
		gridBagConstraints.weightx = 1.0;
		buttonPnl.add(dummyLbl, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
		gridBagConstraints.weightx = 1.0;
		getContentPane().add(buttonPnl, gridBagConstraints);

		mapContainer.setLayout(new java.awt.GridBagLayout());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 7, 0, 7);
		mapContainer.add(usernameLbl, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		getContentPane().add(mapContainer, gridBagConstraints);

		addCustomAttributes();
		registerEventListeners();
		initializeMapKit();

		this.addWaypoints();

		Set<Waypoint> waypoints = new HashSet<Waypoint>();
		waypoints.add(new Waypoint(0.8173707995821654, -70.25979476999538));
		WaypointPainter headQuarterPainter = new WaypointPainter();
		headQuarterPainter.setWaypoints(waypoints);
		headQuarterPainter.setRenderer(new WaypointRenderer() {

			public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
				g.setColor(Color.BLUE);
				g.fillRoundRect(-10, -10, 20, 20, 10, 10);
				return true;
			}
		});

		waypoints = new HashSet<Waypoint>();
		Random rn = new Random();
		for (int i = 0; i < 4000; i++) {
			
			double latitude = (rn.nextDouble() * -180.0) + 90.0;
            double longitude = (rn.nextDouble() * -360.0) + 180.0;
			waypoints.add(new Waypoint(latitude, longitude));
		}

//		waypoints.add(new Waypoint(36.03496231160826, -144.7927409811527));
//		waypoints.add(new Waypoint(-14.54241549107048, -122.5632977377749));
//		waypoints.add(new Waypoint(0.951054642854049, -70.41285490045435));
//		waypoints.add(new Waypoint(66.63569791116203, -100.5573370014041));
		
		
		WaypointPainter datacenterPainter = new WaypointPainter();
		datacenterPainter.setWaypoints(waypoints);
		datacenterPainter.setRenderer(new WaypointRenderer() {

			public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
				
				Slice[] slices = { new Slice(5, Color.black), new Slice(33, Color.green),
						new Slice(20, Color.yellow), new Slice(15, Color.red) };
				
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
					g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
					curValue += slices[i].value;
				}
			}

		});

		final List<GeoPosition> region = new ArrayList<GeoPosition>();
		region.add(new GeoPosition(77.68970041647046, -161.2380960077794));
		region.add(new GeoPosition(77.68970041647046, -45.65515496228814));
		region.add(new GeoPosition(-20.79269625176585, -45.65515496228814));
		region.add(new GeoPosition(-20.79269625176585, -161.2380960077794));

		Painter<JXMapViewer> polygonOverlay = new Painter<JXMapViewer>() {

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

		};

		final List<GeoPosition> region1 = new ArrayList<GeoPosition>();
		region1.add(new GeoPosition(55, -143));
		region1.add(new GeoPosition(55, -134));
		region1.add(new GeoPosition(50, -134));
		region1.add(new GeoPosition(50, -143));

		Painter<JXMapViewer> region1Overlay = new Painter<JXMapViewer>() {

			public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
				g = (Graphics2D) g.create();
				// convert from viewport to world bitmap
				Rectangle rect = map.getViewportBounds();
				g.translate(-rect.x, -rect.y);

				// create a polygon
				Polygon poly = new Polygon();
				for (GeoPosition gp : region1) {
					// convert geo to world bitmap pixel
					Point2D pt = map.getTileFactory().geoToPixel(gp, map.getZoom());
					poly.addPoint((int) pt.getX(), (int) pt.getY());
				}

				// do the drawing
				g.setColor(new Color(255, 0, 0, 100));
				g.fill(poly);
				g.setColor(Color.LIGHT_GRAY);
				g.draw(poly);

				g.dispose();
			}

		};

		CompoundPainter compoundPainter = new CompoundPainter(headQuarterPainter, datacenterPainter, polygonOverlay, region1Overlay);
		compoundPainter.setCacheable(false);

		jXMapKit.getMainMap().setOverlayPainter(compoundPainter);

		setSize(800, 600);
		setLocationRelativeTo(null);
		System.out.println(jXMapKit.getMainMap().getViewportBounds());
	}

	public void addCustomAttributes() {
		closeBtn.setText("Close");
		titleLbl.setText("OpenStreet Maps");
		titlePnl.setBackground(Color.WHITE);
		titleLbl.setFont(new Font("Arial", Font.BOLD, 11));
		titleLbl.setForeground(new Color(65, 94, 117));
	}

	public void initializeMapKit() {
		jXMapKit = new JXMapKit();

		JPanel somePanel = new JPanel();
		somePanel.add(new JButton("Locuras !"));
		somePanel.add(new JCheckBox("Normal"));
		somePanel.add(new JCheckBox("Normal"));
		somePanel.add(new JCheckBox("Normal"));
		somePanel.setSize(50, 20);
		somePanel.setBackground(Color.CYAN);
		somePanel.addMouseMotionListener(new MyMotionListener(somePanel));
		mapContainer.add(somePanel);

		jXMapKit.setDefaultProvider(org.jdesktop.swingx.JXMapKit.DefaultProviders.OpenStreetMaps);

		TileFactoryInfo info = new TileFactoryInfo(0, // min level
				8, // max allowed level
				8, // max level
				256, // tile size
				true, true, // x/y orientation is normal
				"file:/Users/diego/Documents/Maestria/VI/vast2012/tiles/", // base
																				// url
				"x", "y", "z" // url args for x, y &amp; z
		) {
			public String getTileUrl(int x, int y, int zoom) {

				int z = this.getMaximumZoomLevel();
				double limit = Math.pow(2, z);

				System.out.println(this.baseURL + (z - zoom) + "/" + x + "/" + y + ".png");

				//return "http://www.maptiler.org/img/none.png";

				 if (y < 0 || y >= limit) {
					 	return "http://www.maptiler.org/img/none.png";
				 } else {
				 return this.baseURL + (z - zoom) + "/" + x + "/" + y + ".png";
				 }
			}
		};

		//jXMapKit.setTileFactory(new DefaultTileFactory(info));

		jXMapKit.setAddressLocationShown(false);
		jXMapKit.setDataProviderCreditShown(false);
		//jXMapKit.setCenterPosition(new GeoPosition(0, 0));
		jXMapKit.setMiniMapVisible(true);
		jXMapKit.setZoomSliderVisible(true);
		jXMapKit.setZoomButtonsVisible(true);
		jXMapKit.setAddressLocationShown(true);
		jXMapKit.setCenterPosition(new GeoPosition( 45, -90));
		//jXMapKit.setAddressLocation(new GeoPosition(1, 1));

		jXMapKit.setZoom(8);
		((DefaultTileFactory) jXMapKit.getMainMap().getTileFactory()).setThreadPoolSize(8);

		
		
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		mapContainer.add(jXMapKit, gridBagConstraints);
	}

	public void addWaypoints() {
		Set waypoints = new HashSet();
		WaypointPainter painter = new WaypointPainter();
		painter.setWaypoints(waypoints);

		painter.setRenderer(new WaypointRenderer() {
			@Override
			public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
				// Image markerImg = new
				// ImageIcon("image/large_building.png").getImage();
				// g.drawImage(markerImg, -2, -2, null);

				
				g.setColor(Color.BLUE);
				g.fillRoundRect(-10, -10, 5*map.getZoom(), 5*map.getZoom(), 10, 10);

				return true;
			}

		});

		jXMapKit.getMainMap().setOverlayPainter(painter);
		// put your action code here
	}

	public void registerEventListeners() {
		closeBtn.addActionListener(this);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VastVis dialog = new VastVis(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closeBtn) {
			dispose();
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
	public class MyMotionListener implements MouseMotionListener {
	    private JPanel movingPanel;
	    private Point pt;
	    public MyMotionListener (JPanel movingPanel) {
	        this.movingPanel = movingPanel;
	    }
	    @Override
	    public void mouseDragged(MouseEvent e) {
	        pt = SwingUtilities.convertPoint(movingPanel, e.getX(), e.getY(), movingPanel.getParent());
	        movingPanel.setBounds(pt.x, pt.y, movingPanel.getWidth(), movingPanel.getHeight());
	    }
	     
	    @Override
	    public void mouseMoved(MouseEvent e) {
	    }
	}
}

