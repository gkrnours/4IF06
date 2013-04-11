package life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
	 * Creation d'un Set contenant les voisines de la Cellule c et c
	 * @param c
	 * @return un Set de Cellules
	 */
	public Set<Cellule> recupererVoisinage(Cellule c) {
		Coord[] todo = {
				new Coord(c.x()-1, c.y()-1),
				new Coord(c.x()-1, c.y()),
				new Coord(c.x()-1, c.y()+1),
				new Coord(c.x(),   c.y()-1),
				
				new Coord(c.x(),   c.y()+1),
				new Coord(c.x()+1, c.y()-1),
				new Coord(c.x()+1, c.y()),
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
	 * 
	 * @param c
	 * @return retourne vrai si la Cellule c peut vivre, faux sinon
	 */
	// TODO supprimer la duplication de code
	public boolean alive(Cellule c) {
		int cmpt = 0;
		Set<Cellule> hs = recupererVoisinage(c);
		for (Cellule cel : hs) {
			if (cel.equals(c)) continue;
			if(cel.vivante()) ++cmpt;
		}
		return c.evolve(cmpt).vivante();
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

	/**
	 * Controle la presence d'une Coord dans raw
	 * @param c
	 * @return la presence dans raw
	 */
	public boolean existe(Coord c) {
		return this.raw.contains(c);
	}

	@Override
	public String toString() {
		HashMap<Class, String> name = new HashMap<Class, String>();
		name.put(LIFE.class, "Normal");
		name.put(LifePreCyclic.class, "Asymptotique");
		name.put(LifeCyclic.class, "Cyclique");
		String ret=name.get(getClass());
		return ret+" [" + x + "/" + y + "] [" + w + "×" + h + ":" + d + "]";
	}

	public void debug() {
		for (Coord i : raw) {
			System.out.println("[" + i.x() + ":" + i.y() + "]  ");
		}
		System.out.println();
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
		if (existeHashcode(this.hashcode()) != -1) {
			if (!(history.contains(this.raw))) {
				history.add(this.raw);
			} else
				return new LifePreCyclic(this.raw);
		} else {
			addAl(this.hashcode());
		}
		//printAl();
		return this;
	}

	/**
	 * Converti une ArrayList en Set
	 * @param Al
	 * @return un Set
	 */
	public Set<Cellule> arrayListToSet(ArrayList<Cellule> Al) {

		Set<Cellule> s = new HashSet<Cellule>();
		for (Cellule c : Al) {
			s.add(c);
		}
		return s;
	}
	
	/**
	 * CRee un hashcode correspondant au nombre et a la position des Cellules
	 * @return un hashcode
	 */
	public Integer hashcode() {
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
	
	/**
	 * Compte le nombre de voisin d'une Cellule
	 * @param c
	 * @return nombre de voisins d'une Cellule
	 */
	public Integer nbVoisin(Cellule c){
		int cmpt = 0;
		Set<Cellule> hs = recupererVoisinage(c);
			for (Cellule cel : hs) {
				if (!(cel.equals(c))) {
					if (cel instanceof Vivante) {
						cmpt++;
					}
				}
			}
			return cmpt;
	}
	/*
	//retourne vrai si une cellule est sur la bordure du tableau de jeu
	public boolean surBordure(Cellule c,int taille){
		return (c.x()==0 || c.x()==taille || c.y()==0 || c.y()==taille);
	}
	
	//taille=taille du tableau de jeu circulaire
	//retourne vrai si une cellule peut vivre dans un tableau circulaire
	public boolean aliveCirculaire(int taille,Cellule c){
		int cmpt=0;
		if(!(surBordure(c,taille))){
			return alive(c);
		}
		
		if(c.x()==x()){
			if(c.y()==y()){ //si c.y() == a position initiale de l'ordonnée
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),c.y()));
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),taille+1));
				cmpt=cmpt+nbVoisin(new Vivante(taille+1,taille+1));
				cmpt=cmpt+nbVoisin(new Vivante(taille+1,c.y()));
			}
			else if(c.y()==taille){
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),c.y()));
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),y()-1));
				cmpt=cmpt+nbVoisin(new Vivante(taille+1,y()-1));
				cmpt=cmpt+nbVoisin(new Vivante(taille+1,c.y()));
			}
			else{
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),c.y()));
				cmpt=cmpt+nbVoisin(new Vivante(taille+1,c.y()));
			}
			
		}
		else if(c.x()==taille){
			if(c.y()==y()){ //si c.y() == a position initiale de l'ordonnée
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),c.y()));
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),taille+1));
				cmpt=cmpt+nbVoisin(new Vivante(x()-1,taille+1));
				cmpt=cmpt+nbVoisin(new Vivante(x()-1,c.y()));
			}
			else if(c.y()==taille){
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),c.y()));
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),y()-1));
				cmpt=cmpt+nbVoisin(new Vivante(x()-1,y()-1));
				cmpt=cmpt+nbVoisin(new Vivante(x()-1,c.y()));
			}
			else{
				cmpt=cmpt+nbVoisin(new Vivante(c.x(),c.y()));
				cmpt=cmpt+nbVoisin(new Vivante(x()-1,c.y()));
			}
		}
		if(c instanceof Vivante)
			return (cmpt==2 || cmpt==3);
		else return cmpt==3;
	}
	*/
}
