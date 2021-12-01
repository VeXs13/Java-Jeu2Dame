package utilitaire;

import java.util.Scanner;

public class Fonctions {

	@SuppressWarnings("resource")
	public static String giveString() {

		return new Scanner(System.in).nextLine().toLowerCase();

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

}
