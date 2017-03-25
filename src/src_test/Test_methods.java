package src_test;

import static org.junit.Assert.*;
import src_main.myComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import src_main.Rectangle;

public class Test_methods {
	
	private static ArrayList<Rectangle> arrayRec = new ArrayList<Rectangle>();
	private static String[] keyVals = {"a","b","c","d","e","f","g","h","i","j"};
	private static Rectangle testOb1 = new Rectangle (5,5);
	private static Rectangle testOb2 = new Rectangle (5,10);
	
	
	

	@BeforeClass
	public static void setup() {
		for (int i=1; i<100; i+=10)
		{
			arrayRec.add(new Rectangle(i,i));
		}
		

	}

	@AfterClass
	public static void teardown() {
		
		arrayRec=null;
		keyVals=null;
		testOb1=null;
		testOb2=null;
	}
	
/* This is the test for HashSet, which is characterized by
 * "Hash" and "Set", and so I will test the uniqueness aspects of hashing
 * and some of the Set operations. 
 */
	
	@Test
	public void testHashSet() {
		
		HashSet<Rectangle> hs = new HashSet<Rectangle>(arrayRec);
		
		// should have 10 unique elements from construction
		assertEquals(hs.size(),10);
		
		// Two additions of same object, same hash code, should only increase size by 1
		hs.add(testOb1);
		hs.add(testOb1);
		assertEquals(hs.size(),11);
		
		//Test a couple set operations, create new small HashSet 
		HashSet<Rectangle> hs2 = new HashSet<Rectangle>();
		hs2.add(testOb1);
		hs2.add(testOb2);
		
		// The following set operations should be akin to 1) intersection (A and B) and 2) set difference (A not in B)
		hs.retainAll(hs2);
		assertEquals(hs.size(), 1);
		hs2.removeAll(hs);
		assertEquals(hs2.size(),1);

	}
	
	/*This is the test for HashMap, characterized by "Hash" and "Map", so essentially I will test for uniqueness and 
	 * various Map methods, such as playing with key-value pairs
	 * 
	 */
	
	@Test
	public void testHashMap() {
		
		HashMap<String, Rectangle> hm = new HashMap();
		for (int i=0; i<10; i++)
		{
			hm.put(keyVals[i], arrayRec.get(i));
		}
		
		// Should have been created with 10 unique key-value pairs
		assertEquals(hm.size(),10);
		
		// Should have a size of 12 now, that is values can be duplicates, but the keys are unique
		hm.put("k", arrayRec.get(0));
		hm.put("l", arrayRec.get(1));
		assertEquals(hm.size(),12);
		
		//Each key should only be mapped to one value, much like a mathematical function (1 output per unique input)
		hm.put("a", arrayRec.get(1));
		hm.put("a", arrayRec.get(2));
		hm.put("a", arrayRec.get(0));
		assertEquals(hm.get("a"),arrayRec.get(0));
		assertEquals(hm.size(),12);

	}
	
	/* This is the test for TreeMap, characterized by "Tree" and "Map", meaning it should essentially 
	 * contain a collection of key-value pairs in some type of particular order (the keys),
	 * and so I will test these characteristics.
	 */
	@Test
	public void testTreeMap() {
		
		TreeMap<Rectangle, Double> tm = new TreeMap<Rectangle, Double>();
		for (int i=0; i<10; i++)
		{
			tm.put(arrayRec.get(i), new Double(arrayRec.get(i).getArea()));
		}
		
		/*Check that the keys were sorted in the desired order, ascending order (area) for 
		 * given Rectangle key. I chose Rectangle as the key since I implemented Comparable in 
		 * Rectangle, and it is my understanding that keys are sorted using my overridden compareTo() method
		 */
		assertEquals(tm.firstKey(), arrayRec.get(0));
		assertEquals(tm.lastKey(), arrayRec.get(9));
		
		//A double check on ordering, a new rectangle with the largest area should be the last key
		Rectangle recT = new Rectangle(100,100);
		tm.put(recT, recT.getArea());
		assertEquals(tm.lastKey(),recT);
		
		
		// Again test the properties of Map, key-value pairs are updated, no repeated keys, only new keys are added
		assertEquals(tm.size(),11);
		tm.put(recT, 20.0);
		assertEquals(tm.size(),11);
		tm.put(new Rectangle(5,5), 25.0);
		assertEquals(tm.size(),12);
		

	}
	
	/* This is the test for TreeSet, characterized by "Tree" and "Set", meaning it should essentially contain a set
	 * of elements in some default or specified order, in this case specified, so I will test these characteristics.
	 */
	@Test
	public void testTreeset() {
		
		//initialized with my custom comparator, and then built the set with the loop
		TreeSet<Rectangle> ts = new TreeSet<Rectangle>(new myComparator());
		for (int i=0; i<10; i++)
		{
			ts.add(arrayRec.get(i));
		}
		
		/*In my Rectangle class, I implemented comparable and ordered by ascending area, but
		 * in this TreeSet I passed my custom Comparator to sort by descending area, so
		 * the following tests should pass (the opposite ordering that I did in TreeMap)
		 */
		assertEquals(ts.first(), arrayRec.get(9));
		assertEquals(ts.last(),arrayRec.get(0));
		
		// A couple tests for the inherited Set methods, basically the same as done in HashSet
		TreeSet<Rectangle> ts2 = new TreeSet<Rectangle>();
		ts2.add(arrayRec.get(0));
		ts2.add(arrayRec.get(1));
		ts.removeAll(ts2);
		assertEquals(ts.size(),8);
		ts.retainAll(ts2);
		assertEquals(ts.isEmpty(),true);

	}
	
	/*This is the test for LinkedList, characterized by "Linked" and "List", which by my understanding Linked basically suggests 
	 * the given object is built to be worked with in some particular order, for example, ArrayList is more so characterized by
	 * random-access whereas LinkedList is characterized by working in a particular sequence, for better or worse (efficiency). Also,
	 * much should be the same between the two given they both extend List, so I'll ignore those methods.
	 * I will test these characteristics. 
	 */
	@Test
	public void testLinkedList() {
		
		LinkedList<Rectangle> ll = new LinkedList<Rectangle>(arrayRec);
		
		//first test construction, doc said I can construct with Collection object
		assertEquals(ll.size(),10);
		
		//Test some of the "Linked" oriented methods, such as addFirst(), addLast(), removeFirst(), removeLast()
		assertEquals(ll.getFirst(),arrayRec.get(0));
		Rectangle rTest1 = new Rectangle(10,15);
		ll.addFirst(rTest1);
		assertEquals(ll.getFirst(),rTest1);
		assertEquals(ll.getLast(),arrayRec.get(9));
		Rectangle rTest2 = new Rectangle(8,8);
		ll.addLast(rTest2);
		assertEquals(ll.getLast(),rTest2);
		
		ll.removeFirst();
		assertEquals(ll.getFirst(),arrayRec.get(0));
		ll.removeLast();
		assertEquals(ll.getLast(),arrayRec.get(9));
		

	}
}
