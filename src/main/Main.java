package main;

import jeu.Plateau;
import utilitaire.util;

public class Main {

	public static void main(String[] args) {

		// fichiers servant a la configuration de base (indispensable pour cette
		// version)
		String config = "./exterieur/default/config.properties";
		String position = "./exterieur/default/position.txt";

		// v�rifie les fichiers (et leur contenus)
		Plateau plateau = new Plateau(config, position);

		// d�termin� par le constructeur
		if (plateau.getJouer()) {
			
			util.seeInformations(plateau);

			// commencer une nouvelle partie
			//plateau.start();

			System.out.println("le programme s'est termin� normalement");
		} else {
			System.out.println("le programme � une erreur");
		}
	}

}
