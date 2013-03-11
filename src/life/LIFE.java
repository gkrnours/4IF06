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
		x = Collections.min(raw, new Coord.compareX()).x();
		y = Collections.min(raw, new Coord.compareY()).y();
		w = Collections.max(raw, new Coord.compareX()).x() - x + 1;
		h = Collections.max(raw, new Coord.compareY()).y() - y + 1;
		d = (float) (raw.size() / (1. * w * h));
	}

	public LIFE(ArrayList<Cellule> cells) {
		raw = cells;
		Collections.sort(raw);
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
		Collections.sort(raw);
		update();
	}
	
	public Set<Cellule> recupererVoisinage(Cellule c) {
		System.out.println(c);
		int ax = c.x()-1, ay = c.y()-1, bx = c.x()+1, by = c.y() +1;
		Set<Cellule> s = new HashSet<Cellule>();
		for(Cellule d: raw){
			if(c.y() < d.y()+1) break; // on a dépassé les voisins
			if(d.isIn(ax, ay, bx, by)){
				System.out.println(d);
				s.add(d);
			}
		}
		c.setNeighboor(s.size());
		s.add(c);
		return s;
	}

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

	public boolean existe(Coord c) {
		return this.raw.contains(c);
	}

	
	public String toString() {
		return "LIFE [" + x + "/" + y + "] [" + w + "×" + h + ":" + d + "]";
	}

	public void debug() {
		for (Coord i : raw) {
			System.out.print("[" + i.x() + ":" + i.y() + "]  ");
		}
		System.out.println();
	}


	public boolean hasNext() {
		return true;
	}

	public void remove() {
	}

	public Object next() {
		Set<Cellule> work = new HashSet<Cellule>();
		for(Cellule c: raw){
			work.addAll(recupererVoisinage(c));
		}
		
		ArrayList<Cellule> r = new ArrayList<Cellule>();
		for(Cellule cell : work){
			r.add(cell.next());
		}
		raw = r;
		return this;
	}

}
