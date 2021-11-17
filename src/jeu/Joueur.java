package jeu;

import java.util.ArrayList;
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
	
	HashMap<Integer,Piece> pieces = new HashMap<Integer,Piece>();
	ArrayList<Piece> piece = new ArrayList<Piece>();

	public Joueur(String couleur, String name) {
		this.couleur = couleur;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getControlByIA() {
		return controlByIA;
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
	
	public String getLastPiece() {	
		//return piece.get(piece.size()-1).getVisage();
		return pieces.get(pieces.size()).getVisage();
	}

	public void addPiece(String type,char motif, int i, int j) {
		
		if(type == "pion")piece.add(new Pion(piece.size()+1,motif,i,j));
		if(type == "dame")piece.add(new Dame(piece.size()+1,motif,i,j));
		
		if(type == "pion")pieces.put(pieces.size()+1, new Pion(pieces.size()+1,motif,i,j));
		if(type == "dame")pieces.put(pieces.size()+1, new Dame(pieces.size()+1,motif,i,j));
		
		//piece.add(new Piece(piece.size()+1,motif,i,j));
		//pieces.put(pieces.size()+1, new Piece(pieces.size()+1,motif,i,j));
	}
	
	@Override
	public String toString() {
		
		//String fin = "\n\n	pieces tableau\n";
		
		//for(Object value:piece) {fin += value;}
		
		//fin += "\n	pieces map\n";
		
		//for(Entry<Integer, Piece> entry : pieces.entrySet()) {
		//	fin += entry.getKey() + " " + entry.getValue().toString();
		//}
		
		return "nom = "+name+" controlé par ia = "+controlByIA+"\ncouleur = "+couleur+" pion = "+pion+" dame = "+dame+"\n";//+fin;
	}
}
