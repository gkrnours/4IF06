package life;

import java.util.ArrayList;

/**
 * Classe abstraite qui stock les Hashcodes et les range par ordre croissant de tours
 *
 */
public class AllLife {
	protected ArrayList<Integer> Al;

	public AllLife() {
		Al = new ArrayList<Integer>();
	}

	public void addAl(Integer n) {
		Al.add(Al.size(), n);
	}
	
	public void printAl(){
		for(Integer i: Al){
			System.out.print(Al.indexOf(i)+" : "+i+" // ");
		}
		System.out.println();
	}
	public int existeHashcode(Integer n){
		return Al.indexOf(n);
	}
}
