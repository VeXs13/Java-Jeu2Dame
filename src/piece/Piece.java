package piece;

public class Piece {

	int id; //sera afficher pour combler le manque d'interface graphique
	int i;
	int j;
	String motif;
	String visage;

	public Piece(int id,char motif, int i, int j) {
		this.id = id;
		this.i = i;
		this.j = j;
		this.motif = Character.toString(motif);
		this.visage = motif + Integer.toString(id) + (id < 10 ? " " : "");
	}

	@Override
	public String toString() {
		return "id = "+id+" i = "+i+" j = " +j+"\n";
	}

	public String getVisage() {
		return visage;
	}
	
}