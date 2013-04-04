package life;

import java.util.ArrayList;
/**
 * classe qui determine un cycle a l'etat "stable"
 * @author itzamma
 *
 */
public class LifeCyclicStable extends LIFE {

	public LifeCyclicStable(ArrayList<Cellule> raw) {
		super(raw);
	}

	public boolean hasNext() {
		return raw.size()>0;
	}

	public LIFE next() {
		return this;
	}

}
