package life;

import java.util.ArrayList;
import java.util.Collections;
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
	
	//renvoie un set contenant les voisinnes de la cellule c, et c
	public Set<Cellule> recupererVoisinage(Cellule c) {
		int dx = c.x() - 1;
		int dy = c.y() - 1;

		Set<Cellule> s = new HashSet<Cellule>();
		for (int i = 0; i < 9; ++i) {
			Cellule cell = getCell(new Coord(dx + (i % 3), dy + (i / 3)));
			s.add(cell);
		}
		return s;
	}

	// TODO supprimer la duplication de code
	// retourne vrai si la cellule c peut vivre, faux sinon
	public boolean alive(Cellule c) {
		int cmpt = 0;
		Set<Cellule> hs = recupererVoisinage(c);
		for (Cellule cel : hs) {
			if (cel.equals(c)) continue;
			if(cel.vivante()) ++cmpt;
		}
		return c.evolve(cmpt).vivante();
	}


	// renvoie la cellule c si celle si est dans raw, et renvoie une morte si
	// elle n'y est pas
	public Cellule getCell(Coord c) {
		int idx = raw.indexOf(c);
		if (idx == -1)
			return new Morte(c);
		else
			return raw.get(idx);
	}

	public boolean existe(Coord c) {
		return this.raw.contains(c);
	}

	@Override
	public String toString() {
		return getClass().getName()+" [" + x + "/" + y + "] [" + w + "×" + h + ":" + d + "]";
	}

	public void debug() {
		for (Coord i : raw) {
			System.out.println("[" + i.x() + ":" + i.y() + "]  ");
		}
		System.out.println();
	}

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

	//converti un arraylist en set
	public Set<Cellule> arrayListToSet(ArrayList<Cellule> Al) {

		Set<Cellule> s = new HashSet<Cellule>();
		for (Cellule c : Al) {
			s.add(c);
		}
		return s;
	}
	
	//retourne un hascode
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
	
	//retourne le nb de voisins d'une cellule
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
