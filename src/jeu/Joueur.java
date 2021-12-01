package jeu;

import java.util.HashMap;

import piece.Dame;
import piece.Piece;
import piece.Pion;

public class Joueur {

	String name;
	String couleur;
	String pion;
	String dame;
	Boolean controlByIA = true;
	
	HashMap<Integer, Piece> pieces = new HashMap<Integer, Piece>();

	public Joueur(String couleur, String name) {
		this.couleur = couleur;
		this.name = name;
	}
	
	public void addPiece(String type, char motif, int i, int j) {

		if (type == "pion") pieces.put(pieces.size() + 1, new Pion(pieces.size() + 1, motif, i, j));
		if (type == "dame") pieces.put(pieces.size() + 1, new Dame(pieces.size() + 1, motif, i, j));

	}

	public void setName(String name) {
		this.name = name;
	}

	public void setControlByIA(Boolean controlByIA) {
		this.controlByIA = controlByIA;
	}

	public void setPion(char pion) {
		this.pion = Character.toString(pion);
	}
	
	public void setDame(char dame) {
		this.dame = Character.toString(dame);
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public String getPion() {
		return pion;
	}

	public String getDame() {
		return dame;
	}
	
	public HashMap<Integer, Piece> getPieces() {
		return pieces;
	}


	

}
