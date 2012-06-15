package com.md.dm.infovis.view;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.md.dm.infovis.vast.controller.DataController;
import com.md.dm.infovis.vast.controller.MapKitController;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class MainView extends JPanel {

	private MapKitController mapKitController;
	private CheckboxTree checkboxTree;
	private DataController dataController;

	public MainView() {
		initComponents();
		afertInitComponents();
	}

	private void afertInitComponents() {
		try {
			dataController = new DataController();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		// centerPanel.add(initMap(), BorderLayout.CENTER);
		westPanel.add(initFilterPanel(), BorderLayout.CENTER);
		// eastPanel.add(initRegionPanel(), BorderLayout.CENTER);
		southPanel.add(initNavigationPanel(), BorderLayout.CENTER);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				initMap(), initRegionPanel());
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(1.0);
		centerPanel.add(splitPane, BorderLayout.CENTER);
	}

	private JPanel initMap() {

		try {
			mapKitController = new MapKitController();
			return mapKitController.getMapKit();

		} catch (Exception e) {
			// TODO: informar
			e.printStackTrace();
		}
		return null;
	}

	private JPanel initFilterPanel() {

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
		String[] options = { "0-20", "20-50", "more than 50" };
		JComboBox numConnectionsComboBox = new JComboBox(options);
		numConnectionsPanel.add(numConnectionsComboBox);

		filterPanel.add(numConnectionsPanel);

		return filterPanel;
	}

	private JPanel initRegionPanel() {

		JPanel regionPanel = new JPanel(new BorderLayout());

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Bank");
		DefaultMutableTreeNode headQuartersNode = new DefaultMutableTreeNode(
				"headquarters");
		rootNode.add(headQuartersNode);

		for (int i = 1; i <= 5; i++) {
			headQuartersNode.add(new DefaultMutableTreeNode("datacenter-" + i));
		}
		headQuartersNode.add(new DefaultMutableTreeNode("headquarters"));

		for (int i = 1; i <= 50; i++) {
			DefaultMutableTreeNode regionNode = new DefaultMutableTreeNode(
					"region-" + i);
			rootNode.add(regionNode);
			int branches = 50;
			if (i >= 1 && i <= 10) {
				branches = 200;
			}
			for (int j = 1; j <= branches; j++) {
				regionNode.add(new DefaultMutableTreeNode("branch" + j));
			}
			regionNode.add(new DefaultMutableTreeNode("headquarters"));
		}
		TreeModel treeModel = new DefaultTreeModel(rootNode);
		checkboxTree = new CheckboxTree(treeModel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(checkboxTree);

		regionPanel.add(scrollPane, BorderLayout.CENTER);

		return regionPanel;
	}

	private JPanel initNavigationPanel() {
		JPanel navigationPanel = new JPanel(new BorderLayout());

		JProgressBar progressBar = new JProgressBar();
		JSlider slider = new JSlider();
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showData();
			}
		});
		JToggleButton toggleButton = new JToggleButton("Play");

		JPanel controlPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(applyButton, BorderLayout.WEST);
		buttonPanel.add(toggleButton, BorderLayout.EAST);

		controlPanel.add(buttonPanel, BorderLayout.WEST);
		controlPanel.add(slider, BorderLayout.CENTER);

		navigationPanel.add(progressBar, BorderLayout.WEST);
		navigationPanel.add(controlPanel, BorderLayout.CENTER);

		return navigationPanel;
	}

	private void showData() {

		Set<String> businessUnits = new HashSet<String>();
		Set<String> facilities = new HashSet<String>();
		BasicDBObject key = new BasicDBObject();
		key.append("bussinesUnit", true);
		key.append("facility", true);
		key.append("location", true);

		QueryBuilder qb = new QueryBuilder();
		qb.put("statusList").notEquals(new ArrayList());

		TreeCheckingModel checkingModel = ((CheckboxTree) checkboxTree)
				.getCheckingModel();
		TreePath[] checkingPaths = checkingModel.getCheckingPaths();
		for (int i = 0; i < checkingPaths.length; i++) {
			boolean checked = checkingModel.isPathChecked(checkingPaths[i]);
			if (checked) {
				System.out.println(checkingPaths[i] + " " + checked);

				if (checkingPaths[i].getPathCount() > 1) {

					DefaultMutableTreeNode node = (DefaultMutableTreeNode) checkingPaths[i]
							.getPathComponent(1);
					String businessUnit = (String) node.getUserObject();
					businessUnits.add(businessUnit);

					if (checkingPaths[i].getPathCount() > 2) {
						DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) checkingPaths[i]
								.getPathComponent(2);
						String facility = (String) childNode.getUserObject();
						facilities.add(facility);
						qb.or(new BasicDBObject("bussinesUnit", businessUnit)
								.append("facility", facility));
					}
				}
			}
		}

		System.out.println(businessUnits);
		System.out.println(facilities);

		DBObject group = dataController.group(key, qb.get());
		mapKitController.showData(group);
	}

}
