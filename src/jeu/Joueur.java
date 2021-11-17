package jeu;

import java.util.ArrayList;

import piece.Dame;
import piece.Piece;
import piece.Pion;


public class Joueur {

	String name;
	String couleur;
	char pion;
	char dame;
	
	ArrayList<Piece> piece = new ArrayList<Piece>();
	
	
	public Joueur(String couleur, char pion, char dame) {
		this.couleur = couleur;
		this.pion = pion;
		this.dame = dame;
		
		this.name = (couleur == "blanc" ? "sangoku" : "vegeta");
	}


	public void addPiece(String type,char motif, int i, int j) {
		
		if(type == "pion")piece.add(new Pion(piece.size()+1,motif,i,j));
		if(type == "dame")piece.add(new Dame(piece.size()+1,motif,i,j));
	}


	@Override
	public String toString() {
		
		String fin = "\n     pions+++++ \n";
		
		for(Object value:piece) {fin += value;}
		
		return "name = "+name+" couleur = "+couleur+" pion = "+Character.toString(pion)+" dame = "+Character.toString(dame)+fin;
	}


	public String getLastPiece() {
		
		return piece.get(piece.size()-1).getVisage();
	}

	

}
