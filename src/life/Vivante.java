package life;

/**
 * Classe qui définit l'état d'une @Cellule en vivante
 * @author Jimmy
 *
 */
public class Vivante extends Cellule {

	/**
	 * constructeur
	 * @param x
	 * @param y
	 */
	public Vivante(Integer x, Integer y) {
		super(x, y);
	}
	
	/**
	 * 
	 * @param c
	 */
	public Vivante(Coord c) {
		super(c);
	}
	
	
	@Override
	public Cellule evolve(Integer voisin){
		return null;
	}
	/**
	 *@return true car nous somme dans la classe Vivante
	 */
	@Override
	public boolean vivante() {
		return true;
	}

}
