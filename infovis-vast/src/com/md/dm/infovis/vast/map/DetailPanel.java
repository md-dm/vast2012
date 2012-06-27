/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.md.dm.infovis.vast.map;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.mongodb.DBObject;

/**
 * 
 * @author diego
 */
public class DetailPanel extends javax.swing.JPanel {

	private float tran = 0.8f;
	private DBObject dbObject;

	/**
	 * Creates new form DetailPanel
	 */
	public DetailPanel() {
		initComponents();
	}

	public float getTran() {
		return tran;
	}

	public void setTran(float tran) {
		this.tran = tran;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AlphaComposite old = (AlphaComposite) g2.getComposite();
		g2.setComposite(AlphaComposite.SrcOver.derive(getTran()));
		super.paintComponent(g);
		g2.setComposite(old);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		detailLabel = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		detailsButton = new javax.swing.JButton();
		positionButton = new javax.swing.JButton();

		setLayout(new java.awt.BorderLayout());

		detailLabel.setText("......");
		add(detailLabel, java.awt.BorderLayout.CENTER);

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

		detailsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/md/dm/infovis/vast/map/details.jpeg"))); // NOI18N
		detailsButton.setPreferredSize(new java.awt.Dimension(40, 40));
		detailsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				detailsButtonActionPerformed(evt);
			}
		});
		jPanel1.add(detailsButton);

		positionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/md/dm/infovis/vast/map/maparrow.png"))); // NOI18N
		positionButton.setPreferredSize(new java.awt.Dimension(40, 40));
		jPanel1.add(positionButton);

		add(jPanel1, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>//GEN-END:initComponents

	private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_detailsButtonActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_detailsButtonActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel detailLabel;
	private javax.swing.JButton detailsButton;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JButton positionButton;

	// End of variables declaration//GEN-END:variables

	/**
	 * @return the detailLabel
	 */
	public javax.swing.JLabel getDetailLabel() {
		return detailLabel;
	}

	/**
	 * @param detailLabel
	 *            the detailLabel to set
	 */
	public void setDetailLabel(javax.swing.JLabel detailLabel) {
		this.detailLabel = detailLabel;
	}

	/**
	 * @return the detailsButton
	 */
	public javax.swing.JButton getDetailsButton() {
		return detailsButton;
	}

	/**
	 * @param detailsButton
	 *            the detailsButton to set
	 */
	public void setDetailsButton(javax.swing.JButton detailsButton) {
		this.detailsButton = detailsButton;
	}

	/**
	 * @return the positionButton
	 */
	public javax.swing.JButton getPositionButton() {
		return positionButton;
	}

	/**
	 * @param positionButton
	 *            the positionButton to set
	 */
	public void setPositionButton(javax.swing.JButton positionButton) {
		this.positionButton = positionButton;
	}

	public void setDBObject(DBObject dbObject) {
		this.dbObject = dbObject;
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		buffer.append("<b>BussinesUnit: </b>");
		buffer.append(dbObject.get("bussinesUnit"));
		buffer.append("<BR />");
		buffer.append("<b>Facility: </b>");
		buffer.append(dbObject.get("facility"));
		buffer.append("<BR />");
		buffer.append("<b>Atms: </b>");
		buffer.append(dbObject.get("atm"));
		buffer.append("<BR />");
		buffer.append("<b>Severs: </b>");
		buffer.append(dbObject.get("server"));
		buffer.append("<BR />");
		buffer.append("<b>Workstations: </b>");
		buffer.append(dbObject.get("workstation"));
		buffer.append("<BR />");
		buffer.append("</html>");

		this.getDetailLabel().setText(buffer.toString());

	}
}
