package life;

import java.util.Random;

public abstract class Cellule extends Coord {
	protected int neighboor;
	
	public void setNeighboor(int n){
		neighboor = Math.max(0, Math.min(8, n));
	}
	public Cellule(Integer x, Integer y) {
		super(x, y);
	}
	
	public abstract boolean vivante();
	public abstract Cellule next();

}
