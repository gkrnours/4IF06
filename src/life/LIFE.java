package life;

import java.util.*;

public class LIFE implements Iterator {
	protected Integer x; // horizontal position
	protected Integer y; // vertical position
	protected Integer w; // width
	protected Integer h; // height
	protected Float d; // density
	protected ArrayList<Cellule> raw; // list of living cell
	protected ArrayList<LIFE> shards;// list of LIFE composing this one

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

	private void update() {
		Collections.sort(raw);
		x = Collections.min(raw, new Coord.compareX()).x();
		y = Collections.min(raw, new Coord.compareY()).y();
		w = Collections.max(raw, new Coord.compareX()).x() - x + 1;
		h = Collections.max(raw, new Coord.compareY()).y() - y + 1;
		d = (float) (raw.size() / (1. * w * h));
	}

	public LIFE(ArrayList<Cellule> cells) {
		raw = cells;
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
		Set<Cellule> s = new HashSet<Cellule>();
		if(c == null || raw.size() == 0){ return s; }
		int ax = c.x()-1, ay = c.y()-1, bx = c.x()+1, by = c.y() +1;
		int neighboor = 0;
		for(Cellule d: raw){
			if(c.y() < d.y()+1) break; // on a dépassé les voisins
			if(d.isIn(ax, ay, bx, by)){
				s.add(d);
				++neighboor;
			}
			s.add(new Morte(d.x(), d.y()));
		}
		c.setNeighboor(neighboor);
		s.add(c);
		return s;
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

	public Object next() {
		Set<Cellule> work = new HashSet<Cellule>();
		System.out.println("==== Work ====");
		for(Cellule c: raw){
			System.out.println("cell: "+c+"; ");
			work.addAll(recupererVoisinage(c));
		}
		System.out.println();
		for(Cellule c: raw){
			System.out.println("cell: "+c+"; ");
		}
		System.out.println();
		
		ArrayList<Cellule> r = new ArrayList<Cellule>();
		for(Cellule cell : work){
			r.add(cell.next());
		}
		System.out.println();
		for(Cellule c: work){
			System.out.println("cell: "+c+"; ");
		}
		r.removeAll(Collections.singleton(null));
		raw = r;
		return this;
	}
}