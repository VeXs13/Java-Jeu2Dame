package utilitaire;

import jeu.Plateau;

public class Menu {

	public static void menuPrincipal() {

		Plateau plateau = new Plateau();

		Boolean continuer = true;
		
		String phraseErreur = "";

		while (continuer) {

			Fonctions.afficherAllInformations(plateau);
			System.out.println(phraseErreur+"\n\n");
			phraseErreur = "";

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
				Options.defJoueur(plateau.getJoueurs(), 0);
				break;
			case "2":
				Options.defJoueur(plateau.getJoueurs(), 1);
				break;
			case "3":
				Options.changerCarte(plateau);
				break;
			case "4":
				continuer = false;
				plateau.setJouer(false);
				break;
			case "5":
				if (plateau.getTerrain() != null) {

					if (!Fonctions.sameValue(plateau.getJoueurs()[0].getName(), plateau.getJoueurs()[1].getName())) {

						continuer = false;
						plateau.setJouer(true);
					} else {
						phraseErreur = "ils ont les mêmes noms";
					}
				}
				break;

			}

		}

		if (plateau.isJouer()) {

			plateau.start();
		}
	}

}
