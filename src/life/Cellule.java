package life;

/**
 * classe Cellule qui determine l'état de la Cellule en question dans la coordonnee @Coord
 * @see Coord
 * @author Jimmy
 *
 */
public abstract class Cellule extends Coord {
	public Cellule(Integer x, Integer y) {
		super(x, y);
	}
		
	/**
	 * Constructeur
	 * @param c
	 */
	public Cellule(Coord c) {
		super(c);
	}
	/**
	 * 
	 * @return un booleen suivant l'etat de la Cellule
	 */
	public abstract boolean vivante();
}
