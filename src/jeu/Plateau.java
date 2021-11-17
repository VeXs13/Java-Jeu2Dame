package jeu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import utilitaire.util;

public class Plateau {

	int GD;
	int HB;
	String[][] terrain;
	Joueur[] joueurs = new Joueur[2];
	// sert pour initialiser et continuer la partie
	Boolean jouer = false;

	int nbtours = 0;

	public Plateau(String config, String position) {

		Properties prC = new Properties();
		try {

			prC.load(new FileReader(config));

		} catch (IOException e) {
			System.out.println("le fichier : " + config + "\nn'as pas pu s'ouvrir");
			return;
		}

		BufferedReader brP;
		try {

			brP = new BufferedReader(new FileReader(new File(position)));

		} catch (FileNotFoundException e) {
			System.out.println("le fichier : " + position + "\nn'as pas pu s'ouvrir");
			return;
		}
//*********************************************
		char pblanc, dblanc, pnoir, dnoir;
//*********************************************
		try {
			GD = Integer.parseInt(prC.getProperty("gd"));
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : gd=10");
			return;
		}
		try {
			HB = Integer.parseInt(prC.getProperty("hb"));
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : hb=10");
			return;
		}
		try {
			pblanc = prC.getProperty("pblanc").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : pblanc=o");
			return;
		}
		try {
			dblanc = prC.getProperty("dblanc").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : dblanc=a");
			return;
		}
		try {
			pnoir = prC.getProperty("pnoir").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : pnoir=x");
			return;
		}
		try {
			dnoir = prC.getProperty("dnoir").charAt(0);
		} catch (Exception e) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ndéfaut : dnoir=b");
			return;
		}

		//System.out.println("gd = " + GD + " hb = " + HB + " pblanc = " + pblanc + " dblanc = " + dblanc + " pnoir = "+ pnoir + " dnoir = " + dnoir);

		if (GD < 1) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar gd = " + GD + "<1");
			return;
		}
		if (HB < 1) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar hb = " + HB + "<1");
			return;
		}
		// j'ai eu la fléme de détailler
		if ((pblanc == pnoir) || (pblanc == dnoir) || (pblanc == dblanc) || (pnoir == dnoir) || (pnoir == dblanc)
				|| (dnoir == dblanc)) {
			System.out.println("le fichier : " + config + "\nest mal configuré\ncar plusieurs piéces ont la même lettre");
			return;
		}

		// -------------------------------------------------------------------------------------------
		joueurs[0] = new Joueur("blanc", pblanc, dblanc);
		joueurs[1] = new Joueur("noir", pnoir, dnoir);
		// --------------------------------------------------------------------------------------------

		terrain = new String[HB][GD];

		// util.afficher(terrain); //il est remplis de "null"

		String str;
		// remplir les lignes
		for (int i = 0; i < HB; i++) {

			// recuperer la ligne du fichier
			try {
				str = brP.readLine();
				if (str.length() < GD) {
					str += util.repeat(" ", GD - str.length() - 1);
				}
			} catch (IOException e) {
				// bizarre mais bon
				// The method repeat(int) isundefined for the type String
				str = util.repeat(" ", GD);
			}
// str a donc une taille égale ou supérieur aux nombre de cases a remplir

			// remplir les colonnes
			for (int j = 0; j < GD; j++) {

				char lettre = str.charAt(j);
				// j'aurais bien fait uniquement un switch mais
				// case expressions must be constant expressions
				// il ne veut pas de valeurs dans une variable, il la veut en brute

				String choix = "autre";
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

					joueurs[0].addPiece("pion",lettre, i, j);
					terrain[i][j] = joueurs[0].getLastPiece();
					break;

				case "dblanc":

					joueurs[0].addPiece("dame",lettre, i, j);
					terrain[i][j] = joueurs[0].getLastPiece();
					break;

				case "pnoir":

					joueurs[1].addPiece("pion", lettre,i, j);
					terrain[i][j] = joueurs[1].getLastPiece();
					break;

				case "dnoir":

					joueurs[1].addPiece("dame",lettre, i, j);
					terrain[i][j] = joueurs[1].getLastPiece();
					break;

				default:
					//la case vide poura étre rajouté dans le fichier config.properties
					terrain[i][j] = " . ";
				}
			}
		}
		//util.afficher(terrain); //a comparer avec position.txt
		
		try {
			//penser a fermer prC également
			brP.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		jouer = true;
	}

	public int getGD() {
		return GD;
	}

	public void setGD(int gD) {
		GD = gD;
	}

	public int getHB() {
		return HB;
	}

	public void setHB(int hB) {
		HB = hB;
	}

	public String[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(String[][] terrain) {
		this.terrain = terrain;
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}

	public Boolean getJouer() {
		return jouer;
	}

	public void setJouer(Boolean jouer) {
		this.jouer = jouer;
	}

	public int getNbtours() {
		return nbtours;
	}

	public void setNbtours(int nbtours) {
		this.nbtours = nbtours;
	}

	@Override
	public String toString() {
		util.afficher(terrain);
		return "HB = "+HB+" GD = "+GD+"jouer = "+jouer+"\njoueur[0]-----\n"+joueurs[0]+"\njoueur[1]-----\n"+joueurs[1];
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
	
}
