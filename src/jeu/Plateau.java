package jeu;

import java.util.HashMap;
import java.util.Map;
import piece.Piece;
import piece.Point;
import utilitaire.Fonctions;

public class Plateau {

	int GD;
	int HB;

	String[][] terrain;

	char pblanc, dblanc, pnoir, dnoir;

	Joueur[] joueurs = { new Joueur("blanc", "blancIA"), new Joueur("noir", "noirIA") };

	int nbtours = 1;
	boolean jouer = false;
	String texte = ""; // sert a r�cup�rer les messages de fautes pour les voir

	public void remplirTerrainBase() {

		for (int i = 0; i < terrain.length; i++) {
			for (int j = 0; j < terrain[i].length; j++) {
				terrain[i][j] = " . ";
			}
		}
		for (Map.Entry<Integer, Piece> map : joueurs[0].getPieces().entrySet()) {
			terrain[map.getValue().getI()][map.getValue().getJ()] = map.getValue().getVisage();
		}
		for (Map.Entry<Integer, Piece> truc : joueurs[1].getPieces().entrySet()) {
			terrain[truc.getValue().getI()][truc.getValue().getJ()] = truc.getValue().getVisage();
		}
	}

	public void start() {

		System.out.println("start");

		while (jouer) {// boucle infinie

			for (Joueur joueur : joueurs) {// boucle des joueurs

				joueur.setMonTour(true);

				while (joueur.getMonTour()) {// boucle infinie du joueur

					remplirTerrainBase();// remettre la tableau a plat

					Fonctions.afficherInformations(this, texte);// aficher le plateau
					texte = "";

					// v�rifier si il lui reste des pions
					if (Fonctions.aucunDeplacementPossible(this, joueur)) {
						System.out.println("faire une fonction d'abandon contrainte ???");
						return;
					}

					remplirTerrainBase();// remettre la tableau a plat
					
					System.out.print("tour du joueur : " + joueur.getName()+ "\n'0' pour abandonner\ns�lectionner une pi�ce : ");
					
					int choixPiece = Fonctions.giveInt();
					System.out.println("choixPiece = " + choixPiece);// ligne de v�rification du nombre retourn�

					if (choixPiece == 0) {
						System.out.println("faire une super fonction d'abandon volontaire ici");
					}

					if (!joueur.getPieces().containsKey(choixPiece)) {

						texte = "cette pi�ce n'existe pas";

					} else {
						

						Piece piece = joueur.getPieces().get(choixPiece);
						System.out.println("piece choisie : " +piece);// v�rifier que c'est bien la bonne pi�ce

						// collecter tous les d�placements possible pour la pi�ce + les num�ros sur le
						// tableau
						// s�paer en plusieurs fonctions ?
						
					
						
						HashMap<String, Point> choix;
						if (joueur.getCouleur() == "blanc") {
							
							choix = piece.deplacementsPossibles(terrain, joueur, HB, GD, pnoir, dnoir);
						
						} else {
							
							choix = piece.deplacementsPossibles(terrain, joueur, HB, GD, pblanc, dblanc);
							
							
						}
											
						// si la pi�ce ne peut se d�placer nulle part on quitte cette piece
						if (choix.size() == 0) {

							texte = "cette pi�ce ne peut pas se d�placer";

						} else {

							// r�afficher le terrain avec les d�placements possibles
							Fonctions.afficherInformations(this);

							System.out.print("faire un choix ou 0 pour annuler : ");
							String choixMouvement = Fonctions.giveString();

							// si le mouvement est l�gitime
							if (choix.containsKey(choixMouvement)) {
								// alors on �change + kill
								String kill = swap(piece, choix.get(choixMouvement));
								
								if(kill != "") {
									if(kill.charAt(0) == pblanc || kill.charAt(0) == dblanc) {
										this.getJoueurs()[0].getPieces().remove(Fonctions.trad(kill));
										texte = "je bouffe chez les blanc";
									}else {
										this.getJoueurs()[1].getPieces().remove(Fonctions.trad(kill));
										texte = "je bouffe chez les noir";
									}
								}
								
								joueur.setMonTour(false); // passage au tour de l'autre joueur

							}

						}
					}

				} // boucle infinie du joueur
			} // boucle des joueurs
		} // boucle infinie
	}

	private String swap(Piece piece, Point destination) {

		// �change la place entre la pi�ce et la case vide de destination
		System.out.println("piece : i = " + piece.getI() + " j = " + piece.getJ());
		System.out.println("destination : i = " + destination.getI() + " j = " + destination.getJ());

		piece.setI(destination.getI());
		piece.setJ(destination.getJ());
		
		String result = piece.getManger();
		piece.setManger("");
		return result;

	}

	public boolean isJouer() {
		return this.jouer;
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public void setGD(int gD) {
		GD = gD;
	}

	public void setHB(int hB) {
		HB = hB;
	}

	public char getPblanc() {
		return pblanc;
	}

	public void setPblanc(char pblanc) {
		this.pblanc = pblanc;
		joueurs[0].setPion(pblanc);
	}

	public char getDblanc() {
		return dblanc;
	}

	public void setDblanc(char dblanc) {
		this.dblanc = dblanc;
		joueurs[0].setDame(dblanc);
	}

	public char getPnoir() {
		return pnoir;
	}

	public void setPnoir(char pnoir) {
		this.pnoir = pnoir;
		joueurs[1].setPion(pnoir);
	}

	public char getDnoir() {
		return dnoir;
	}

	public void setDnoir(char dnoir) {
		this.dnoir = dnoir;
		joueurs[1].setDame(dnoir);
	}

	public String[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(String[][] terrain) {
		this.terrain = terrain;
	}

	public int getNbtours() {
		return nbtours;
	}

	public void setNbtours(int nbtours) {
		this.nbtours = nbtours;
	}

	public int getGD() {
		return GD;
	}

	public int getHB() {
		return HB;
	}

	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}

}
