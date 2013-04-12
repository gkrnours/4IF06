package life;

import java.util.Comparator;

import junit.framework.Assert;
import life.cell.Coord;
import life.cell.Morte;
import life.cell.Vivante;

import org.junit.Test;

public class TestJunit {

	/**
	 * Test de l'initialisation des Vivantes
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVivante() {
		Vivante a = new Vivante(1, 2);
		Assert.assertTrue(a.vivante());
	}

	/**
	 * Test de l'initialisation des Mortes
	 * 
	 * @throws Exception
	 */
	@Test
	public void TestMorte() {
		Morte a = new Morte(3, 1);
		Assert.assertFalse(a.vivante());
	}

	/**
	 * Test de isAt
	 */
	@Test
	public void TestIsAt() {
		Coord c = new Coord(1, 2);
		Assert.assertTrue(c.isAt(1, 2));
		Assert.assertFalse(!c.isAt(1, 2));
	}

	/**
	 * Test de isIn
	 */
	@Test
	public void TestIsIn() {
		Coord c = new Coord(5, 10);
		Coord co = new Coord(-10, 0);
		Coord coor = new Coord(5, 10);
		Coord coord = new Coord(0, 0);
		Assert.assertTrue(c.isIn(c, c));
		Assert.assertTrue(c.isIn(co, coor));
		Assert.assertFalse(c.isIn(coor, coord));
	}

	/**
	 * Test de compareTo
	 */
	@Test
	public void TestCompareTo() {
		Coord c = new Coord(5, 10);
		Coord c1 = new Coord(10, 5);
		Coord c2 = new Coord(6, 10);
		Coord c3 = new Coord(-6, 10);
		Assert.assertTrue(c.compareTo(c) == 0);
		Assert.assertTrue(0 < c.compareTo(c1));
		Assert.assertTrue(c1.compareTo(c) < 0);
		Assert.assertTrue(c.compareTo(c2) < 0);
		Assert.assertTrue(0 < c.compareTo(c3));
	}

	/**
	 * Test de equals
	 */
	@Test
	public void TestEquals(){
		Coord c = new Coord (1,2);
		Coord c1 = new Coord (3,4);
		int i = 2;
		Assert.assertTrue(c.equals(c));
		Assert.assertFalse(c.equals(c1));
		Assert.assertFalse(c.equals(i));
	}
	
	/**
	 * Test de compareX
	 */
	@Test
	public void TestCompareX(){
		Comparator<Coord> cmp = new Coord.compareX();
		Coord c = new Coord (1,2);
		Coord c1 = new Coord (2,2);
		Assert.assertTrue(cmp.compare(c, c) == 0);
		Assert.assertFalse(cmp.compare(c, c1) == 0);
	}
	
	/**
	 * Test de compareY
	 */
	@Test
	public void TestCompareY(){
		Comparator<Coord> cmp = new Coord.compareY();
		Coord c = new Coord (1,2);
		Coord c1 = new Coord (2,1);
		Assert.assertTrue(cmp.compare(c, c) == 0);
		Assert.assertFalse(cmp.compare(c, c1) == 0);
	}
	
	
}
