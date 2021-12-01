package piece;

import java.util.HashMap;

import jeu.Joueur;

public abstract class Piece {

	protected int id;
	protected int i;
	protected int j;

	protected char motif;
	protected String visage; // sera afficher pour combler le manque d'interface graphique

	public Piece(int id, char motif, int i, int j) {
		this.id = id;
		this.motif = motif;
		this.i = i;
		this.j = j;
		this.visage = motif + Integer.toString(id) + (id < 10 ? " " : "");
	}

	public abstract HashMap<String, Positions> deplacementsPossibles(String[][] terrain, Joueur joueur, int hb, int gd,
			char pionAdverse, char dameAdverse);

	public abstract HashMap<String, Positions> deplacementsTueur(String[][] terrain, Joueur joueur, int hB, int gD,
			char pnoir, char dnoir);

	// getters
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public String getVisage() {
		return visage;
	}

	public char getMotif() {
		return motif;
	}

	public int getId() {
		return id;
	}

	// setters
	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}

}
