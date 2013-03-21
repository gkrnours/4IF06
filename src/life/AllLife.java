package life;

import java.util.*;

/**
 * Classe abstraite qui stock les Hashcodes et les range par ordre croissant de tours
 *
 */
public class AllLife {
	private ArrayList<Integer> Al;

	public AllLife() {
		Al = new ArrayList<Integer>();
	}

	public void addAl(Integer n) {
		Al.add(Al.size(), n);
	}
	
	public int existeHashcode(Integer n){
		return Al.indexOf(n);
	}
}
