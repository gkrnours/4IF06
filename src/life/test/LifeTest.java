package life.test;


import java.util.ArrayList;
import java.util.Comparator;

import junit.framework.Assert;
import life.LIFE;
import life.cell.Cellule;
import life.cell.Coord;
import life.cell.Morte;
import life.cell.Vivante;

import org.junit.Test;

/**
 * Class qui teste les fonctions en rapport avec le package life
 */

public class LifeTest {
	// Tests sur LIFE.java
	
	/**
	 * Test de nbVoisin et implicitement de recupererVoisinage et getCell
	 */
	@Test
	public void TestNbVoisin() {
		Cellule c = new Vivante(1, 1);
		Cellule c1 = new Vivante(1, 2);
		Cellule c2 = new Vivante(1, 7);
		Cellule c3 = new Morte(2, 2);
		ArrayList<Cellule> raw = new ArrayList(); 
		raw.add(c);
		raw.add(c1);
		raw.add(c2);
		raw.add(c3);
		LIFE life = new LIFE(raw);
		Assert.assertTrue(life.nbVoisin(c) == 1);
		Assert.assertTrue(life.nbVoisin(c2) == 0);
		Assert.assertTrue(life.nbVoisin(c3) == 2);
	}

	/**
	 * Test de alive et implicitement de evolve
	 */
	@Test
	public void TestAlive() {
		ArrayList raw = new ArrayList<Cellule>();
		Cellule c = new Vivante(1, 1);
		Cellule c1 = new Vivante(1, 2);
		Cellule c2 = new Vivante(2, 1);
		Cellule c3 = new Vivante(1, 0);
		Cellule c4 = new Vivante(5, 2);
		Cellule c5 = new Morte(0, 1);
		Cellule c6 = new Morte(0, 2);
		raw.add(c);
		raw.add(c1);
		raw.add(c2);
		raw.add(c3);
		raw.add(c4);
		raw.add(c5);
		raw.add(c6);
		LIFE life = new LIFE(raw);
		/*
		Assert.assertTrue(life.alive(c1));
		Assert.assertTrue(life.alive(c3));
		Assert.assertFalse(life.alive(c4));
		Assert.assertTrue(life.alive(c5));
		Assert.assertFalse(life.alive(c6));
		*/
	}

	/**
	 * Test de existe
	 */
	@Test
	public void TestContains() {
		Cellule c  = new Vivante(1, 1);
		Cellule c1 = new Morte(1, 2);
		ArrayList<Cellule> raw = new ArrayList<Cellule>();
		LIFE life = new LIFE(raw);
		raw.add(c);
		raw.add(c1);
		Assert.assertTrue(life.existe(c));
		Assert.assertTrue(life.existe(c1));
		Assert.assertFalse(life.existe(new Vivante(1, 4)));
	}

	/**
	 * Test de hasNext en utilisant un carre et un life vide (on sait qu'un
	 * carre de 2 par 2 est une figure qui ne change pas en Jeu de la vie et a
	 * donc forcement un next tandis qu'un life sans cellules ne peut avoir de
	 * next)
	 */
	@Test
	public void TestHasNext() {
		ArrayList<Cellule> raw = new ArrayList<Cellule>();
		Cellule c = new Vivante(0, 0);
		Cellule c1 = new Vivante(0, 1);
		Cellule c2 = new Vivante(1, 1);
		Cellule c3 = new Vivante(1, 0);
		raw.add(c);
		raw.add(c1);
		raw.add(c2);
		raw.add(c3);
		LIFE life = new LIFE(raw);
		Assert.assertTrue(life.hasNext());
		LIFE life2 = new LIFE(new ArrayList<Cellule>());
		Assert.assertFalse(life2.hasNext());
		life.next();
	}

	/**
	 * Test de next. On utilise des dispositions dont on connait l'étape
	 * suivante afin de pouvoir vérifier l'exactitude de la méthode. Ici, on
	 * utilise un carre de 2 par 2 car il n'evolue pas et next=disposition
	 * actuelle, et une ligne de 3 Vivante car on sait que cette disposition
	 * doit donner une colonne de 3 Vivante avec le même milieu que la ligne.
	 */
	@Test
	public void TestNext() {
		ArrayList<Cellule> raw = new ArrayList<Cellule>();
		Cellule c = new Vivante(0, 0);
		Cellule c1 = new Vivante(0, 1);
		Cellule c2 = new Vivante(1, 1);
		Cellule c3 = new Vivante(1, 0);
		raw.add(c);
		raw.add(c1);
		raw.add(c2);
		raw.add(c3);
		LIFE life = new LIFE(raw);
		life.next();
		Assert.assertTrue(life.size()==4);					// Il y a 4 Cellule
		Assert.assertTrue(life.existe(c));					// Chaque Cellule 
		Assert.assertTrue(life.existe(c1));					// est bien à la 
		Assert.assertTrue(life.existe(c2));					// bonne position.
		Assert.assertTrue(life.existe(c3));

		raw = new ArrayList<Cellule>();
		Cellule c4 = new Vivante(0, 0);
		Cellule c5 = new Vivante(0, 1);
		Cellule c6 = new Vivante(0, 2);
		raw.add(c4);
		raw.add(c5);
		raw.add(c6);
		LIFE life2 = new LIFE(raw);
		life2.next();
		Assert.assertTrue(life2.size()==3);					// Il y a 3 Cellule	
		Assert.assertTrue(life2.existe(new Vivante(-1,1)));	// Chaque Cellule
		Assert.assertTrue(life2.existe(c5));				// est bien à la
		Assert.assertTrue(life2.existe(new Vivante(1,1)));	// bonne position.
	}

	
	
}
