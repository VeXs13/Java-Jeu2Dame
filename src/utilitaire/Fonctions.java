package utilitaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

import jeu.Joueur;
import jeu.Plateau;
import piece.Piece;
import piece.Positions;

public class Fonctions {

	@SuppressWarnings("resource")
	public static String giveString() {

		return new Scanner(System.in).nextLine();

	}

	public static int giveInt() {
		char[] chiffres = giveString().toCharArray();
		int result = 0;
		for (int i = 0; i < chiffres.length; i++) {
			System.out.println("pieces[i] = " + chiffres[i]);

			if ('0' <= chiffres[i] && chiffres[i] <= '9') {

				System.out.println("give int : result = " + result);
				result = (result * 10) + chiffres[i] - '0';
			} else {
				return result;
			}
		}
		return result;
	}

	public static String[][] remplirTerrain(int hb, int gd, String motif, Joueur[] joueurs) {
		String[][] result = new String[hb][gd];

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = motif;
			}
		}

		for (Joueur joueur : joueurs) {

			for (Map.Entry<Integer, Piece> map : joueur.getPieces().entrySet()) {
				result[map.getValue().getI()][map.getValue().getJ()] = map.getValue().getVisage();
			}
		}
		return result;
	}

	public static boolean aucunDeplacementPossible(String[][] terrain, int HB, int GD, Joueur joueur,
			Joueur joueurAdverse) {

		String[][] terrainCopy = terrain.clone();

		char pionAdverse = joueurAdverse.getPion();
		char dameAdverse = joueurAdverse.getDame();

		HashMap<String, Positions> result;

		for (Entry<Integer, Piece> piece : joueur.getPieces().entrySet()) {

			result = piece.getValue().deplacementsPossibles(terrainCopy, joueur, HB, GD, pionAdverse, dameAdverse);

			if (result.size() > 0) {
				return false;
			}

		}
		return true;
	}

	public static boolean contien(String tableau, char lettre1, char lettre2) {

		for (int i = 0; i < tableau.length(); i++) {

			if (tableau.charAt(i) == lettre1 || tableau.charAt(i) == lettre2) {
				return true;
			}
		}

		return false;
	}

	// ------------------niveau 1
	public static void afficherAllInformations(Plateau plateau) {

		for (int i = 0; i < 20; i++)
			System.out.println("\n");
		Fonctions.afficher(plateau.getTerrain());
		System.out.print("\ntour : " + plateau.getNbTours() + "\n\n");
		Fonctions.afficherAll(plateau.getJoueurs());

	}

	// ------------------niveau 2
	private static void afficherAll(Joueur[] joueurs) {

		for (Joueur joueur : joueurs) {
			System.out.println(joueur.getAllInformations());
		}

	}

	// ------------------niveau 3
	public static void afficher(String[][] terrain) {

		
		if (terrain == null) {
			System.out.println("aucun terrain sélectionné");
		} else {
			
			int hb = terrain.length;
			int gd = terrain[0].length;

			for (int i = 0; i < terrain.length; i++) {
				for (int j = 0; j < terrain[i].length; j++) {
					System.out.print(terrain[i][j]);
				}

				if (i % 2 == 0) {
					System.out.print(formater((hb * gd / 2) - (i * (hb / 2)+(gd/2)) + 1, 7));
				}

				System.out.print("\n");
			}

			String inter = "";
			int max = gd / 2;

			for (int j = 0; j < 3 * gd; j++) {

				if (j % 6 == 1) {
					inter += max;
					max--;
				} else {
					inter += " ";
				}

			}
			System.out.println(inter);
		}

	}

	// ------------------niveau 2
	public static void afficherInformations(Plateau plateau, String texte) {
		afficherInformations(plateau);
		System.out.println(texte);

	}

	// ------------------niveau 3
	public static void afficherInformations(Plateau plateau) {

		for (int i = 0; i < 20; i++) {
			System.out.println("\n");
		}

		Fonctions.afficher(plateau.getTerrain());
		System.out.print("\ntour : " + plateau.getNbTours() + "\n\n");
		Fonctions.afficher(plateau.getJoueurs());

	}

	// ------------------niveau 4
	private static void afficher(Joueur[] joueurs) {

		for (Joueur joueur : joueurs) {
			System.out.println(joueur.getInformations());
		}

	}

	public static int trad(String nbr) {
		int result = 0;
		for (int i = 1; i < nbr.length(); i++) {
			if ('0' <= nbr.charAt(i) && nbr.charAt(i) <= '9') {
				result = (result * 10) + nbr.charAt(i) - '0';
			} else {
				return result;
			}
		}
		return result;
	}

	public static String formater(int chiffre, int taille) {

		String result = Integer.toString(chiffre);

		for (int i = result.length(); i < taille; i++) {

			if (i % 2 == 0) {
				result = result + " ";
			} else {
				result = " " + result;
			}
		}

		return result;
	}

	// niveau 1
	public static int choixPiece(boolean controlIA, int tailleMax) {

		if (controlIA) {
			return Fonctions.nbrAleatoire(1, tailleMax);
		} else {

			return Fonctions.giveInt();
		}
	}

	public static int nbrAleatoire(int min, int max) {

		return min + new Random().nextInt(max + 1 - min);
	}

	public static String choixMouvement(boolean controlIA, int tailleMax) {

		if (controlIA) {

			return Integer.toString(Fonctions.nbrAleatoire(1, tailleMax));
		} else {

			return Fonctions.giveString();
		}
	}

	public static String abanddon(Joueur joueurAdverse) {
		System.out.println("le joueur " + joueurAdverse.getName() + " souhaite abandonner");
		System.out.println("0 pour annuler");
		System.out.println("1 pour déclarer match null");
		System.out.println("2 pour confirmer l'abandon");
		System.out.print("choix : ");

		String choix = giveString();

		if (joueurAdverse.getControlByIA()) {
			choix = "2";
		}

		switch (choix) {

		case "1":
			return "null";

		case "2":
			return "abandon";

		default:
			return "";
		}
	}

	public static String combine(String texte, int ajout) {

		String result = "";

		int i = 0;
		while (texte.charAt(i) < '0' || '9' < texte.charAt(i)) {
			result += texte.charAt(i);
			i++;
		}
		int valeur = 0;

		while (i < texte.length() && '0' <= texte.charAt(i) && texte.charAt(i) <= '9') {
			valeur = (valeur * 10) + (texte.charAt(i) - '0');
			i++;
		}
		valeur += ajout;
		return result + valeur;
	}

	public static boolean sameValue(String nom1, String nom2) {

		if (nom1.length() != nom2.length()) {
			return false;
		}

		for (int i = 0; i < nom1.length(); i++) {

			if (nom1.charAt(i) != nom2.charAt(i)) {
				return false;
			}

		}

		return true;
	}

	public static ArrayList<String> recupererTerrain(String[][] terrain, int nbTours, int hb, int gd) {

		ArrayList<String> result = new ArrayList<String>();

		String inter = "";

		for (int i = 0; i < terrain.length; i++) {
			inter = "";

			for (int j = 0; j < terrain[i].length; j++) {
				inter += terrain[i][j];
			}

			if (i % 2 == 0) {
				inter += formater( ( (hb * gd ) - ((i * hb ) + gd) )/2 + 1 , 7);
			}

			result.add(inter);
		}

		inter = "";
		int max = gd / 2;

		for (int j = 0; j < 3 * gd; j++) {

			if (j % 6 == 1) {
				inter += max;
				max--;
			} else {
				inter += " ";
			}

		}

		result.add(inter);
		result.add("");
		result.add("tour : " + nbTours);

		return result;
	}

	public static String traductionMouvement(Piece depart, Positions arrive, int hb, int gd) {
		
		int debut = ( (hb * gd ) - ( (depart.getI() * hb)  + (depart.getJ() - 1  ) ) ) / 2  ;
		String milieu = sameValue(arrive.getManger(), "") ? "-" : "x";
		int fin = ( (hb * gd ) - ( (arrive.getI() * hb  + arrive.getJ() - 1  ) ) ) / 2;

		return debut + milieu + fin;
	}

	public static String presenter(String texte) {

		// System.out.println("=> "+texte);

		if (texte == null || texte == "") {
			return "";
		}

		if (texte.contains("-")) {
			return texte;
		}

		String debut = "";
		for (int i = 0; texte.charAt(i) != 'x' && i < texte.length(); i++) {
			debut += texte.charAt(i);
		}

		String fin = "";
		for (int i = texte.length() - 1; texte.charAt(i) != 'x' && i > 0; i--) {
			fin = texte.charAt(i) + fin;
		}

		// System.out.println( debut + "x" + fin + " =>");
		return debut + "x" + fin;
	}

}
