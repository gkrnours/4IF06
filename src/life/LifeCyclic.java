package life;

import java.util.ArrayList;

import life.cell.Cellule;
/**
 * classe qui determine un cycle non-stable
 *
 */
public class LifeCyclic extends LIFE implements Asymptotique {
	private ArrayList<ArrayList<Cellule>> history;
	private Integer current;
	private Integer periode;
	
	public LifeCyclic(ArrayList<Cellule> raw, ArrayList<ArrayList<Cellule>> history) {
		super(raw);
		this.history = history;
		this.current = 0;
		this.periode = history.size();
	}
	
	public String rapport(){
		return "Ce LIFE est cyclique de période "+this.periode;
	}

	public LIFE next(){
		current = (current+1) % periode;
		raw = history.get(current);
		return this;
	}
	
}
