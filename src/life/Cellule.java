package life;

import java.util.Random;

public class Cellule extends Coord {

	public Cellule(Integer x, Integer y) {
		super(x, y);
	}
	
	public boolean vivante(){
		return (this instanceof Vivante);
	}
		
	public Cellule next(){
		Random RND = new Random();
		return RND.nextBoolean()?this:null;
	}

}
