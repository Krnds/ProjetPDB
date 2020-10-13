package fr.karinedias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import fr.karinedias.math.NeighborSearch;
import fr.karinedias.math.ResidueDistanceCalculation;
import fr.karinedias.model.Chain;
import fr.karinedias.model.Complex;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;
import fr.karinedias.query.FetchStructure;
import fr.karinedias.query.Query;
import fr.karinedias.utils.ChainParser;
import fr.karinedias.utils.FileReader;
import fr.karinedias.utils.MoleculeParser;
import fr.karinedias.utils.ResidueParser;
import fr.karinedias.visualisation.JmolIntegration;

public class Application {
	private static Scanner SC;

	public static void main(String[] args) throws IOException, URISyntaxException {
		menu();

	}

	public static void menu() throws IOException, URISyntaxException {

		int choice = 0;
		boolean retourMenu = true;

		System.out.println("----------Calcul des distances intermoléculaires dans la PDB----------\n");

		do {
			SC = new Scanner(System.in);
			System.out.println("MENU PRINCIPAL\n");
			System.out.println("[1]\tChercher des complexes moléculaires\n"
					+ "[2]\tCalcul de distance entre deux résidus d'un complexe choisi\n"
					+ "[3]\tListe des résidus situés à une distance inférieure à un seuil\n"
					+ "[4]\tDistances les plus faibles entre un résidu et ses voisins (avec des cytokines)\n"
					+ "[5]\tDéfinir une zone d'interaction et l'appliquer aux 10 cytokines\n" + "[6]\tQuitter");

			if (SC.hasNextInt()) {
				choice = SC.nextInt();
			}
			if (choice <= 0 || choice > 6) {
				System.out.println("Choix invalide");
			}
			switch (choice) {
			case 1:
				try {
					choice1();
					break;
				} catch (IOException e) {
					System.out.println("File not found");
					e.printStackTrace();
				}
			case 2:
				try {
					choice2();
					break;
				} catch (URISyntaxException e) {
					System.out.println("file not found");
					e.printStackTrace();
				}
			case 3:
				choice3();
				break;
			case 4:
				choice4();
				break;
			case 5:
				choice5();
				break;
			case 6:
				SC.close();
				retourMenu = false;
				System.exit(0);
				break;
			}

		} while (retourMenu);
		SC.close();
	}

	public static void choice1() throws IOException {
		boolean incorrect = false;
		long startTime = System.currentTimeMillis();
		System.out.println("\nRecherche du nombre de complexes moléculaires dans la PDB...");
		// Find all PDB complexes (2 or more entities)
		Query query = new Query();
		Query.postQuery();
		do {
			System.out.println(query.getMolecularComplexes().size()
					+ " complexes moléculaires ont été trouvés.\nImprimer la liste des identifiants ? [O/N]");
			String r = SC.next();
			if (r.contentEquals("O")) {
				System.out.println(query.getMolecularComplexes().toString());
				break;
			} else if (r.contentEquals("N")) {
				break;
			} else {
				incorrect = true;
				System.out.println("Entrée invalide");
			}
		} while (incorrect);
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		System.out.println("\nTemps d'éxécution " + elapsedTime + " secondes");
	}

	public static void choice2() throws URISyntaxException, IOException {
		boolean incorrect = false;
		long startTime = System.currentTimeMillis();

		System.out.println("\nCalcul de distance entre deux résidus d'un complexe...");
		do {
			System.out.println("Choisissez un complexe parmi ces identifiants :\n");
			System.out.println(
					"[1]\t1fn3\n[2]\t2b5i\n[3]\t2na8\n[4]\t1a5b\n[5]\t3dcu\n[6]\t3l3t\n[7]\t3qt2\n[8]\t3tnw\n[9]\t6rj4");
			int r = SC.nextInt();
			if (r < 1 || r > 9) {
				incorrect = true;
				System.out.println("Entrée invalide");
			} else {
				distancesFromRandomResidues("/data/" + complexes(r) + ".cif");
			}

		} while (incorrect);
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		System.out.println("\n\nTemps d'éxécution " + elapsedTime + " seconde(s)");
	}

	public static void choice3() throws IOException {
		boolean incorrect = false;
		System.out.println("Choisissez un complexe parmi ces identifiants :\n");
		System.out.println(
				"[1]\t1fn3\n[2]\t2b5i\n[3]\t2na8\n[4]\t1a5b\n[5]\t3dcu\n[6]\t3l3t\n[7]\t3qt2\n[8]\t3tnw\n[9]\t6rj4");
		int r = SC.nextInt();
		do {
			if (r < 1 || r > 9) {
				incorrect = true;
				System.out.println("Entrée invalide");
			} else {
				System.out.println("Entrez une distance (entier) :");
				int d = SC.nextInt();
				allResiduesFromDistance("/data/" + complexes(r) + ".cif", complexes(r), d);
			}

		} while (incorrect);
	}

