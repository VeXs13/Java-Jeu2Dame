package jeu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import piece.Piece;
import utilitaire.Fonctions;


public class surplus {

	int GD;
	int HB;

	String[][] terrain;

	char pblanc, dblanc, pnoir, dnoir;

	Joueur[] joueurs = { new Joueur("blanc", "blancIA"), new Joueur("noir", "noirIA") };

	final String config = "/config.properties";
	final String position = "/position.txt";

	final String[] adresses = { "./externes/defaut", "./externes/voici", "./externes/voila" };

	public void defJoueur(int i) {

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

	public void changerCarte() {

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
			loadCarte(adresses[choix - 1] + config, adresses[choix - 1] + position);
		}

	}

	@SuppressWarnings("resource")
	private Boolean verifier(String config, String position) {

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

	private void loadCarte(String config, String position) {

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
		
		try {
			GD = Integer.parseInt(prC.getProperty("gd"));
			HB = Integer.parseInt(prC.getProperty("hb"));
			pblanc = prC.getProperty("pblanc").charAt(0);
			dblanc = prC.getProperty("dblanc").charAt(0);
			pnoir = prC.getProperty("pnoir").charAt(0);
			dnoir = prC.getProperty("dnoir").charAt(0);
			joueurs[0].setPion(pblanc);
			joueurs[0].setDame(dblanc);
			joueurs[1].setPion(pnoir);
			joueurs[1].setDame(dnoir);

		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\na mal fonctionné\nréessayer");
			return;
		}

		// -------------------------------------------------------------------------------------------
		
		joueurs[0].pieces.clear();
		joueurs[1].pieces.clear();
		//joueurs[0].pieces = new HashMap<Integer, Piece>();
		//joueurs[1].pieces = new HashMap<Integer, Piece>();

		terrain = new String[HB][GD];
		// util.afficher(terrain); //il est remplis de "null"

		String str;
		// remplir les lignes
		for (int i = 0; i < HB; i++) {

			// recuperer la ligne du fichier
			try {
				str = brP.readLine();
			} catch (IOException e) {
				str = "";
			}

			// remplir les colonnes
			for (int j = 0; j < GD; j++) {

				char lettre = '\0';
				String choix = "autre";

				if (j < str.length()) {
					lettre = str.charAt(j);
					if (lettre == pblanc)
						choix = "pblanc";
					if (lettre == dblanc)
						choix = "dblanc";
					if (lettre == pnoir)
						choix = "pnoir";
					if (lettre == dnoir)
						choix = "dnoir";
				}

				// j'aurais bien fait uniquement un switch mais
				// case expressions must be constant expressions
				// il ne veut pas de valeurs dans une variable, il la veut en brute

				switch (choix) {
				case "pblanc":

					joueurs[0].addPiece("pion", lettre, i, j);
					// terrain[i][j] = joueurs[0].getLastPiece();
					break;

				case "dblanc":

					joueurs[0].addPiece("dame", lettre, i, j);
					// terrain[i][j] = joueurs[0].getLastPiece();
					break;

				case "pnoir":

					joueurs[1].addPiece("pion", lettre, i, j);
					// terrain[i][j] = joueurs[1].getLastPiece();
					break;

				case "dnoir":

					joueurs[1].addPiece("dame", lettre, i, j);
					// terrain[i][j] = joueurs[1].getLastPiece();
					break;

				default:
					// la case vide poura étre rajouté dans le fichier config.properties
					// terrain[i][j] = " . ";
				}
			}
		}

		remplirTerrainBase();
		// util.afficher(terrain); //a comparer avec position.txt

		try {
			// prC n'as pas besoin de se fermer??? étrange
			brP.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

public void remplirTerrainBase(){
		
		for(int i = 0 ; i < terrain.length ; i++) {
			for(int j = 0 ; j < terrain[i].length ; j++) {
				terrain[i][j] = " . ";
			}
		}
		for(Map.Entry<Integer,Piece> map : joueurs[0].getPieces().entrySet()) {
			terrain[map.getValue().getI()][map.getValue().getJ()] = map.getValue().getVisage();
		}
		for(Map.Entry<Integer,Piece> truc : joueurs[1].getPieces().entrySet()) {
			terrain[truc.getValue().getI()][truc.getValue().getJ()] = truc.getValue().getVisage();
		}
	}

}
