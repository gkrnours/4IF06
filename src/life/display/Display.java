package life.display;

import life.LIFE;
import life.cell.Coord;

public interface Display {
	public abstract void show();
	public abstract void view(Coord topLeft, Coord bottomRight);
	public abstract void refresh();
	public abstract void update(LIFE life);
}