	public static void choice4() throws IOException {
		boolean incorrect = false;
		System.out.println("Choisissez un complexe cytokine/recepteur parmi :\n");
		System.out.println(
				"[1]\t1ira\n[2]\t3alq\n[3]\t3dlq\n[4]\t3g9v\n[5]\t3og4\n[6]\t3og6\n[7]\t3qb7\n[8]\t3va2\n[9]\t2z3r\n[10]\t3tgx");
		int r = SC.nextInt();
		do {
			if (r < 1 || r > 10) {
				incorrect = true;
				System.out.println("Entrée invalide");
			} else {
				System.out.println("Entrez une distance (entier) :");
				int d = SC.nextInt();
				distancesFromResidue("/data/cytokines/" + cytokines(r) + ".cif", cytokines(r), d);
			}
		} while (incorrect);
	}

	public static void choice5() throws IOException, URISyntaxException {
		boolean incorrect = false;
		System.out.println("Choisissez un complexe cytokine/recepteur parmi :\n");
		System.out.println(
				"[1]\t1ira\n[2]\t3alq\n[3]\t3dlq\n[4]\t3g9v\n[5]\t3og4\n[6]\t3og6\n[7]\t3qb7\n[8]\t3va2\n[9]\t2z3r\n[10]\t3tgx");
		int r = SC.nextInt();
		do {
			if (r < 1 || r > 10) {
				incorrect = true;
				System.out.println("Entrée invalide");
			} else {
				System.out.println("Entrez une distance (entier) :");
				int d = SC.nextInt();
				representInteractions("/data/cytokines/" + cytokines(r) + ".cif", cytokines(r), d);
			}
		} while (incorrect);
	}

	private static void distancesFromRandomResidues(String structureFile) throws IOException {

		FileReader fr = new FileReader(structureFile);
		StringBuilder sb;
		sb = fr.readerJar();
		ResidueParser rp = new ResidueParser(sb);
		List<Residue> listOfResidues = rp.getAllResidues();

		// Get random residues from all molecules and compute distance between them
		Random rand = new Random();
		Residue randomResidue1 = listOfResidues.get(rand.nextInt(listOfResidues.size()));
		System.out.println("Résidu aléatoire 1 = " + randomResidue1);
		Residue randomResidue2 = listOfResidues.get(rand.nextInt(listOfResidues.size()));
		System.out.println("Résidu aléatoire 2 = " + randomResidue2);

		double distance = ResidueDistanceCalculation.distanceBetweenResidues(randomResidue1, randomResidue2);
		System.out.println("La distance entre les 2 résidus est de " + Math.round(distance) + " Angström\n");
	}

