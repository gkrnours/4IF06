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

	@Override
	public int compareTo(Coord other) {
		if (y.equals(other.y()))
			return this.x - other.x();
		else
			return this.y - other.y();
	}

	public boolean isAt(int x, int y) {
		return this.x == x && this.y == y;
	}
	public boolean isIn(int ax, int ay, int bx, int by) {
		return !(
				x < ax 
			||	y < ay
			||	bx < x
			||	by < y
				);
	}

	public Coord(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Coord c) {
		return (x == c.x() && y == c.y());
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
	public String toString(){
		String r = super.toString();
		return r+"["+x+";"+y+"]";
	}
}
