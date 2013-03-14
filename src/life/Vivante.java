package life;

public class Vivante extends Cellule {

	public Vivante(Integer x, Integer y) {
		super(x, y);
	}
	public Vivante(Coord c) {
		super(c);
	}

	@Override
	public boolean vivante() {
		return true;
	}
}
