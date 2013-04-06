package life;

public class Frontiere extends Cellule {

	public Frontiere(Coord c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public Frontiere(Integer x, Integer y){
		super(x,y);
	}

	@Override
	public Cellule evolve(Integer voisin) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean vivante() {
		// TODO Auto-generated method stub
		return false;
	}

}
