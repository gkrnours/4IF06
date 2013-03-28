package life;
import java.util.*;

public class LifeCyclic extends LIFE {

	public LifeCyclic(ArrayList<Cellule> raw) {
		super(raw);
	}
	protected Integer periode;
	protected ArrayList<ArrayList<Cellule>> history;
	
}
