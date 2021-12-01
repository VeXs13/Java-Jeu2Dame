package piece;

import java.util.HashMap;

import jeu.Joueur;
import utilitaire.Fonctions;

public class Dame extends Piece {

	public Dame(int id, char motif, int i, int j) {
		super(id, motif, i, j);
	}

	@Override
	// récupére une arrayList des positions disponibles
	// chaque position est constitué de : x , y , manger
	// manger correspond a la réfférence de la piéce manger
	// manger peut étre vide
	public HashMap<String, Positions> deplacementsPossibles(String[][] terrain, Joueur joueur, int hb, int gd,
			char pionAdverse, char dameAdverse) {

		HashMap<String, Positions> result = new HashMap<String, Positions>();

		String manger;

		// haut droite
		manger = "";

		for (int ligne = i, colonne = j; ligne > 0 && colonne < gd - 1;) {

			ligne = ligne - 1;
			colonne = colonne + 1;

			if (terrain[ligne][colonne] == " . ") {

				terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
				if (manger == "") {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne));
				} else {
					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		// bas droite
		manger = "";

		for (int ligne = i, colonne = j; ligne < hb - 1 && colonne < gd - 1;) {

			ligne = ligne + 1;
			colonne = colonne + 1;

			if (terrain[ligne][colonne] == " . ") {

				terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
				if (manger == "") {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne));
				} else {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		// bas gauche
		manger = "";

		for (int ligne = i, colonne = j; ligne < hb - 1 && colonne > 0;) {

			ligne = ligne + 1;
			colonne = colonne - 1;

			if (terrain[ligne][colonne] == " . ") {

				terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
				if (manger == "") {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne));
				} else {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		// haut gauche
		manger = "";

		for (int ligne = i, colonne = j; ligne > 0 && colonne > 0;) {

			ligne = ligne - 1;
			colonne = colonne - 1;

			if (terrain[ligne][colonne] == " . ") {

				terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
				if (manger == "") {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne));
				} else {

					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		return result;

	}

	@Override
	// récupére une arrayList des positions disponibles
	// chaque position est constitué de : x , y , manger
	// manger correspond a la réfférence de la piéce manger
	// manger NE PEUT PAS étre vide
	public HashMap<String, Positions> deplacementsTueur(String[][] terrain, Joueur joueur, int hb, int gd,
			char pionAdverse, char dameAdverse) {

		HashMap<String, Positions> result = new HashMap<String, Positions>();

		String manger;

		// haut droite
		manger = "";

		for (int ligne = i, colonne = j; ligne > 0 && colonne < gd - 1;) {

			ligne = ligne - 1;
			colonne = colonne + 1;

			if (terrain[ligne][colonne] == " . ") {

				if (manger == "") {
				} else {
					terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		// bas droite
		manger = "";

		for (int ligne = i, colonne = j; ligne < hb - 1 && colonne < gd - 1;) {

			ligne = ligne + 1;
			colonne = colonne + 1;

			if (terrain[ligne][colonne] == " . ") {

				if (manger == "") {

				} else {
					terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		// bas gauche
		manger = "";

		for (int ligne = i, colonne = j; ligne < hb - 1 && colonne > 0;) {

			ligne = ligne + 1;
			colonne = colonne - 1;

			if (terrain[ligne][colonne] == " . ") {

				if (manger == "") {

				} else {
					terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		// haut gauche
		manger = "";

		for (int ligne = i, colonne = j; ligne > 0 && colonne > 0;) {

			ligne = ligne - 1;
			colonne = colonne - 1;

			if (terrain[ligne][colonne] == " . ") {

				if (manger == "") {

				} else {
					terrain[ligne][colonne] = Fonctions.formater(result.size() + 1, 3);
					result.put(Integer.toString(result.size() + 1), new Positions(ligne, colonne, manger));
				}

			} else {

				if (Fonctions.contien(terrain[ligne][colonne], pionAdverse, dameAdverse)) {

					if (manger != "") {
						break;
					}

					manger = terrain[ligne][colonne];

				} else {

					break;

				}

			}

		}

		return result;

	}
}
