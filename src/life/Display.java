package life;

public abstract class Display {
	protected LIFE life;
	public Display(LIFE life){
		this.life = life;
	}
	public abstract void show();
	public abstract void show(Coord topLeft, Coord bottomRight);
	public abstract void update();
	public abstract void view();
	public abstract void view(Coord topLeft, Coord bottomRight);

}
