package life;

import java.util.ArrayList;

import life.cell.Cellule;

public class Enterprise extends LifeCyclic implements Asymptotique {

	public Enterprise(ArrayList<Cellule> raw, ArrayList<ArrayList<Cellule>> h,
			Integer dx, Integer dy) {
		super(raw, h);
	}
	
}