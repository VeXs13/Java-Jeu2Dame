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
	Boolean monTour = false;

	HashMap<Integer, Piece> pieces = new HashMap<Integer, Piece>();

	public Joueur(String couleur, String name) {
		this.couleur = couleur;
		this.name = name;
	}

	public void addPiece(String type, char motif, int i, int j) {

		if (type == "pion")
			pieces.put(pieces.size() + 1, new Pion(pieces.size() + 1, motif, i, j));
		if (type == "dame")
			pieces.put(pieces.size() + 1, new Dame(pieces.size() + 1, motif, i, j));

	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getName() {
		return name;
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

	public String getInformations() {
		return "nom = " + name + "couleur = " + couleur + "\npion = " + pion
				+ " dame = " + dame + "\n nbr pieces = " + pieces.size() + "\n";// +fin;
	}

	public String getAllInformations() {
		return "nom = " + name + " controlé par ia = " + controlByIA + "\ncouleur = " + couleur + " pion = " + pion
				+ " dame = " + dame + "\nnbr pieces = " + pieces.size() + "\n";// +fin;
	}

	public Boolean getMonTour() {
		return monTour;
	}

	public void setMonTour(Boolean monTour) {
		this.monTour = monTour;
	}
	
	

}
