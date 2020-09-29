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

		jmolPanel.setPreferredSize(new Dimension(800, 800));
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
			viewer.renderScreenImage(g, 800, 800);
		}
	}

	public void setStructure(String filename) {

		JmolViewer viewer = jmolPanel.getViewer();
		viewer.openFile(filename);

		// send PDB file to Jmol
		viewer.openStringInline(filename);
		viewer.evalString("restrict *.ca; hide waters ;spacefill on, backbone 0.8");
		viewer.evalString("select :A ; color gold; define mol1 selected");
		viewer.evalString("select :B ; color LightSeaGreen; define mol2 selected");
//		viewer.evalString("select within (10.0, :X) and not mol1; color Bisque; define zone selected");
//		viewer.evalString("select within (10.0, :Y) and not mol2; color Bisque; define zone selected");

		viewer.evalString("select ser44,pro45,thr46,arg47,lys71,asp73,leu74,tyr75,asn76,lys77,leu102,phe103,pro134,pro135,leu136,asp137,thr185,phe186,ser187,val188,pro189,ser44,pro45,thr46,arg47,lys71,asp73,leu74,tyr75,asn76,lys77,leu102,phe103,pro134,pro135,leu136,asp137,thr185,phe186,ser187,val188,pro189; color red; define t1 selected");
//		viewer.evalString("select gln236,leu237,ser238,asp239,ile240,ala241,tyr242,val249,tyr261,phe296,ala297,lys298,asn299,thr300,pro209,val233,gly235,trp243,glu259,asp260,tyr262,ser263,thr274,leu275,ile276,thr277,ile303,val264,glu265,lys270,arg271,ser273,his301,pro206,thr207,arg208,arg272,asn204,lys114,leu115,pro116,leu201,glu203,lys205,lys112,gln113,ile199,thr200,glu202,phe111,phe111,gly121,gly122,leu123,val124,pro126,phe198,asp120,cys125,arg163,ile165,val117,gly119,val160,lys161,asp162,leu164,val166,met167,val169,tyr127,met128,glu129,gly159,ile196,glu197,ile110,tyr177,cys179,arg194,val195,ile13,leu15,lys12,asn30,glu11,ile14,pro28,leu29,arg9,glu10,pro31,leu88,lys7,glu8,pro26,cys27,asn32,glu33,leu88,arg89,ile90,lys91,ile92,ser93,val16,arg25,ala109,phe130,ser17,gln108,thr193,ala94,lys95,ala107,ser18,phe96,val97,tyr105,asn106,asn176,thr178,gly302,asp304,val210,ala305,ser248,ala118,asn168,ala170,phe131,lys132,asn136,glu137,leu138,val24,lys63,his34,asn269; color bisque; define t2 selected");
//		viewer.evalString("select t1 and not mol1");
//		viewer.evalString("select t2 and not mol2");
//		viewer.evalString("display mol2; color LightSeaGreen");
		// viewer.evalString("display mol1 and zone or mol2 and zone");
		this.viewer = viewer;
	}

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

	// TODO
	private String selectResidues(List<Residue> residues, String color, String selectionName) {

		String res = null;
		for (Residue residue : residues) {
			res = residue.getResidueName().toLowerCase() + residue.getResidueNumber() + ",";
		}
		return "select " + res + "; color " + color + "; define " + selectionName + "selected";

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

		String cyto1 = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/cytokins/3og4.cif";
		FileReader pdb = new FileReader(cyto1);

		// à lancer dans Application
		JmolIntegration ex = new JmolIntegration();
		ex.setStructure(cyto1);

	}
}
