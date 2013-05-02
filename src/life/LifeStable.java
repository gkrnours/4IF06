package life;

import java.util.ArrayList;

import life.cell.Cellule;
/**
 * classe qui determine un cycle a l'etat "stable"
 * @author itzamma
 *
 */
public class LifeStable extends LIFE implements Asymptotique{

	public LifeStable(ArrayList<Cellule> raw) {
		super(raw);
	}
	
	public String rapport(){
		return "Ce LIFE est stable. Il ne changeras plus.";
	}

	public boolean hasNext() {
		return false;
	}

	public LIFE next() {
		return this;
	}

}
