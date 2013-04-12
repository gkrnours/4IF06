package life.cell;


/**
 * Classe qui definit l'etat d'une @Cellule en vivante
 * @author Jimmy
 *
 */
public class Vivante extends Cellule {

	private Integer[] vivant={2,3};
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
	
	public static void majVivant(){
		//msie a jour du tableau vivant
	}
	@Override
	public Cellule evolve(Integer voisin){
		if(voisin==vivant[0] || voisin==vivant[1])
			return this;
		else return new Morte(x(),y());
	}
	/**
	 *@return true car nous somme dans la classe Vivante
	 */
	@Override
	public boolean vivante() {
		return true;
	}

}
