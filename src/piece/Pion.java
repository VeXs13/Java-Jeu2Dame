package piece;

import java.util.HashMap;
import jeu.Joueur;
import utilitaire.Fonctions;

public class Pion extends Piece {

	public Pion(int id, char motif, int i, int j) {
		super(id, motif, i, j);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashMap<String, Point> deplacementsPossibles(String[][] terrain, Joueur joueur, int hb, int gd,char pionAdverse, char dameAdverse) {

		// fonction très moche en copier-coller mais comme il n'y a que 4 cas

		HashMap<String, Point> result = new HashMap<String, Point>();

		// blanc descend et noir monte avec 0 au sommet
		int cInt = (joueur.getCouleur() == "blanc") ? 1 : -1;

		// distance 1 a gauche

		// si la case est disponible et vide
		if (0 <= cInt + i && i + cInt < hb && j - 1 >= 0 && terrain[i + cInt][j - 1] == " . ") {

			// ajouter un chiffre sur le terrain
			terrain[i + cInt][j - 1] = Fonctions.formater(result.size() + 1, 3);

			// ajouter ce chiffre aux résultats possibles
			result.put(Integer.toString(result.size() + 1), new Point(i + cInt, j - 1));
		}

		// distance 1 a droite

		// si la case est disponible et vide
		if (0 <= cInt + i && i + cInt < hb && j + 1 < gd && terrain[i + cInt][j + 1] == " . ") {

			// ajouter un chiffre sur le terrain
			terrain[i + cInt][j + 1] = Fonctions.formater(result.size() + 1, 3);

			// ajouter ce chiffre aux résultats possibles
			result.put(Integer.toString(result.size() + 1), new Point(i + cInt, j + 1));
		}

		// distance 2 a gauche

		// si la case est dans le tableau
		if (0 <= 2 * cInt + i && i + 2 * cInt < hb && j - 2 >= 0) {

			// si la case visée est disponible
			if (terrain[i + 2 * cInt][j - 2] == " . ") {

				// si la case sauté est un pion ou dame adverse
				if (terrain[i + cInt][j - 1].contains(Character.toString(pionAdverse))
						|| terrain[i + cInt][j - 1].contains(Character.toString(dameAdverse))) {

					// ajouter un chiffre sur le terrain
					terrain[i + 2 * cInt][j - 2] = Fonctions.formater(result.size() + 1, 3);

					// ajouter ce chiffre aux résultats possibles
					result.put(Integer.toString(result.size() + 1), new Point(i + 2 * cInt, j - 2));

					// récupérer le nom de la piéce sauté
					manger = terrain[i + cInt][j - 1];

				}
			}
		}
		
		// distance 2 a droite

				// si la case est dans le tableau
				if (0 <= 2 * cInt + i && i + 2 * cInt < hb && j + 2 < gd) {

					// si la case visée est disponible
					if (terrain[i + 2 * cInt][j + 2] == " . ") {

						// si la case sauté est un pion ou dame adverse
						if (terrain[i + cInt][j + 1].contains(Character.toString(pionAdverse))
								|| terrain[i + cInt][j + 1].contains(Character.toString(dameAdverse))) {

							// ajouter un chiffre sur le terrain
							terrain[i + 2 * cInt][j + 2] = Fonctions.formater(result.size() + 1, 3);

							// ajouter ce chiffre aux résultats possibles
							result.put(Integer.toString(result.size() + 1), new Point(i + 2 * cInt, j + 2));

							// récupérer le nom de la piéce sauté
							manger = terrain[i + cInt][j + 1];

						}
					}
				}
				
				//System.out.println("-----------------------");
				//for(Entry<String, Point> truc : result.entrySet()) {
					
					//System.out.println("key = " + truc.getKey() + "  value = " + truc.getValue().getI() + "  " + truc.getValue().getJ());
				//}
				
		return result;
	}
}
