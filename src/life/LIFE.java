package life;

import java.util.ArrayList;
import java.util.Collections;

public class LIFE {
	protected Integer x; // horizontal position
	protected Integer y; // vertical position
	protected Integer w; // width
	protected Integer h; // height
	protected Float   d; // density
	protected ArrayList<Coord> raw; // list of living cell
	protected ArrayList<LIFE> shards;// list of LIFE composing this one
	
	private void update(){
		x = Collections.min(raw, new Coord.compareX() ).x();
		y = Collections.min(raw, new Coord.compareY() ).y();
		w = Collections.max(raw, new Coord.compareX() ).x()-x+1;
		h = Collections.max(raw, new Coord.compareY() ).y()-y+1;
		d = (float)(raw.size()/(1.*w*h));
	}
	public LIFE(){
		raw = new ArrayList<Coord>();
		raw.add(new Coord(0,1));
		raw.add(new Coord(1,1));
		raw.add(new Coord(6,0));
		raw.add(new Coord(1,2));
		raw.add(new Coord(5,2));
		raw.add(new Coord(7,2));
		raw.add(new Coord(6,2));
		Collections.sort(raw);
		update();
	}
	public String toString(){
		return "LIFE ["+x+"/"+y+"] ["+w+"×"+h+":"+d+"]";
	}
	public void debug(){
		for(Coord i: raw){
			System.out.println("["+i.x()+":"+i.y()+"]");
		}
	}
}