package life;

import java.util.ArrayList;

public class LifePreCyclic extends LIFE {
	protected Integer periode = null;
	protected Integer current = null;
	protected Integer x;
	protected Integer y;
	private Integer origin_x;
	private Integer origin_y;
	protected ArrayList<ArrayList<Cellule>> history;
	
	public LifePreCyclic(ArrayList<Cellule> raw) {
		super(raw);
		this.origin_x = this.x;
		this.origin_y = this.y;
		this.history = new ArrayList<ArrayList<Cellule>>();
		history.add(raw);
	}
	
	@Override
	public LIFE next() {
		if (periode == null) {// c'est la premiere fois qu'on le rencontre
			super.next(); // pourquoi on fait letape suivante ????
			if (!history.get(0).equals(raw)) {
				history.add(raw);
			} 
			else {
				if(history.size() == 1){ // alors on a un stable
					return new LifeCyclicStable(raw);
				}
				if (origin_x == x && origin_y == y) {
					return new LifeCyclic(raw, history);
				}
				return new Enterprise(raw, history, this.x,this.y);
			}
			return this;
		}
		++current;
		raw = history.get(current);

		return this;
	}

	@Override
	public String toString() {
		return getClass().getName()+" [" + x + "/" + y + "] [" + w + "×" + h + ":" + d + "]";
	}
}
