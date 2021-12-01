package jeu;

import java.util.HashMap;
import java.util.Map;

import piece.Piece;
import piece.Point;
import utilitaire.Fonctions;

public class Plateau extends surplus {

	int nbtours = 0;
	boolean jouer = false;

	public boolean isJouer() {
		return jouer;
	}

	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public String[][] getTerrain() {
		return terrain;
	}

	public void remplirTerrainBase() {

		// remplis le terrain avec la base
		// => des " . " partout et les pieces
		// les pieces mortes étant enlevés des tableaux, elles disparaiseent toute seul
		
		//faudrait voir pour ajouter des sauts de ligne et un encadré pour faire genre "pouf" ça a disparus

		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[i].length; j++) {
				terrain[i][j] = " . ";
			}
		}
		for (Map.Entry<Integer, Piece> truc : joueurs[0].getPieces().entrySet()) {
			terrain[truc.getValue().getI()][truc.getValue().getJ()] = truc.getValue().getVisage();
		}
		for (Map.Entry<Integer, Piece> truc : joueurs[1].getPieces().entrySet()) {
			terrain[truc.getValue().getI()][truc.getValue().getJ()] = truc.getValue().getVisage();
		}
	}

	private void swap(Piece piece, Point destination) {

		//échange la place entre la piéce et la case vide de destination
		

		piece.setI(destination.getI());
		piece.setJ(destination.getJ());

		if (piece.getManger() != "") {
			System.out.println("je vais te manger");
		}
		remplirTerrainBase();
		

	}

	//fonction principale du jeu g.game en plein développement
	public void start() {
		System.out.println("start");

		while (jouer) {

			for (Joueur joueur : joueurs) {

				for (int i = 0; i < 10; i++)
					System.out.println("\n");

				Fonctions.afficher(terrain);

				System.out.print("sélectionner une piéce : ");
				int xxx = Integer.parseInt(Fonctions.giveString());

				if (joueur.getPieces().containsKey(xxx)) {
					Piece prout = joueur.getPieces().get(xxx);
					System.out.println(prout);

					HashMap<String, Point> choix = prout.deplacementsPossibles(terrain, joueur);

					Fonctions.afficher(terrain);

					System.out.print("faire un choix ou 0 pour annuler : ");

					String choixPiece = Fonctions.giveString();

					if (choix.containsKey(choixPiece)) {

						swap(prout, choix.get(choixPiece));

					}

					switch (Fonctions.giveString()) {

					case "0":
						System.out.println("zero");
						break;

					default:
						System.out.println("coucou");
					}

				} else {
					System.out.println("cette piéce n'existe pas");
				}
			}
		}
	}

}
