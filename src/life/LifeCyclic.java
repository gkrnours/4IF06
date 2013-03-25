package life;
import java.util.*;

public class LifeCyclic extends LIFE {

	protected Integer periode;
	protected Set<Cellule> history;
	
	public LifeCyclic(Integer p, Set<Cellule> s){
		periode=p;
		history=s;
	}
}
