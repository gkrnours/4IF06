package life;

public abstract class Display {
	protected LIFE life;
	public int i = 0;
	
	public Display(LIFE life){
		this.life = life;
	}
	public void newLife(LIFE life){
		this.life = life;
	}

	/**
	 *  
	 */
	public abstract void show();// affiche la surface
	public abstract void show(Coord topLeft, Coord bottomRight);
	public abstract void update(); // maj
	public abstract void view();// recadre la caméra
	public abstract void view(Coord topLeft, Coord bottomRight);

}
