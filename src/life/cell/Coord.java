package life.cell;

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
	
	/**
	 * Verifie la presence d'une Coord aux coordonnées indiquees
	 * @param x
	 * @param y
	 * @return egalite des coordonnees
	 */
	public boolean isAt(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	/**
	 * Verifie la présence d'une Coord dans un rectangle compose par deux 
	 * autres Coord
	 * @param a
	 * @param b
	 * @return isIn(a.x(), a.y(), b.x(), b.y())
	 */
	public boolean isIn(Coord a, Coord b) {
		return isIn(a.x(), a.y(), b.x(), b.y());
	}
	
	/**
	 * Verifie la presence d'un point dans un rectangle forme par quatre autres 
	 * points
	 * @param ax
	 * @param ay
	 * @param bx
	 * @param by
	 * @return 
	 */
	public boolean isIn(int ax, int ay, int bx, int by) {
		return !(x < ax || y < ay
			||	bx < x || by < y );
	}
	/**
	 * @param other
	 * @return un entier negatif, zero, ou un entier positif suivant si l'objet est
	 * inferieur, egal ou superieur
	 */
	@Override
	public int compareTo(Coord other) {
		if (y.equals(other.y()))
			return this.x - other.x();
		else
			return this.y - other.y();
	}
	/**
	 * methode equals qui indique si les objets compares sont egaux a l'Objet c
	 * @return booléen: true si les 2 objets sont egaux, false sinon
	 */
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

	/**
	 * Comparateur de la coordonee x de deux Coord
	 * @author 
	 *
	 */
	public static class compareX implements Comparator<Coord> {
		public int compare(Coord a, Coord b) {
			return a.x() - b.x();
		}
	}

	/**
	 * Comparateur de la coordonee y de deux Coord
	 * @author 
	 *
	 */
	public static class compareY implements Comparator<Coord> {
		public int compare(Coord a, Coord b) {
			return a.y() - b.y();
		}
	}
}
