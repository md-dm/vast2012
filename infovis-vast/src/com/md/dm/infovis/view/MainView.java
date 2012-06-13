package com.md.dm.infovis.view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class MainView extends JPanel {
	
	
	public MainView() {
		initComponents();
		afterInitComponents();
	}

	private void afterInitComponents() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		JPanel westPanel = new JPanel(new BorderLayout()); 
		JPanel eastPanel = new JPanel(new BorderLayout()); 
		JPanel northPanel = new JPanel(new BorderLayout()); 
		JPanel southPanel = new JPanel(new BorderLayout()); 
		JPanel centerPanel = new JPanel(new BorderLayout()); 
		
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.add(initMap(), BorderLayout.CENTER);
		westPanel.add(initFilterPanel(), BorderLayout.CENTER);
		
	}
	
	private JPanel initMap(){
		
		JXMapKit jXMapKit = new JXMapKit();
		jXMapKit.setDefaultProvider(org.jdesktop.swingx.JXMapKit.DefaultProviders.OpenStreetMaps);
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
		return jXMapKit;
	}
	
	private JPanel initFilterPanel(){
		
		JPanel filterPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(filterPanel, WIDTH);
		filterPanel.setLayout(boxLayout);
		
		JPanel policyStatusPanel = new JPanel();
		policyStatusPanel.setBorder(new TitledBorder("Policy Status"));
		policyStatusPanel.setLayout(new GridLayout(5, 0));
		Checkbox healthy = new Checkbox("Healthy");
		Checkbox moderate = new Checkbox("Moderate");
		Checkbox nonNormal = new Checkbox("Non normal");
		Checkbox critical = new Checkbox("Critical");
		Checkbox infected = new Checkbox("Infected");
		
		policyStatusPanel.add(healthy);		
		policyStatusPanel.add(moderate);		
		policyStatusPanel.add(nonNormal);		
		policyStatusPanel.add(critical);		
		policyStatusPanel.add(infected);
		
		filterPanel.add(policyStatusPanel);
		
		JPanel activityFlagPanel = new JPanel();
		activityFlagPanel.setBorder(new TitledBorder("Activity Flag"));
		activityFlagPanel.setLayout(new GridLayout(5, 0));
		Checkbox normal = new Checkbox("Normal");
		Checkbox goingDown = new Checkbox("Going down");
		Checkbox invalidLogin = new Checkbox("5 invalid login");
		Checkbox fullyConsumed = new Checkbox("CPU fully consumed");
		Checkbox deviceAdded = new Checkbox("Device added");
		
		activityFlagPanel.add(normal);		
		activityFlagPanel.add(goingDown);		
		activityFlagPanel.add(invalidLogin);		
		activityFlagPanel.add(fullyConsumed);		
		activityFlagPanel.add(deviceAdded);
		
		filterPanel.add(activityFlagPanel);
		
		JPanel numConnectionsPanel = new JPanel();
		numConnectionsPanel.setBorder(new TitledBorder("# Connections"));
		numConnectionsPanel.setLayout(new GridLayout(1, 1));
		String[] options = {"0-20", "20-50", "more than 50"};
		JComboBox numConnectionsComboBox = new JComboBox(options);
		numConnectionsPanel.add(numConnectionsComboBox);
		
		filterPanel.add(numConnectionsPanel);

		return filterPanel;
	}

}
