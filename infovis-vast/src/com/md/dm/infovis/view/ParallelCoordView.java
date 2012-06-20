package com.md.dm.infovis.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import jp.jasp.jasplot.CSVDataModel;
import jp.jasp.jasplot.DataModel;
import jp.jasp.jasplot.HistogramPlotModel;
import jp.jasp.jasplot.JasplotPanel;

public class ParallelCoordView extends JPanel {
	
	public ParallelCoordView() {
		initComponents();
	}

	private void initComponents() {
		 DataModel dataModel = new CSVDataModel("resources/meta.csv");
		 HistogramPlotModel model = new HistogramPlotModel(dataModel);
	     JasplotPanel jasplot = new JasplotPanel(model);

	     setLayout(new BorderLayout());
	     add(jasplot, BorderLayout.CENTER);
	}

}
