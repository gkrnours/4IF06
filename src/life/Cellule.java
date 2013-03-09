package life;

public class Cellule extends Coord {

	public Cellule(Integer x, Integer y) {
		super(x, y);
	}
	
	public boolean vivante(){
		return (this instanceof Vivante);
	}

}
