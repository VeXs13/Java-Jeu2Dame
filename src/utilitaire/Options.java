package utilitaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import jeu.Joueur;
import jeu.Plateau;

public class Options {

	final static String config = "/config.properties";
	final static String position = "/position.txt";

	final static String[] adresses = 
		{ 
			"./externes/defaut",
			"./externes/voici",
			"./externes/voila"
			
		
		};

	public static void defJoueur(Joueur[] joueurs, int i) {

		System.out.println("donner un nom ou '' pour une ia");
		System.out.print("choix : ");
		String choix = Fonctions.giveString();

		switch (choix) {

		case "":
			joueurs[i].setName((i == 0 ? "blancIA" : "noirIA"));
			joueurs[i].setControlByIA(true);
			break;

		default:
			joueurs[i].setName(choix);
			joueurs[i].setControlByIA(false);

		}
	}

	public static void changerCarte(Plateau plateau) {

		// utilise les chemins de fichiers

		System.out.println("\n\n0) annuler");

		for (int i = 0; i < adresses.length; i++) {
			System.out.println(i + 1 + ") " + adresses[i]);
		}

		System.out.print("choix : ");
		int choix;

		try {
			choix = Integer.parseInt(Fonctions.giveString());
		} catch (Exception e) {
			choix = 0;
		}

		if (choix <= 0 || adresses.length < choix) {
			// le nombre donné est en dehors de ce qui est attendus
			return;
		}

		if (verifier(adresses[choix - 1] + config, adresses[choix - 1] + position)) {
			loadCarte(adresses[choix - 1] + config, adresses[choix - 1] + position, plateau);
		}

	}

	@SuppressWarnings("resource")
	private static Boolean verifier(String config, String position) {

		// verifie si les fichiers utilisés conviennent

		// System.out.println(config);
		// System.out.println(position);

		// config.properties
		Properties prC = new Properties();

		try {
			prC.load(new FileReader(config));
		} catch (IOException e) {
			System.out.println("le fichier : " + config + "\nn'as pas pu s'ouvrir");
			return false;
		}

		// ****************************************
		char pblanc, dblanc, pnoir, dnoir;
		// ****************************************

		try {
			int gd = Integer.parseInt(prC.getProperty("gd"));

			if (gd < 1) {
				System.out.println(
						"le fichier : " + config + "\nest mal configuré\ncar gd = " + gd + "<1\ndéfaut : gd=10");
				return false;
			}

		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : gd=10");
			return false;
		}

		try {
			int hb = Integer.parseInt(prC.getProperty("hb"));

			if (hb < 1) {
				System.out.println(
						"le fichier : " + config + "\nest mal configuré\ncar gd = " + hb + "<1\ndéfaut : hb=10");
				return false;
			}

		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : hb=10");
			return false;
		}

		try {
			pblanc = prC.getProperty("pblanc").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : pblanc=o");
			return false;
		}
		try {
			dblanc = prC.getProperty("dblanc").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : dblanc=O");
			return false;
		}
		try {
			pnoir = prC.getProperty("pnoir").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : pnoir=x");
			return false;
		}
		try {
			dnoir = prC.getProperty("dnoir").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : dnoir=X");
			return false;
		}

		if (pblanc == pnoir) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar pblanc = pnoir ont la même lettre");
			return false;
		}

