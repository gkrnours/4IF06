package life;

public abstract class Display {
	protected LIFE life;
	public Display(LIFE life){
		this.life = life;
	}
	public abstract void show();
	public abstract void update();
}
