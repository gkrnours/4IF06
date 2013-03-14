package life;

public class Morte extends Cellule {

	public Morte(Integer x, Integer y) {
		super(x, y);
	}
	public Morte(Coord c) {
		super(c);
	}

	@Override
	public boolean vivante() {
		return false;
	}
}
