package life;

/**
 * Classe qui definit l'etat d'une @Cellule en morte
 * @author Jimmy
 *
 */
public class Morte extends Cellule {
	
	/**
	 * Constructeur
	 * @param x
	 * @param y
	 */
	public Morte(Integer x, Integer y) {
		super(x, y);
	}
	
	/**
	 * 
	 * @param c
	 */
	public Morte(Coord c) {
		super(c);
	}
	
	/**
	 * @return false car nous sommes dans la classe Morte
	 */
	@Override
	public boolean vivante() {
		return false;
	}
}
