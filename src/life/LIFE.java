package life;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import life.cell.Cellule;
import life.cell.Coord;
import life.cell.Morte;
enum Type {NORMAL, FRONTIER, SPHERE};

public class LIFE extends AllLife implements Iterator<LIFE> {
	protected Type type; 
	protected Coord topLeft;
	protected Coord bottomRight;
	protected Integer x; // horizontal position
	protected Integer y; // vertical position
	protected Integer w; // width
	protected Integer h; // height
	protected Float d; // density
	protected ArrayList<Cellule> raw; // list of living cell
	protected ArrayList<LIFE> shards;// list of LIFE composing this one
	protected Set<ArrayList<Cellule>> history;

	public Integer x() {
		return x;
	}

	public Integer y() {
		return y;
	}

	public Integer w() {
		return w;
	}

	public Integer h() {
		return h;
	}

	public Float d() {
		return d;
	}

	public Iterator<Cellule> raw() {
		return raw.iterator();
	}

	/**
	 * constructeur
	 * 
	 * @param cells
	 */
	public LIFE(ArrayList<Cellule> cells) {
		raw = cells;
		history = new HashSet<ArrayList<Cellule>>();
		update();
	}
	
	/**
	 * Mise a jour des coordonnees
	 */
	private void update() {
		if (raw.isEmpty())
			return;
		Collections.sort(raw);
		x = Collections.min(raw, new Coord.compareX()).x();
		y = Collections.min(raw, new Coord.compareY()).y();
		w = Collections.max(raw, new Coord.compareX()).x() - x + 1;
		h = Collections.max(raw, new Coord.compareY()).y() - y + 1;
		d = (float) (raw.size() / (1. * w * h));
	}
	
	/**
	 * Creation d'un Set contenant les voisines de la Cellule c et c
	 * @param c
	 * @return un Set de Cellules
	 */
	public Set<Cellule> recupererVoisinage(Cellule c) {
		Coord[] todo = {
				new Coord(c.x()-1, c.y()-1),
				new Coord(c.x()-1, c.y()  ),
				new Coord(c.x()-1, c.y()+1),
				new Coord(c.x(),   c.y()-1),
			//	new Coord(c.x(),   c.y()  ), // break the code
				new Coord(c.x(),   c.y()+1),
				new Coord(c.x()+1, c.y()-1),
				new Coord(c.x()+1, c.y()  ),
				new Coord(c.x()+1, c.y()+1),
		};
		ArrayList<Cellule> tmp = new ArrayList<>();
		for(Cellule cell: raw){
			if(cell.compareTo(todo[0]) < 0) continue;
			if(0 < cell.compareTo(todo[7]) ) break;
			tmp.add(cell);
		}
		Set<Cellule> s = new HashSet<Cellule>();

		for(Coord t: todo){
			boolean morte = true;
			for(Cellule cell: tmp){
				if(t.equals(cell)){
					s.add(cell);
					morte = false;
					break;
				}
			}
			if(morte) s.add(new Morte(t));
		}
		return s;
	}

	/**
	 * Compte le nombre de voisin d'une Cellule
	 * @param c
	 * @return nombre de voisins d'une Cellule
	 */
	public int nbVoisin(Cellule c){
		int cmpt = 0;
		Set<Cellule> hs = recupererVoisinage(c);
		for (Cellule cel : hs) {
			if ((cel.equals(c))){
				// On compte seulement le nombre de voisin
				// On ne prend pas en compte la cellule elle-même
				continue;
			}
			if (cel.vivante()) {
				
				++cmpt;
			}
		}
		return cmpt;
	}

	/**
	 * Renvoie une Cellule a condition qu'elle existe
	 * @param c
	 * @return la Cellule c si elle est dans raw, une morte sinon
	*/
	public Cellule getCell(Coord c) {
		int idx = raw.indexOf(c);
		if (idx == -1)
			return new Morte(c);
		else
			return raw.get(idx);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String toString() {
		HashMap<Class, String> name = new HashMap<Class, String>();
		name.put(LIFE.class, "Normal");
		name.put(LifePreCyclic.class, "Asymptotique");
		name.put(LifeCyclic.class, "Cyclique");
		String ret=name.get(getClass());
		return ret+" [" + x + "/" + y + "]" +
				" [" + w + "×" + h + ":" + raw.size() + "]";
	}

	/**
	 * Verifie la presence d'une etape suivante
	 * return la positivite de raw.size
	 */
	public boolean hasNext() {
		return 0 < raw.size();
	}

	public void remove() {
	}

	public LIFE next() {
		Set<Cellule> work = new HashSet<Cellule>();
		for (Cellule c : raw) {
			work.addAll(recupererVoisinage(c));
		}
		ArrayList<Cellule> r = new ArrayList<Cellule>();
		for (Cellule cell : work) {
			Cellule tmp = cell.evolve(nbVoisin(cell));
			if(!(tmp instanceof Morte)){
				r.add(tmp);
			}
		}
		raw = r;
		update();
		if (existeHashcode(this.hashCode()) != -1) {
			if (!(history.contains(this.raw))) {
				history.add(this.raw);
			} else
				return new LifePreCyclic(this.raw);
		} else {
			addAl(this.hashCode());
		}
		return this;
	}

	/**
	 * Cree un hashcode correspondant au nombre et a la position des Cellules
	 * @return un hashcode
	 */
	@Override
	public int hashCode() {
		if (raw == null)
			return 0;
		Integer hl = (w() + h());
		Integer t = 0;
		for (Cellule c : raw) {
			t += c.hashCode() % 0xFFFFFF;
		}
		t += hl * 0x1000000;
		return t;
	}
}