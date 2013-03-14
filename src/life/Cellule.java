package life;

public abstract class Cellule extends Coord {
	public Cellule(Integer x, Integer y) {
		super(x, y);
	}
	public Cellule(Coord c) {
		super(c);
	}
	
	public abstract boolean vivante();
}
