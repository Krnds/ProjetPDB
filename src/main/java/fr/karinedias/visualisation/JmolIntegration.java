package fr.karinedias.visualisation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;

public class JmolIntegration {
	JmolViewer viewer;

	JmolPanel jmolPanel;
	JFrame frame;

	public JmolIntegration() {
		frame = new JFrame();
		frame.addWindowListener(new ApplicationCloser());
		Container contentPane = frame.getContentPane();
		jmolPanel = new JmolPanel();

		jmolPanel.setPreferredSize(new Dimension(900, 900));
		contentPane.add(jmolPanel);

		frame.pack();
		frame.setVisible(true);

	}

	public void setTitle(String label) {
		frame.setTitle(label);
	}

	public JmolViewer getViewer() {
		return jmolPanel.getViewer();
	}

	static class ApplicationCloser extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	static class JmolPanel extends JPanel {
		private static final long serialVersionUID = -3661941083797644242L;
		JmolViewer viewer;
		JmolAdapter adapter;

		JmolPanel() {
			adapter = new SmarterJmolAdapter();
			viewer = JmolViewer.allocateViewer(this, adapter);

		}

		public JmolViewer getViewer() {
			return viewer;
		}

		public void executeCmd(String rasmolScript) {
			viewer.evalString(rasmolScript);
		}

		final Dimension currentSize = new Dimension();
		final Rectangle rectClip = new Rectangle();

		public void paint(Graphics g) {
			getSize(currentSize);
			g.getClipBounds(rectClip);
			viewer.renderScreenImage(g, 900, 900);
		}
	}

	public void setStructure(String filename, HashMap<Integer, List<Character>> info, String interactionZoneAtoms) {

		JmolViewer viewer = jmolPanel.getViewer();
		viewer.openFile(filename);

		// send PDB file to Jmol
		viewer.openStringInline(filename);
		viewer.evalString("restrict *.ca; hide waters ;spacefill on, backbone 0.8");

		// select and color chains of each molecule
		viewer.evalString(selectChain(info.get(1), "gold"));
		viewer.evalString(selectChain(info.get(2), "LightSeaGreen"));

		// select all atoms of interaction zone
		viewer.evalString(selectAtoms(interactionZoneAtoms, "red"));

		this.viewer = viewer;
	}

	// represents structure when there's no interactions displayed
	public void setStructureWithoutInteraction(String filename, HashMap<Integer, List<Character>> info) {

		JmolViewer viewer = jmolPanel.getViewer();
		viewer.openFile(filename);

		// send PDB file to Jmol
		viewer.openStringInline(filename);
		viewer.evalString("restrict *.ca; hide waters ;spacefill on, backbone 0.8");

		// select and color chains of each molecule
		viewer.evalString(selectChain(info.get(1), "gold"));
		viewer.evalString(selectChain(info.get(2), "LightSeaGreen"));

		this.viewer = viewer;
	}

	public String selectChain(List<Character> chains, String color) {

		String command = "";

		if (chains.size() > 1) {
			command = "select ";
			for (Character character : chains) {
				command += ":" + character + " or ";
			}

			// Remove the last "or" of the command
			if (command != null && command.length() > 0 && command.charAt(command.length() - 2) == 'r'
					&& command.charAt(command.length() - 3) == 'o') {
				command = command.substring(0, command.length() - 3) + "; color " + color;
			}
		} else
			command = "select :" + chains.get(0) + "; color " + color;

		return command;
	}

	public String selectAtoms(String atoms, String color) {

		String command = "select " + atoms.substring(0, atoms.length() - 1) + "; color " + color;
		System.out.println(command);
		return command;
	}

}