		if (pblanc == dnoir) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar pblanc = dnoir ont la même lettre");
			return false;
		}

		if (pblanc == dblanc) {
			System.out
					.println("le fichier : " + config + "\nest mal configuré\ncar pblanc = dblanc ont la même lettre");
			return false;
		}

		if (pnoir == dnoir) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar pnoir = dnoir ont la même lettre");
			return false;
		}

		if (pnoir == dblanc) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar pnoir = dblanc ont la même lettre");
			return false;
		}

		if (dblanc == dnoir) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar dblanc = dnoir ont la même lettre");
			return false;
		}

		// -------------------------------------------------------------------------------------------

		// position.txt
		try {

			new BufferedReader(new FileReader(new File(position)));

		} catch (FileNotFoundException e) {
			System.out.println("le fichier : " + position + "\nn'as pas pu s'ouvrir");
			System.out.println(e);
			return false;
		}

		return true;
	}

	private static void loadCarte(String config, String position, Plateau plateau) {

		Properties prC = new Properties();
		try {

			prC.load(new FileReader(config));

		} catch (IOException e) {
			System.out.println("le fichier : " + config + "\nn'as pas pu s'ouvrir\nréessayer");
			return;
		}

		BufferedReader brP;
		try {

			brP = new BufferedReader(new FileReader(new File(position)));

		} catch (FileNotFoundException e) {
			System.out.println("le fichier : " + position + "\nn'as pas pu s'ouvrir\nréessayer");
			return;
		}

		char pblanc, dblanc, pnoir, dnoir;
		try {
			plateau.setGD(Integer.parseInt(prC.getProperty("gd")));
			plateau.setHB(Integer.parseInt(prC.getProperty("hb")));

			pblanc = prC.getProperty("pblanc").charAt(0);
			dblanc = prC.getProperty("dblanc").charAt(0);
			pnoir = prC.getProperty("pnoir").charAt(0);
			dnoir = prC.getProperty("dnoir").charAt(0);
			
			plateau.getJoueurs()[0].setPion(pblanc);
			plateau.getJoueurs()[0].setDame(dblanc);
			plateau.getJoueurs()[1].setPion(pnoir);
			plateau.getJoueurs()[1].setDame(dnoir);

			plateau.setPblanc(pblanc);
			plateau.setDblanc(dblanc);
			plateau.setPnoir(pnoir);
			plateau.setDnoir(dnoir);

		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\na mal fonctionné\nréessayer");
			return;
		}

		// -------------------------------------------------------------------------------------------

		plateau.getJoueurs()[0].getPieces().clear();
		plateau.getJoueurs()[1].getPieces().clear();

		plateau.setTerrain(new String[plateau.getHB()][plateau.getGD()]);
		// util.afficher(terrain); //il est remplis de "null"

		String str;
		// remplir les lignes
		for (int i = 0; i < plateau.getHB(); i++) {

			// recuperer la ligne du fichier
			try {
				str = brP.readLine();
			} catch (IOException e) {
				str = "";
			}
			if(str == null) {str = "";}//me suis pas penché dessus
			// remplir les colonnes
			for (int j = 0; j < plateau.getGD() && j < str.length(); j++) {

				char lettre = '\0';
				String choix = "autre";

				lettre = str.charAt(j);
				if (lettre == pblanc)
					choix = "pblanc";
				if (lettre == dblanc)
					choix = "dblanc";
				if (lettre == pnoir)
					choix = "pnoir";
				if (lettre == dnoir)
					choix = "dnoir";

				switch (choix) {
				case "pblanc":

					plateau.getJoueurs()[0].addPiece("pion", plateau.getPblanc(), i, j);
					break;

				case "dblanc":

					plateau.getJoueurs()[0].addPiece("dame", plateau.getDblanc(), i, j);
					break;

				case "pnoir":

					plateau.getJoueurs()[1].addPiece("pion", plateau.getPnoir(), i, j);
					break;

				case "dnoir":

					plateau.getJoueurs()[1].addPiece("dame", plateau.getDnoir(), i, j);
					break;

				default:
				}

				// j'aurais bien fait uniquement un switch mais
				// case expressions must be constant expressions
				// il ne veut pas de valeurs dans une variable, il la veut en brute

			}
		}
		
		plateau.getJoueurs()[0].setTaille(plateau.getJoueurs()[0].getPieces().size());
		plateau.getJoueurs()[1].setTaille(plateau.getJoueurs()[1].getPieces().size());
		
	
		plateau.setTerrain( Fonctions.remplirTerrain(plateau.getHB(),plateau.getGD(), " . ",plateau.getJoueurs() ));
		
		// util.afficher(terrain); //a comparer avec position.txt

		try {
			// prC n'as pas besoin de se fermer??? étrange
			brP.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
