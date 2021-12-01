package utilitaire;

import jeu.Plateau;

public class Menu {

	public static Plateau menuPrincipal() {

		Plateau plateau = new Plateau();

		Boolean continuer = true;

		while (continuer) {

			for (int i = 0; i < 100; i++) {
				System.out.print("\n"); // sert a sauter une page a défaut de clear
			}
			
			Fonctions.afficherAllInformations(plateau);

			// faire un fichier avec des constantes si besoin
			System.out.println("1) définir le joueur blanc");
			System.out.println("2) définir le joueur noir");
			System.out.println("3) changer carte");
			System.out.println("4) quitter");
			System.out.println("5) valider");
			System.out.print("votre choix : ");

			String choix = Fonctions.giveString();

			switch (choix) {
			case "1":
				Options.defJoueur(plateau.getJoueurs(),0);
				break;
			case "2":
				Options.defJoueur(plateau.getJoueurs(),1);
				break;
			case "3":
				Options.changerCarte(plateau);
				break;
			case "4":
				continuer = false;
				plateau.setJouer(false);
				break;
			case "5":
				System.out.println("???");
				continuer = false;
				plateau.setJouer(true);
				break;

			}

		}

		return plateau;
	}

}
