package fr.karinedias.visualisation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolAdapter;
import org.jmol.api.JmolViewer;

import fr.karinedias.model.Chain;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;
import fr.karinedias.utils.FileReader;

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

	public void setStructure(String filename) {

		JmolViewer viewer = jmolPanel.getViewer();
		viewer.openFile(filename);

		// send PDB file to Jmol
		viewer.openStringInline(filename);
		viewer.evalString("restrict *.ca; hide waters ;spacefill on, backbone 0.8");
		viewer.evalString("select :A or : C ; color gold; define mol1 selected");
		viewer.evalString("select :B or :D ; color LightSeaGreen; define mol2 selected");
//		viewer.evalString("select within (10.0, :X) and not mol1; color Bisque; define zone selected");
//		viewer.evalString("select within (10.0, :Y) and not mol2; color Bisque; define zone selected");

		 viewer.evalString("select atomno=2,atomno=16,atomno=25,atomno=186,atomno=194,atomno=203,atomno=211,atomno=218,atomno=222,atomno=231,atomno=303,atomno=310,atomno=319,atomno=323,atomno=330,atomno=337,atomno=343,atomno=350,atomno=355,atomno=363,atomno=374,atomno=383,atomno=391,atomno=392,atomno=400,atomno=408,atomno=416,atomno=425,atomno=465,atomno=512,atomno=528,atomno=543,atomno=553,atomno=560,atomno=632,atomno=913,atomno=918,atomno=935,atomno=945,atomno=951,atomno=961,atomno=972,atomno=1096,atomno=1180,atomno=1191,atomno=1196,atomno=1207,atomno=1211,atomno=1218,atomno=1225,atomno=1243,atomno=1250,atomno=1274,atomno=1275,atomno=1462,atomno=2193,atomno=2221,atomno=2229,atomno=2235,atomno=2240,atomno=2249; color red; define t1 selected");

		//viewer.evalString("select atomno=1370,atomno=1379,atomno=1386,atomno=1398,atomno=1402,atomno=1411,atomno=1422,atomno=1617,atomno=1624,atomno=1633,atomno=1641,atomno=1653,atomno=1779,atomno=1787,atomno=1809,atomno=1810,atomno=1832,atomno=2150,atomno=2492,atomno=2499,atomno=2506,atomno=2513,atomno=2527,atomno=2532; color FireBrick; define t1 selected");
		//viewer.evalString(
		//		"select atomno=1430,atomno=1436,atomno=1443,atomno=1450,atomno=1649,atomno=1667,atomno=1675,atomno=1683,atomno=1695,atomno=1703,atomno=1909,atomno=1917,atomno=2154,atomno=2161,atomno=2168,atomno=2176,atomno=2543,atomno=2550,atomno=2561,atomno=2567,atomno=2574; color red; define t2 selected");
//		viewer.evalString("select t1 and not mol1");
//		viewer.evalString("select t2 and not mol2");
//		viewer.evalString("display mol2; color LightSeaGreen");
		// viewer.evalString("display mol1 and zone or mol2 and zone");
		this.viewer = viewer;
	}
	
//	public void setStructure2(String filename, List<Character> chains, String objCommand) {
//
//		JmolViewer viewer = jmolPanel.getViewer();
//		viewer.openFile(filename);
//
//		// send PDB file to Jmol
//		viewer.openStringInline(filename);
//		viewer.evalString("restrict *.ca; hide waters ;spacefill on, backbone 0.8");
//		viewer.evalString(selectChain(chains, ))
//		this.viewer = viewer;
//	}
	

	private String selectChain(List<Character> chains, String color, String selectionName) {

		String command = "";

		if (chains.size() > 1) {
			command = "select ";
			for (Character character : chains) {
				command += ":" + character + " or ";
			}

			// Remove the last "or" of the command
			if (command != null && command.length() > 0 && command.charAt(command.length() - 2) == 'r'
					&& command.charAt(command.length() - 3) == 'o') {
				command = command.substring(0, command.length() - 3) + "; color " + color + "; define " + selectionName
						+ " selected";
			}
		} else
			command = "select :" + chains.get(0) + "; color " + color + "; define " + selectionName + " selected";
		
		return command;
	}

	private String selectObjects(int nInteractions, String objects, String color, String selectionName) {

		String command = "";

		if (nInteractions > 1) {
			command = "select " + objects + "; color " + color + "; define " + selectionName + " selected";
		} else {
			command = "select :" + objects + "; color " + color + "; define " + selectionName + " selected";
		}
		return command;
	}

	public static void main(String[] args) {
//		├── 3alq.cif -> compliquée, 12 chaines ?
//		├── 3dlq.cif
//		├── 3g9v.cif
//		├── 3og4.cif
//		├── 3og6.cif
//		├── 3qb7.cif
//		├── 3va2.cif
//		├── 3va2.pdb
//		├── 4nkq.cif
//		├── 4nn6.cif
//		└── 4wrl.cif

		String cyto1 = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/3tnw.cif";
		FileReader pdb = new FileReader(cyto1);

		// à lancer dans Application
		JmolIntegration ex = new JmolIntegration();
		ex.setStructure(cyto1);

	}
}
