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
	public HashMap<String, Point> deplacementsPossibles(String[][] terrain, Joueur joueur) {

		HashMap<String, Point> result = new HashMap<String, Point>();

		int cInt = (joueur.getCouleur() == "blanc") ? 1 : -1;
		// blanc descend et noir monte avec 0 au sommet

		if ( terrain[i + cInt][j - 1] == " . ") {

			terrain[i + cInt][j - 1] = Fonctions.formater(result.size()+1,3);
			result.put(Integer.toString(result.size()+1), new Point(i + cInt,j-1));

		} else {

			if (terrain[i + 2 * cInt][j - 2] == " . " && terrain[i + cInt][j - 1] != " . "
					&& terrain[i + cInt][j - 1] != joueur.getPion() && terrain[i + cInt][j - 1] != joueur.getDame()) {

				terrain[i + 2 * cInt][j - 2] = Fonctions.formater(result.size()+1,3);
				result.put(Integer.toString(result.size()+1), new Point(i + 2 * cInt,j-2));
				manger = terrain[i + cInt][j - 1];
			}
		}

		if (terrain[i + cInt][j + 1] == " . ") {

			terrain[i + cInt][j + 1] = Fonctions.formater(result.size()+1,3);
			result.put(Integer.toString(result.size()+1), new Point(i + cInt,j-1));

		} else {

			if (terrain[i + 2 * cInt][j + 2] == " . " && terrain[i + cInt][j + 1] != " . "
					&& terrain[i + cInt][j + 1] != joueur.getPion() && terrain[i + cInt][j + 1] != joueur.getDame()) {

				terrain[i + 2 * cInt][j + 2] = Fonctions.formater(result.size()+1,3);
				result.put(Integer.toString(result.size()+1), new Point(i + 2 * cInt,j+2));
				manger = terrain[i + cInt][j - 1];
			}
		}

		return result;

	}
	/*
	 * switch(couleur) {
	 * 
	 * case "blanc" :
	 * 
	 * return new ArrayList<Point>() {{ add(new Point(i + 1,j + -1)); add(new
	 * Point(i + 2,j + -2)); add(new Point(i + 1,j + +1)); add(new Point(i + 2,j +
	 * +2)); }}; default :
	 * 
	 * return new ArrayList<Point>() {{
	 * 
	 * add(new Point(i - 1,j + -1)); add(new Point(i - 2,j + -2)); add(new Point(i -
	 * 1,j + +1)); add(new Point(i - 2,j + +2));
	 * 
	 * }}; } }
	 */
}
