package life;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class LIFE extends AllLife implements Iterator<LIFE>{
	protected Integer x; // horizontal position
	protected Integer y; // vertical position
	protected Integer w; // width
	protected Integer h; // height
	protected Float d; // density
	protected ArrayList<Cellule> raw; // list of living cell
	protected ArrayList<LIFE> shards;// list of LIFE composing this one
	protected Integer hashcode=hashcode();
	protected ArrayList<ArrayList<Cellule>> history;

	public Integer x() {
		return x;
	}

	public Integer y() {
		return y;
	}

	public Integer w() {
		return w;
	}

	public Integer h() {
		return h;
	}

	public Float d() {
		return d;
	}

	public Iterator<Cellule> raw() {
		return raw.iterator();
	}

	/**
	 * Mises à jour des coordonnées
	 */
	private void update() {
		if(raw.isEmpty()) return;
		Collections.sort(raw);
		x = Collections.min(raw, new Coord.compareX()).x();
		y = Collections.min(raw, new Coord.compareY()).y();
		w = Collections.max(raw, new Coord.compareX()).x() - x + 1;
		h = Collections.max(raw, new Coord.compareY()).y() - y + 1;
		d = (float) (raw.size() / (1. * w * h));
	}

	public LIFE(ArrayList<Cellule> cells) {
		raw = cells;
		history=new ArrayList<ArrayList<Cellule>>();
		update();
	}

	public LIFE() {
		raw = new ArrayList<Cellule>();
		raw.add(new Vivante(0, 1));
		raw.add(new Vivante(1, 1));
		raw.add(new Vivante(6, 0));
		raw.add(new Vivante(1, 2));
		raw.add(new Vivante(5, 2));
		raw.add(new Vivante(7, 2));
		raw.add(new Vivante(6, 2));
		update();
	}
	
	public Set<Cellule> recupererVoisinage(Cellule c) {
		int dx = c.x()-1; int dy = c.y()-1;
		
		Set<Cellule> s = new HashSet<Cellule>();
		for(int i=0; i<9; ++i){
			Cellule cell = getCell(new Coord(dx+(i%3), dy+(i/3)));
			s.add(cell);
		}
		return s;
	}

	//TODO supprimer la duplication de code
	public boolean alive(Cellule c) {
		int cmpt = 0;
		Set<Cellule> hs = recupererVoisinage(c);
		if (c instanceof Vivante) {
			for (Cellule cel : hs) {
				if (!(cel.equals(c))) {
					if (cel instanceof Vivante) {
						cmpt++;
					}
				}
			}
			return (cmpt == 2 || cmpt == 3);
		} else {
			for (Cellule cel : hs) {
				if (!(cel.equals(c))) {
					if (cel instanceof Vivante) {
						cmpt++;
					}
				}
			}
			return (cmpt == 3);
		}

	}

	//TODO remove
	public void unTour() {
		ArrayList<Cellule> al = new ArrayList<Cellule>();
		Set<Cellule> s=new HashSet<Cellule>();
		for (Cellule c : this.raw) {
			if (alive(c)) {
				al.add(new Vivante(c.x(), c.y()));
			} else
				al.add(new Morte(c.x(), c.y()));
			s=recupererVoisinage(c);
			for(Cellule cel: s){
				if (alive(c)) {
					al.add(new Vivante(cel.x(), cel.y()));
				} else
					al.add(new Morte(cel.x(), cel.y()));
			}
		}
	}

	public Cellule getCell(Coord c){
		int idx = raw.indexOf(c);
		if(idx == -1) return new Morte(c);
		else return raw.get(idx);
	}
	public boolean existe(Coord c) {
		return this.raw.contains(c);
	}

	
	public String toString() {
		return "LIFE [" + x + "/" + y + "] [" + w + "×" + h + ":" + d + "]";
	}

	public void debug() {
		for (Coord i : raw) {
			System.out.println("[" + i.x() + ":" + i.y() + "]  ");
		}
		System.out.println();
	}


	public boolean hasNext() {
		return 0 < raw.size();
	}

	public void remove() {
	}

	
	public LIFE next() {
		System.out.println(this+" "+this.hashCode());
		
		Set<Cellule> work = new HashSet<Cellule>();
		for(Cellule c: raw){
			work.addAll(recupererVoisinage(c));
		}
		
		ArrayList<Cellule> r = new ArrayList<Cellule>();
		for(Cellule cell : work){
			if(alive(cell))
				r.add(cell.vivante()?cell:new Vivante(cell));
		}
		raw = r;
		update();
		if(existeHashcode(this.hashcode)!=-1){
			
			if(!(history.contains(this.raw))){
				history.add(this.raw);
			}
			else 
				return new LifeCyclic(history.indexOf(this.raw),arrayListToSet(this.raw));
			
		}
		super.addAl(hashcode());
				
		return this;
	}
	
	public Set<Cellule> arrayListToSet(ArrayList<Cellule> Al){
		
		Set<Cellule> s=new HashSet<Cellule>();
		for(Cellule c: Al){
			s.add(c);
		}
		return s;
	}

	public Integer hashcode(){
		if(raw == null) return 0;
		//Integer t=0xFFF&raw.size();
		Integer hl=0xFFFFF&((this.w()+this.h()/2)*0x1000);
		//t=t+hl;
		Integer t=0;
		for(Cellule c: raw){
			t+=c.ha;
		}
		t=0xFFF&t;
		t/=raw.size();
		t+=hl;
		super.addAl(t);
		return t;
	}
}
