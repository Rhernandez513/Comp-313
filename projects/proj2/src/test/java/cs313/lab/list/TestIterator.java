package cs313.lab.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIterator {

	private List<Integer> list;

	@Before
	public void setUp() throws Exception {
//		list = new ArrayList<Integer>();
		// List list = new LinkedList<Integer>();
		// TODO also try with a LinkedList - does it make any difference?
		list = new LinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testEmpty() {
		final Iterator<Integer> i = list.iterator();
		assertFalse(i.hasNext());
	}

	@Test
	public void testNonempty() {
		list.add(33);
		list.add(77);
		list.add(44);
		list.add(77);
		list.add(55);
		list.add(77);
		list.add(66);
		final Iterator<Integer> i = list.iterator();
		assertTrue(i.hasNext());
		assertEquals(33, i.next().intValue());
		// TODO fix the expected values in the assertions below
		assertTrue(i.hasNext());
		assertEquals(77, i.next().intValue());
		assertTrue(i.hasNext());
		assertEquals(44, i.next().intValue());
		assertTrue(i.hasNext());
		assertEquals(77, i.next().intValue());
		assertTrue(i.hasNext());
		assertEquals(55, i.next().intValue());
		assertTrue(i.hasNext());
		assertEquals(77, i.next().intValue());
		assertTrue(i.hasNext());
		assertEquals(66, i.next().intValue());
		assertFalse(i.hasNext());
	}

	@Test
	public void testRemove() {
		list.add(33);
		list.add(77);
		list.add(44);
		list.add(77);
		list.add(55);
		list.add(77);
		list.add(66);
		final Iterator<Integer> i = list.iterator();
		while (i.hasNext()) {
			if (i.next() == 77) {
				i.remove(); // TODO what happens if you use list.remove(77)?
        // list.remove(77); // IndexOutofBounds Exception
			}
		}
		// TODO using assertEquals and Arrays.asList (see above)
		// express which values are left in the list
		final Iterator<Integer> secondIter = list.iterator();
    while (secondIter.hasNext()) {
      int test = secondIter.next();
      assertFalse(test == 77);
      System.out.println("value left in list " + test + "\n");
    }
	}

	@Test
	public void testAverageValues() {
		list.add(33);
		list.add(77);
		list.add(44);
		list.add(77);
		list.add(55);
		list.add(77);
		list.add(66);
		double sum = 0;
		int n = 0;
		// TODO use an iterator and a while loop to compute the average (mean) of the values
		// (defined as the sum of the items divided by the number of items)
		final Iterator<Integer> iter = list.iterator();
    while (iter.hasNext()) {
      sum += iter.next();
      ++n;
    }
    double mean = sum / n;
    System.out.println("Mean of the list: " + mean + "\n");
		assertEquals(61.3, sum / n, 0.1);
		assertEquals(7, n);
	}
}
