package utilitaire;

import jeu.Plateau;

public class util {

	public static String repeat(String motif, int combien) {
		return motif + (combien != 0 ? repeat(motif, combien - 1) : "");
	}

	public static void afficher(String[][] terrain) {

		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[i].length; j++) {
				System.out.print(terrain[i][j]);
			}
			System.out.print("\n");
		}

	}

	public static void seeInformations(Plateau plateau) {
		
		System.out.println(plateau);
		
	}

}
