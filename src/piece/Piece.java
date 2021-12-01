package piece;

import java.util.HashMap;

import jeu.Joueur;

public abstract class Piece {

	int id;
	int i;
	int j;
	
	char motif;
	String visage; //sera afficher pour combler le manque d'interface graphique
	
	String manger = "";
	Boolean mort = false;

	public Piece(int id,char motif, int i, int j) {
		this.id = id;
		this.motif = motif;
		this.i = i;
		this.j = j;
		this.visage = motif + Integer.toString(id) + (id < 10 ? " " : "");
	}

	public String getVisage() {
		return visage;
	}
	
	public abstract HashMap<String, Point> deplacementsPossibles(String[][] terrain, Joueur joueur,int hb,int gd, char pionAdverse, char dameAdverse);
	
	@Override
	public String toString() {
		return "motif = "+motif+" id = "+id+" i = "+i+" j = " +j+" manger = "+manger+"\n";
	}

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	
	
	public String getManger() {
		return manger;
	}
	public void setManger(String manger) {
		this.manger = manger;
	}

	public Boolean getMort() {
		return mort;	
	}
	public void setMort(Boolean mort) {
		this.mort = mort;
	}
}
