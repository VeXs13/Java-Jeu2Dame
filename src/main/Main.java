package main;

import jeu.Plateau;
import utilitaire.Menu;

public class Main {

	public static void main(String[] args) {
		
		Plateau plateau = Menu.menuPrincipal();
		
		if (plateau.isJouer()) {

			// commencer une nouvelle partie
			plateau.start();
		}
		System.out.println("le programme s'est terminé");
		
	}
}
