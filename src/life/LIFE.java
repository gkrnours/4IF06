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

	public Set<Cellule> recupererVoisinage(Cellule c){
		Set<Cellule> s=new HashSet<Cellule>();
		s.add(c);
		for(int x=0;x<2;x++){
			for(int y=0;y<2;y++){
				if(existe(new Coord(c.x()+x,c.y()+y))){
					s.add(new Cellule(c.x()+x,c.y()+y));
				}
				if(existe(new Coord(c.x()-x,c.y()-y))){
					s.add(new Cellule(c.x()-x,c.y()-y));
				}if(existe(new Coord(c.x()+x,c.y()-y))){
					s.add(new Cellule(c.x()+x,c.y()-y));
				}if(existe(new Coord(c.x()-x,c.y()+y))){
					s.add(new Cellule(c.x()-x,c.y()+y));
				}
			}
		}
		
		return s;
	}
	public boolean existe(Coord c){
		return this.raw.contains(c);
	}
	public LIFE() {
		raw = new ArrayList<Cellule>();
		raw.add(new Cellule(0, 1));
		raw.add(new Cellule(1, 1));
		raw.add(new Cellule(6, 0));
		raw.add(new Cellule(1, 2));
		raw.add(new Cellule(5, 2));
		raw.add(new Cellule(7, 2));
		raw.add(new Cellule(6, 2));
		Collections.sort(raw);
		update();
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

	public boolean hasNext() { return true; }
	public void remove() {}
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}
}