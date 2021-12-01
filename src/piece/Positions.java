package piece;

public class Positions {

	private int i;
	private int j;
	private String manger = "";
	
	public Positions(int i, int j) {
		this.i = i;
		this.j = j;
	}

	
	public Positions(int i, int j, String manger) {
		this.i = i;
		this.j = j;
		this.manger = manger;
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
	
	
	
	
	
}
