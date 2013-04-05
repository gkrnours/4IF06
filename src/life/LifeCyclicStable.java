package life;

import java.util.ArrayList;

public class LifeCyclicStable extends LIFE {

	public LifeCyclicStable(ArrayList<Cellule> raw) {
		super(raw);
	}

	public boolean hasNext() {
		return false;
	}

	public LIFE next() {
		return this;
	}

}
