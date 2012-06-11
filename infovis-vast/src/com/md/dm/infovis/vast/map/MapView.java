/*
 * Created by JFormDesigner on Sun Jun 10 22:14:44 GMT-03:00 2012
 */

package com.md.dm.infovis.vast.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 * @author Diego Correa
 */
public class MapView extends JPanel {
	
	private JXMapKit jXMapKit;
	
	public MapView() {
		initComponents();
		afterInitComponents();
	}

	private void afterInitComponents() {
		
		jXMapKit = new JXMapKit();
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

		panel1.add(jXMapKit, BorderLayout.CENTER);
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Diego Correa
		panel1 = new JPanel();
		panel2 = new JPanel();
		checkBox2 = new JCheckBox();
		checkBox3 = new JCheckBox();
		checkBox4 = new JCheckBox();
		checkBox5 = new JCheckBox();
		checkBox6 = new JCheckBox();
		panel3 = new JPanel();
		checkBox7 = new JCheckBox();
		checkBox8 = new JCheckBox();
		checkBox9 = new JCheckBox();
		checkBox10 = new JCheckBox();
		checkBox11 = new JCheckBox();
		scrollPane1 = new JScrollPane();
		tree1 = new JTree();
		progressBar1 = new JProgressBar();
		slider1 = new JSlider();
		panel4 = new JPanel();
		comboBox1 = new JComboBox();
		toggleButton1 = new JToggleButton();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


		//======== panel1 ========
		{
			panel1.setBackground(new Color(100, 238, 238));
			panel1.setLayout(new BorderLayout());
		}

		//======== panel2 ========
		{
			panel2.setBorder(new TitledBorder("Policy Status"));
			panel2.setLayout(new GridLayout(5, 0));

			//---- checkBox2 ----
			checkBox2.setText("Healthy");
			panel2.add(checkBox2);

			//---- checkBox3 ----
			checkBox3.setText("Moderate");
			panel2.add(checkBox3);

			//---- checkBox4 ----
			checkBox4.setText("Non normal");
			panel2.add(checkBox4);

			//---- checkBox5 ----
			checkBox5.setText("Critical");
			panel2.add(checkBox5);

			//---- checkBox6 ----
			checkBox6.setText("Infected");
			panel2.add(checkBox6);
		}

		//======== panel3 ========
		{
			panel3.setBorder(new TitledBorder("Activity Flag"));
			panel3.setLayout(new GridLayout(5, 1));

			//---- checkBox7 ----
			checkBox7.setText("Normal");
			panel3.add(checkBox7);

			//---- checkBox8 ----
			checkBox8.setText("Going down");
			panel3.add(checkBox8);

			//---- checkBox9 ----
			checkBox9.setText("5 invalid login");
			panel3.add(checkBox9);

			//---- checkBox10 ----
			checkBox10.setText("CPU fully consumed");
			panel3.add(checkBox10);

			//---- checkBox11 ----
			checkBox11.setText("Device added");
			panel3.add(checkBox11);
		}

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(tree1);
		}

		//======== panel4 ========
		{
			panel4.setBorder(new TitledBorder("# Connections"));
			panel4.setLayout(new GridLayout(1, 1));
			panel4.add(comboBox1);
		}

		//---- toggleButton1 ----
		toggleButton1.setText("play");

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
									.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(layout.createSequentialGroup()
								.addComponent(panel4, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup()
							.addComponent(progressBar1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGap(11, 11, 11)))
					.addGroup(layout.createParallelGroup()
						.addComponent(slider1, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton1)
						.addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(33, 33, 33))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(panel4, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(progressBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
							.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(slider1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(toggleButton1)
							.addGap(0, 7, Short.MAX_VALUE))
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
					.addGap(20, 20, 20))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Diego Correa
	private JPanel panel1;
	private JPanel panel2;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JCheckBox checkBox6;
	private JPanel panel3;
	private JCheckBox checkBox7;
	private JCheckBox checkBox8;
	private JCheckBox checkBox9;
	private JCheckBox checkBox10;
	private JCheckBox checkBox11;
	private JScrollPane scrollPane1;
	private JTree tree1;
	private JProgressBar progressBar1;
	private JSlider slider1;
	private JPanel panel4;
	private JComboBox comboBox1;
	private JToggleButton toggleButton1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
