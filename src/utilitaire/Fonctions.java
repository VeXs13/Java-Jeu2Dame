package utilitaire;

import java.util.Scanner;

public class Fonctions {

	@SuppressWarnings("resource")
	public static String giveString() {
		
		return new Scanner(System.in).nextLine().toLowerCase();

	}

	public static void afficher(String[][] terrain) {

		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[i].length; j++) {
				System.out.print(terrain[i][j]);
			}
			System.out.print("\n");
		}

	}

}
