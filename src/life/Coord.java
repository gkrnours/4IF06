package life;

import java.util.Comparator;

public class Coord implements Comparable<Coord> {
	protected Integer x;
	protected Integer y;

	public Integer x() {
		return x;
	}
	public Integer y() {
		return y;
	}
	
	public Coord(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	public Coord(Coord c) {
		this.x = c.x();
		this.y = c.y();
	}
	
	public boolean isAt(int x, int y) {
		return this.x == x && this.y == y;
	}
	public boolean isIn(int ax, int ay, int bx, int by) {
		return !(x < ax || y < ay
			||	bx < x || by < y );
	}

	@Override
	public int compareTo(Coord other) {
		if (y.equals(other.y()))
			return this.x - other.x();
		else
			return this.y - other.y();
	}
	@Override
	public boolean equals(Object c) {
		if(!(c instanceof Coord)) return super.equals(c);
		return (x == ((Coord)c).x() && y == ((Coord)c).y());
	}
	@Override
	public int hashCode(){
		return (x+0xFFF)+y;
	}
	@Override
	public String toString(){
		String r = super.toString();
		return r+"["+x+";"+y+"]";
	}

	// Comparator
	public static class compareX implements Comparator<Coord> {
		public int compare(Coord a, Coord b) {
			return a.x() - b.x();
		}
	}

	public static class compareY implements Comparator<Coord> {
		public int compare(Coord a, Coord b) {
			return a.y() - b.y();
		}
	}
}
