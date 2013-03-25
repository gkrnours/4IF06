package life;

import java.util.Comparator;

public class Coord implements Comparable<Coord> {
	protected Integer x;
	protected Integer y;
	
	/**
	 * 
	 * @return
	 */
	public Integer x() {
		return x;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer y() {
		return y;
	}
	
	/**
	 * Constructeur
	 * @param x
	 * @param y
	 */
	public Coord(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructeur
	 * @param c
	 */
	public Coord(Coord c) {
		this.x = c.x();
		this.y = c.y();
	}
	
	public boolean isAt(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public boolean isIn(Coord a, Coord b) {
		return isIn(a.x(), a.y(), b.x(), b.y());
	}
	
	public boolean isIn(int ax, int ay, int bx, int by) {
		return !(x < ax || y < ay
			||	bx < x || by < y );
	}
	/**
	 * @param other
	 * @return un entier négatif, zero, ou un entier positive suivant si l'objet est
	 * inférieur, égal ou supérieur
	 */
	@Override
	public int compareTo(Coord other) {
		if (y.equals(other.y()))
			return this.x - other.x();
		else
			return this.y - other.y();
	}
	/**
	 * méthode equals qui indique si les objets comparés sont sont égaux à l'Objet c
	 * @return booléen: true si les 2 objets sont égaux, false sinon
	 */
	@Override
	public boolean equals(Object c) {
		if(!(c instanceof Coord)) return super.equals(c);
		return (x == ((Coord)c).x() && y == ((Coord)c).y());
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode(){
		return (x+0xFFF)+y;
	}
	
	/**
	 * toString()
	 * @return la représentation de l'objet sous forme de chaîne de caractères
	 */
	@Override
	public String toString(){
		String r = super.toString();
		return r+"["+x+";"+y+"]";
	}

	/**
	 * Comparateur
	 * @author 
	 *
	 */
	public static class compareX implements Comparator<Coord> {
		public int compare(Coord a, Coord b) {
			return a.x() - b.x();
		}
	}

	/**
	 * Comparateur
	 * @author 
	 *
	 */
	public static class compareY implements Comparator<Coord> {
		public int compare(Coord a, Coord b) {
			return a.y() - b.y();
		}
	}
}
