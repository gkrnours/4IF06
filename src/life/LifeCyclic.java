package life;

import java.util.*;

public class LifeCyclic extends LIFE {

	public LifeCyclic(ArrayList<Cellule> raw) {
		super(raw);
	}

	protected Integer periode = null;
	protected Integer current = null;
	protected ArrayList<ArrayList<Cellule>> history;

	public boolean sameHistory(ArrayList<Cellule> al1,ArrayList<Cellule> al2){
		if(al1.isEmpty()) return true;
		else{
			if(!al1.get(0).equals(al2.get(0)))
				return false;
			else{
				
				return sameHistory((ArrayList)al1.subList(0,0),(ArrayList)al2.subList(0,0));
			}
		}
	}

	@Override
	public LIFE next() {
		if (periode == null) {// c'est la premiere fois qu'on le rencontre
			super.next(); // pourquoi on fait letape suivante ????
			if (!history.get(0).equals(raw)) {
				history.add(raw);
			} else {
				periode = history.size();
				current = 0;
				ArrayList<Cellule> al1 = history.get(0);
				ArrayList<Cellule> al2 = raw.get(0);
				if (!sameHistory(al1,al2)) { //s'ils n'ont pas le meme history, alors c'est un 
					return new Enterprise(this);
				}
			}
			return this;
		}
		++current;
		raw = history.get(current);

		return this;
	}

}
