/**
 * 
 */
package com.md.dm.infovis.vast.map;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author diego
 * 
 */
public class TransparentPanel extends JPanel {

	public TransparentPanel() {
		this.setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int x = 34;
		int y = 34;
		int w = getWidth() - 68;
		int h = getHeight() - 68;
		int arc = 30;

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(new Color(0, 0, 0, 220));
		g2.fillRoundRect(x, y, w, h, arc, arc);

		g2.setStroke(new BasicStroke(3f));
		g2.setColor(Color.WHITE);
		g2.drawRoundRect(x, y, w, h, arc, arc);

		g2.dispose();
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		TransparentPanel transparentPanel = new TransparentPanel();
		transparentPanel.setPreferredSize(new Dimension(200, 300));
		frame.getContentPane().add(transparentPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
