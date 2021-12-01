package jeu;

import java.util.ArrayList;
import java.util.HashMap;

import main.Sauvegarde;
import piece.Dame;
import piece.Piece;
import piece.Positions;
import utilitaire.Fonctions;

public class Plateau {

	private Sauvegarde sauvegarde = new Sauvegarde();

	private int HB, GD;
	private String caseDefaut = " . ";

	private String[][] terrain;

	private char pblanc, dblanc, pnoir, dnoir;

	private Joueur[] joueurs = { new Joueur("blanc", "blancIA"), new Joueur("noir", "noirIA") };

	private int nbTours = 1;
	private boolean jouer = false;
	private String texteErreur;

	private ArrayList<String> deroulement;
	private String recupererLeCoup;

	public void start() {

		// crée les fichiers nécessaires
		sauvegarde.init(this);

		// if(true) {return;}

		System.out.println("c'est l'heure du tutututu duel");

		while (jouer) {// boucle infinie
			
			terrain = Fonctions.remplirTerrain(HB, GD, caseDefaut, joueurs);// remettre la tableau a plat
			deroulement = Fonctions.recupererTerrain(terrain, nbTours);

			for (int i = 0; i < 2; i++) {// boucle des joueurs

				//recupere les coups joues pour l'enregistrement
				recupererLeCoup = "";
				
				Joueur joueur = joueurs[i];
				joueur.setMonTour(true);
				boolean controlIA = joueur.getControlByIA();
				int taille = joueur.getTaille();
				Joueur joueurAdverse = joueurs[(i + 1) % 2];

				while (joueur.getMonTour()) {// tant que c'est le tour du joueur

					terrain = Fonctions.remplirTerrain(HB, GD, caseDefaut, joueurs);// remettre la tableau a plat

					Fonctions.afficherInformations(this, texteErreur);// aficher le plateau avec l'éventuel "erreur"
																		// commise
					texteErreur = "";

					// vérifier si il lui reste des pions
					if (Fonctions.aucunDeplacementPossible(terrain, HB, GD, joueur, joueurAdverse)) {

						try {
							deroulement.add(Fonctions.presenter(recupererLeCoup));
							sauvegarde.addHistorique(deroulement);
							Sauvegarde.gagner(joueurAdverse, joueur);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return;
					}

					System.out.print("tour du joueur : " + joueur.getName()
							+ "\n'0' pour abandonner\nsélectionner une piéce : ");

					int choixPiece = Fonctions.choixPiece(controlIA, taille);

					// System.out.println("\n-----------choixPiece----------------\n" + choixPiece);

					// fonction d'abanddon / match null
					if (choixPiece == 0) {
						try {
							switch (Fonctions.abanddon(joueur)) {

							case "abandon":

								Sauvegarde.abandon(joueur, joueurAdverse);
								deroulement.add(Fonctions.presenter(recupererLeCoup));
								sauvegarde.addHistorique(deroulement);
								return;

							case "nul":
								
								Sauvegarde.nul(joueur, joueurAdverse);
								deroulement.add(Fonctions.presenter(recupererLeCoup));
								sauvegarde.addHistorique(deroulement);
								return;
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					if (!joueur.getPieces().containsKey(choixPiece)) {

						texteErreur = "cette piéce n'existe pas";
						continue;
					}

					Piece piece = joueur.getPieces().get(choixPiece);

					if (!deplacementLibre(true, piece, joueur, joueurAdverse)) {
						continue;
					};

					evolution(piece, HB);

					joueur.setMonTour(false);
					
					deroulement.add(Fonctions.presenter(recupererLeCoup));
					
					if (joueur.getCouleur() == "noir") {
						nbTours++;
						try {
							sauvegarde.addHistorique(deroulement);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} // tant que c'est le tour du joueur

			} // boucle des joueurs

		} // boucle infinie

	}

	private boolean deplacementLibre(boolean libre, Piece piece, Joueur j, Joueur jA) {

		terrain = Fonctions.remplirTerrain(HB, GD, caseDefaut, joueurs);// remettre la tableau a plat
		Fonctions.afficherInformations(this, texteErreur);// aficher le plateau

		HashMap<String, Positions> choixPossible;

		if (libre) {
			choixPossible = piece.deplacementsPossibles(terrain, j, HB, GD, jA.getPion(), jA.getDame());
		} else {
			choixPossible = piece.deplacementsTueur(terrain, j, HB, GD, jA.getPion(), jA.getDame());
		}

		if (choixPossible == null || choixPossible.size() == 0) {

			texteErreur = "cette piéce ne peut pas se déplacer";
			if (libre) {
				return false;
			}
			return true;
		}

		// réafficher le terrain avec les déplacements possibles
		Fonctions.afficherInformations(this);

		System.out.print("faire un choix ou 0 pour annuler : ");

		String choixMouvement;

		choixMouvement = Fonctions.choixMouvement(j.getControlByIA(), choixPossible.size());

		if (!choixPossible.containsKey(choixMouvement)) {
			texteErreur = "mouvement introuvable";
			if (libre) {
				return false;
			}
			return true;
		}

		// alors on échange + piece a tuer kill
		System.out.println("\npiece : id = " + piece.getId() + " i = " + piece.getI() + " j = " + piece.getJ());
		System.out.println("destination : i = " + choixPossible.get(choixMouvement).getI() + " j = "
				+ choixPossible.get(choixMouvement).getJ());
		
		recupererLeCoup += Fonctions.traductionMouvement(piece,choixPossible.get(choixMouvement));

		String kill = swap(piece, choixPossible.get(choixMouvement));

		if (kill != "") {
			jA.getPieces().remove(Fonctions.trad(kill));
			deplacementLibre(false, piece, j, jA);
		}

		return true;

	}

	private String swap(Piece piece, Positions destination) {

		// échange la place entre la piéce et la case vide de destination

		piece.setI(destination.getI());
		piece.setJ(destination.getJ());

		String result = destination.getManger();
		return result;

	}

	private void evolution(Piece piece, int hb) {

		if (piece.getMotif() == pblanc && piece.getI() == hb - 1) {
			System.out.println("pion blanc evolue : " + piece.getVisage() + "  " + piece.getId());

			joueurs[0].getPieces().remove(piece.getId());

			Piece inter = new Dame(piece.getId(), dblanc, piece.getI(), piece.getJ());

			System.out.println("dame : " + inter.getId());

			joueurs[0].addPiece(inter);
			return;
		}

		if (piece.getMotif() == pnoir && piece.getI() == 0) {
			System.out.println("pion noir evolue : " + piece.getVisage() + "  " + piece.getId());

			joueurs[1].getPieces().remove(piece.getId());

			Piece inter = new Dame(piece.getId(), dnoir, piece.getI(), piece.getJ());

			System.out.println("dame : " + inter.getId());

			joueurs[1].addPiece(inter);
		}

	}

	// getters
	public String[][] getTerrain() {
		return terrain;
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public int getNbTours() {
		return nbTours;
	}

	public boolean isJouer() {
		return jouer;
	}

	public int getHB() {
		return HB;
	}

	public int getGD() {
		return GD;
	}

	public char getPblanc() {
		return pblanc;
	}

	public char getDblanc() {
		return dblanc;
	}

	public char getPnoir() {
		return pnoir;
	}

	public char getDnoir() {
		return dnoir;
	}

	// setters
	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public void setHB(int hB) {
		HB = hB;
	}

	public void setGD(int gD) {
		GD = gD;
	}

	public void setPblanc(char pblanc) {
		this.pblanc = pblanc;
	}

	public void setDblanc(char dblanc) {
		this.dblanc = dblanc;
	}

	public void setPnoir(char pnoir) {
		this.pnoir = pnoir;
	}

	public void setDnoir(char dnoir) {
		this.dnoir = dnoir;
	}

	public void setTerrain(String[][] terrain) {
		this.terrain = terrain;
	}

}