	private static void allResiduesFromDistance(String structureFile, String ID, int distance) throws IOException {
		FileReader fr = new FileReader(structureFile);
		StringBuilder sb;
		sb = fr.readerJar();
		ResidueParser rp = new ResidueParser(sb);
		List<Residue> listOfResidues = rp.getAllResidues();
		for (Residue residue : listOfResidues) {
			residue.toString();
		}

		// parsing molecules
		MoleculeParser mp = new MoleculeParser(sb);
		List<Molecule> listOfMolecules = new ArrayList<>();
		listOfMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));
		System.out.println(
				"Il y a " + listOfMolecules.size() + " molécule(s) dans la structure " + ID.toUpperCase() + "\n");

		// For each molecule found, append the list of residues
		for (Molecule currentMolecule : listOfMolecules) {

			List<Residue> residuesOfCurrentMolecule = listOfResidues.stream()
					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
					.collect(Collectors.toList());
			currentMolecule.setResidues(residuesOfCurrentMolecule);
			System.out.println(currentMolecule.toString());
		}

		// get complex info
		Complex complex = new Complex(ID, listOfMolecules);

		Random rand = new Random();
		Residue randomResidue1 = listOfResidues.get(rand.nextInt(listOfResidues.size()));

		// Get all neighbor residues
		List<Residue> interactions = NeighborSearch.getNeighborsResidues(complex, randomResidue1, distance);
		System.out.println("Résidus situés à une distance inférieure à " + distance + " Anstroms du résidu aléatoire "
				+ randomResidue1.toString() + "\n");
		for (Residue residue : interactions) {
			System.out.println(residue.toString());
		}
	}

	private static void distancesFromResidue(String structureFile, String ID, int distance) throws IOException {
		FileReader fr = new FileReader(structureFile);
		StringBuilder sb;
		sb = fr.readerJar();
		ResidueParser rp = new ResidueParser(sb);
		List<Residue> listOfResidues = rp.getAllResidues();
		for (Residue residue : listOfResidues) {
			residue.toString();
		}

		// parsing molecules
		MoleculeParser mp = new MoleculeParser(sb);
		List<Molecule> listOfMolecules = new ArrayList<>();
		listOfMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));

		// For each molecule found, append the list of residues
		for (Molecule currentMolecule : listOfMolecules) {

			List<Residue> residuesOfCurrentMolecule = listOfResidues.stream()
					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
					.collect(Collectors.toList());
			currentMolecule.setResidues(residuesOfCurrentMolecule);
		}

		// get complex info
		Complex complex = new Complex(ID, listOfMolecules);

		Random rand = new Random();
		Residue randomResidue1 = listOfResidues.get(rand.nextInt(listOfResidues.size()));

		// Get all neighbor residues
		List<Double> distances = NeighborSearch.getNeighborsDistance(complex, randomResidue1, distance);
		System.out.println("Distances les plus faibles des résidus situés à moins de " + distance
				+ " Anstroms du résidu aléatoire : " + randomResidue1.toString() + "\n");
		for (Double d : distances) {
			System.out.println(d);
		}
	}

	private static void representInteractions(String structureFile, String ID, int distance)
			throws IOException, URISyntaxException {
		FileReader pdb = new FileReader(structureFile);
		StringBuilder sb = pdb.readerJar();
		ResidueParser rp = new ResidueParser(sb);

		List<Residue> listOfResidues = rp.getAllResidues();

		// parsing molecules
		MoleculeParser mp = new MoleculeParser(sb);
		List<Molecule> listOfMolecules = new ArrayList<>();
		listOfMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));

		// parsing chains
		ChainParser cp = new ChainParser(sb);
		for (Molecule mol : listOfMolecules) {
			System.out.println(mol.getDescription());
			mol.setChain(cp.getChains(mol));
		}

		// JMOL CHAINS
		// Create structure to fit each molecule number with the list of chains
		HashMap<Integer, List<Character>> jmolInfo = new HashMap<Integer, List<Character>>();

		// For each molecule found, append the list of residues
		for (Molecule currentMolecule : listOfMolecules) {

			List<Residue> residuesOfCurrentMolecule = listOfResidues.stream()
					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
					.collect(Collectors.toList());

			// Set all residues to the correct molecule
			currentMolecule.setResidues(residuesOfCurrentMolecule);
			List<Chain> c = currentMolecule.getChain();

			List<Character> chainCharacters = c.stream().map(Chain::getChain).collect(Collectors.toList());
			System.out.println(chainCharacters.toString());

			jmolInfo.put(currentMolecule.getId(), chainCharacters);

		}

		// Get the names of the alternate chains displayed in Jmol
		Set<Character> chains = new HashSet<Character>(listOfResidues.size());
		listOfResidues.stream().filter(r -> chains.add(r.getAltChain())).collect(Collectors.toList());

		// Test get neighbors from chain
		List<Residue> neighborsFromChain = NeighborSearch.getNeighborsFromChains(listOfMolecules.get(0),
				listOfMolecules.get(1), distance, true);

		// link to JmolIntegration
		List<Character> selectChains = new ArrayList<Character>();
		List<String> selectResiduesCommand = new ArrayList<String>();
		String selectAtoms = "";
		System.out.println("Molécules et chaines pour la représentation Jmol = " + jmolInfo.toString());
		if (neighborsFromChain.size() >= 1) {
			for (Character character : chains) {
				selectChains.add(character);
				for (Residue residue2 : neighborsFromChain) {
					if (residue2.getAltChain() == character) {
						selectResiduesCommand
								.add(residue2.getResidueName().toLowerCase() + residue2.getAltResidueNumber() + ",");
						selectAtoms += "atomno=" + residue2.getAtomNumber() + ",";
					}
				}
			}

			System.out.println(System.getProperty("java.io.tmpdir") + structureFile);

			JmolIntegration testJmol = new JmolIntegration();

			FetchStructure structureFromPDB = new FetchStructure(ID);
			structureFromPDB.getPDBFile();

			testJmol.setStructure(structureFromPDB.getPath(), jmolInfo, selectAtoms);
		} else {
			JmolIntegration Jmol = new JmolIntegration();
			Jmol.setStructureWithoutInteraction(structureFile, jmolInfo);
		}
	}

	private static String complexes(int i) {

		switch (i) {
		case 1:
			return "1fn3";
		case 2:
			return "2b5i";
		case 3:
			return "2na8";
		case 4:
			return "1a5b";
		case 5:
			return "3dcu";
		case 6:
			return "3l3t";
		case 7:
			return "3qt2";
		case 8:
			return "3tnw";
		case 9:
			return "6rj4";
		default:
			return "";
		}

	}

	private static String cytokines(int i) {

		switch (i) {
		case 1:
			return "1ira";
		case 2:
			return "3alq";
		case 3:
			return "3dlq";
		case 4:
			return "3g9v";
		case 5:
			return "3og4";
		case 6:
			return "3og6";
		case 7:
			return "3qb7";
		case 8:
			return "3va2";
		case 9:
			return "2z3r";
		case 10:
			return "3tgx";
		default:
			return "";
		}

	}

}
