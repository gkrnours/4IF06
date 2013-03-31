package life;

import java.util.*;

public class LifeCyclic extends LIFE {

	public LifeCyclic(ArrayList<Cellule> raw, Integer x, Integer y) {
		super(raw);
		this.x=x;
		this.y=y;
		this.origin_x=x;
		this.origin_y=y;
	}

	protected Integer periode = null;
	protected Integer current = null;
	protected Integer x;
	protected Integer y;
	private Integer origin_x;
	private Integer origin_y;
	protected ArrayList<ArrayList<Cellule>> history;

	@Override
	public LIFE next() {
		if (periode == null) {// c'est la premiere fois qu'on le rencontre
			super.next(); // pourquoi on fait letape suivante ????
			if (!history.get(0).equals(raw)) {
				history.add(raw);
			} else {
				periode = history.size();
				current = 0;
				if (!(origin_x.equals(x) && origin_y.equals(y))){ 
					return new Enterprise(raw,this.x,this.y);
				}
			}
			return this;
		}
		++current;
		raw = history.get(current);

		return this;
	}

}
