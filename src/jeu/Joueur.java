package jeu;

import java.util.HashMap;

import piece.Dame;
import piece.Piece;
import piece.Pion;

public class Joueur {

	private String name;
	private String couleur;
	private char pion;
	private char dame;
	private Boolean controlByIA = true;
	private Boolean monTour = false;

	private HashMap<Integer, Piece> pieces = new HashMap<Integer, Piece>();

	// du a des soucis sur l'aléatoire, cette variable sert a récupérer le nombre de
	// piéces maximum
	private int taille;

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

	public String getAllInformations() {
		return "nom = " + name + " controlé par ia = " + controlByIA + "\ncouleur = " + couleur + " pion = " + pion
				+ " dame = " + dame + "\nnbr pieces = " + pieces.size() + "\n";// +fin;
	}

	public String getInformations() {
		return "nom = " + name + " couleur = " + couleur + "\npion = " + pion + " dame = " + dame + "\n nbr pieces = "
				+ pieces.size() + "\n";// +fin;
	}

	public void addPiece(Piece piece) {

		pieces.put(piece.getId(), piece);

	}

//  getters
	public Boolean getMonTour() {
		return monTour;
	}

	public String getCouleur() {
		return couleur;
	}

	public HashMap<Integer, Piece> getPieces() {
		return pieces;
	}

	public char getPion() {
		return pion;
	}

	public char getDame() {
		return dame;
	}

	public String getName() {
		return name;
	}

	public Boolean getControlByIA() {
		return controlByIA;
	}

	public int getTaille() {
		return taille;
	}

	// setters
	public void setMonTour(Boolean monTour) {
		this.monTour = monTour;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setControlByIA(Boolean controlByIA) {
		this.controlByIA = controlByIA;
	}

	public void setPion(char pblanc) {
		this.pion = pblanc;
	}

	public void setDame(char dame) {
		this.dame = dame;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

}
