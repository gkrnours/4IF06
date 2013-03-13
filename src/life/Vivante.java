package life;

public class Vivante extends Cellule {

	public Vivante(Integer x, Integer y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean vivante() {
		return true;
	}

	@Override
	public Cellule next() {
		return (2 <= neighboor && neighboor <= 3)?this:null;
	}	
}
