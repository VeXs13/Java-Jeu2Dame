package jeu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import utilitaire.Fonctions;

public class Plateau {

	int GD;
	int HB;
	String[][] terrain;
	Joueur[] joueurs = { new Joueur("blanc", "blancIA"), new Joueur("noir", "noirIA") };

	String config = "/config.properties";
	String position = "/position.txt";

	static String[] adresses = {

			"./externes/defaut", "./externes/voici", "./externes/voila" };

	int nbtours = 0;

	Boolean jouer = false;

	public void defJoueur(int i) {

		System.out.println("donner un nom ou 0 pour IA");
		System.out.print("choix : ");
		String choix = Fonctions.giveString();

		if (choix == "0") {
			joueurs[i].setName("blancIA");
			joueurs[i].setControlByIA(true);
		} else {
			joueurs[i].setName(choix);
			joueurs[i].setControlByIA(false);
		}
	}

	public void changerCarte() {

		System.out.println("\n\n");
		System.out.println("0) annuler");
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
			return;
		}

		if (verifier(adresses[choix - 1] + config, adresses[choix - 1] + position)) {
			loadCarte(adresses[choix - 1] + config, adresses[choix - 1] + position);
		}

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
//*********************************************
		char pblanc, dblanc, pnoir, dnoir;
//*********************************************
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
					terrain[i][j] = joueurs[0].getLastPiece();
					break;

				case "dblanc":

					joueurs[0].addPiece("dame", lettre, i, j);
					terrain[i][j] = joueurs[0].getLastPiece();
					break;

				case "pnoir":

					joueurs[1].addPiece("pion", lettre, i, j);
					terrain[i][j] = joueurs[1].getLastPiece();
					break;

				case "dnoir":

					joueurs[1].addPiece("dame", lettre, i, j);
					terrain[i][j] = joueurs[1].getLastPiece();
					break;

				default:
					// la case vide poura étre rajouté dans le fichier config.properties
					terrain[i][j] = " . ";
				}
			}
		}
		// util.afficher(terrain); //a comparer avec position.txt

		try {
			// prC ne se ferme pas ??? étrange
			brP.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	private Boolean verifier(String config, String position) {

		System.out.println(config);
		System.out.println(position);

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
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : dblanc=a");
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
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : dnoir=b");
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

	public void setJouer(Boolean jouer) {
		this.jouer = jouer;
	}

	public Boolean getJouer() {
		return jouer;
	}

	public void start() {
		System.out.println("start");
	}

	@Override
	public String toString() {

		if (terrain != null) {
			Fonctions.afficher(terrain);
		} else {
			System.out.println("aucun terrain sélectionné");
		}

		return "\nHB = " + HB + " GD = " + GD + " jouer = " + jouer + "\n\njoueur blanc\n" + joueurs[0]
				+ "\njoueur noir\n" + joueurs[1];
	}

}
