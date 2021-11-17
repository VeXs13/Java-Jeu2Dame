package main;

import jeu.Plateau;
import utilitaire.Menu;

public class Main {

	public static void main(String[] args) {

		Plateau plateau = Menu.menuPrincipal();

		if (plateau.getJouer()) {

			// commencer une nouvelle partie
			plateau.start();
		}
		System.out.println("le programme s'est terminé");
	}
}
