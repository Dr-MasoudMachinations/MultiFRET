package plugins.MasoudR.multifreticy.Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import icy.gui.frame.IcyInternalFrame;
import plugins.MasoudR.multifreticy.MultiFretIcy;

/* Workspace class holds mid-experiment images and graphs, creating an easy to oversee tab-based window */

@SuppressWarnings("serial")
public class Workspace extends JTabbedPane {
	private JPanel grid;
	private GridBagConstraints c;
	private int num = 1;
	private JScrollPane sp;
	private int comps = 0;
	private int graphs = 0;
	private int tab = 0;
	private int tablimit = MultiFretIcy.PS.tabLimit;

	private ArrayList<JPanel> gridList = new ArrayList<JPanel>();

	Workspace() {
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.addTab("tab" + num, growPanel());
	}

	// Not in use atm, but in the future split tabs when they reach ~25 or user
	// defined
	public void newTab() {
		this.addTab("tab" + num, growPanel());
	}

	private JPanel growPanel() {
		JPanel gp = new JPanel(false);
		gp.setLayout(new FlowLayout(FlowLayout.LEFT));
//    gp.setAlignmentX(Component.LEFT_ALIGNMENT);
		grid = new JPanel(new GridBagLayout()); // any number of rows, 2 columns, H and V gap
		grid.setAlignmentX(Component.LEFT_ALIGNMENT);
		gridList.add(grid);
		c = new GridBagConstraints();
		c.weightx = c.weighty = 1.0;
		sp = new JScrollPane(grid);
		sp.getVerticalScrollBar().setUnitIncrement(50);
		sp.setAlignmentX(Component.LEFT_ALIGNMENT);
		gp.add(sp, BorderLayout.CENTER);
//    f.pack();
		this.setVisible(true);
		return gp;
	}

	public void addComponent(int x, int y, Component comp) throws IOException {
		if (comps == tablimit) {
			num++;
			comps = 0;
			newTab();
		}
		c.gridx = x;
		c.gridy = y;
		grid.add(comp, c);
//      this.repaint();
		comps++;
	}

	public void addComponent(int x, int y, IcyInternalFrame comp) throws IOException {
		if (comps == tablimit) {
			num++;
			comps = 0;
			newTab();
		}
		c.gridx = x;
		c.gridy = y;
		grid.add(comp, c);
//      this.repaint();
		comps++;
	}

	public void addGraph(int x, int y, Component comp) throws IOException {
		if (graphs == tablimit) {
			graphs = 0;
			tab++;
		}
		c.gridx = x;
		c.gridy = y; // not working, graphs appear in order instead
		gridList.get(tab).add(comp, c);
		this.revalidate();
		this.repaint();
		graphs++;
	}

	public JScrollPane getScrollPane() {
		return (sp);
	}

	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
}