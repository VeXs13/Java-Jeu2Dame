package utilitaire;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import jeu.Joueur;
import jeu.Plateau;
import piece.Piece;
import piece.Point;

public class Fonctions {

	@SuppressWarnings("resource")
	public static String giveString() {

		return new Scanner(System.in).nextLine().toLowerCase();

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

	public static void afficher(String[][] terrain) {

		if (terrain == null) {
			System.out.println("aucun terrain sélectionné");
		} else {

			for (int i = 0; i < terrain.length; i++) {
				for (int j = 0; j < terrain[i].length; j++) {
					System.out.print(terrain[i][j]);
				}
				System.out.print("\n");
			}
		}

	}

	private static void afficher(Joueur[] joueurs) {

		for (Joueur joueur : joueurs) {
			System.out.println(joueur.getInformations());
		}

	}

	private static void afficherAll(Joueur[] joueurs) {

		for (Joueur joueur : joueurs) {
			System.out.println(joueur.getAllInformations());
		}

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

	public static void afficherInformations(Plateau plateau) {

		for (int i = 0; i < 20; i++)
			System.out.println("\n");
		Fonctions.afficher(plateau.getTerrain());
		System.out.print("\ntour : " + plateau.getNbtours() + "\n\n");
		Fonctions.afficher(plateau.getJoueurs());

	}

	public static void afficherAllInformations(Plateau plateau) {

		for (int i = 0; i < 20; i++)
			System.out.println("\n");
		Fonctions.afficher(plateau.getTerrain());
		System.out.print("\ntour : " + plateau.getNbtours() + "\n\n");
		Fonctions.afficherAll(plateau.getJoueurs());

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

	public static boolean aucunDeplacementPossible(String[][] terrain, Joueur joueur, int hb, int gd, char pionAdverse,
			char dameAdverse) {

		for (Entry<Integer, Piece> piece : joueur.getPieces().entrySet()) {

			// System.out.print(piece.getKey() + " = ");

			HashMap<String, Point> result;

			result = piece.getValue().deplacementsPossibles(terrain, joueur, hb, gd, pionAdverse, dameAdverse);

			// System.out.println(result.size());

			if (result.size() > 0) {

				return false;
			}

			// if(piece.getValue().deplacementsPossibles(plateau.getTerrain(),
			// joueur,plateau.getHB(),plateau.getGD()).size() > 0) {
			// return true;
			// }
		}

		return true;
	}

	public static void afficherInformations(Plateau plateau, String texte) {
		afficherInformations(plateau);
		System.out.println(texte);

	}

	public static boolean aucunDeplacementPossible(Plateau plateau, Joueur joueur) {

		if (joueur.getCouleur() == "blanc") {
			if (Fonctions.aucunDeplacementPossible(plateau.getTerrain(), joueur, plateau.getHB(), plateau.getGD(),
					plateau.getPnoir(), plateau.getDnoir())) {
				return true;
			}
		} else {
			if (Fonctions.aucunDeplacementPossible(plateau.getTerrain(), joueur, plateau.getHB(), plateau.getGD(),
					plateau.getPblanc(), plateau.getDblanc())) {
				return true;
			}
		}

		return false;
	}

}
