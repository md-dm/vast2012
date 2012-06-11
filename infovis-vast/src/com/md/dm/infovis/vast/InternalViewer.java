/**
 * 
 */
package com.md.dm.infovis.vast;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

import com.md.dm.infovis.vast.map.MapView;

/**
 * @author diego
 * 
 */
public class InternalViewer extends JInternalFrame {

	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;

	public InternalViewer() {
		super("Document #" + (++openFrameCount), true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		// ...Create the GUI and put it in the window...

		// ...Then set the window size or call pack...
		setSize(600, 600);
		setLayout(new BorderLayout());
		add(new MapView(), BorderLayout.CENTER);

		// Set the window's location.
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
	}
}
