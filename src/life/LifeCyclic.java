package life;

import java.util.*;

public class LifeCyclic extends LIFE {

	public LifeCyclic(ArrayList<Cellule> raw, Integer x, Integer y) {
		super(raw);
		this.x=x;
		this.y=y;
	}

	protected Integer periode = null;
	protected Integer current = null;
	protected Integer x;
	protected Integer y;
	//protected ArrayList<ArrayList<Cellule>> history;
	protected ArrayList<LifeCyclic> history;

	@Override
	public LIFE next() {
		if (periode == null) {// c'est la premiere fois qu'on le rencontre
			super.next(); // pourquoi on fait letape suivante ????
			if (!history.get(0).equals(raw)) {
				history.add(this);
			} else {
				periode = history.size();
				current = 0;
				if (!(x.equals(history.get(0).x)&&y.equals(history.get(0)))){ //s'ils n'ont pas le meme history, alors c'est un 
					return Enterprise e=new Enterprise(super.getRaw(),this.x,this.y);
				}
			}
			return this;
		}
		++current;
		raw = history.get(current).super.getRaw();

		return this;
	}

}
