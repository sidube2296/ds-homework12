import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.Callable;

import edu.uwm.cs.junit.LockedTestCase;
import edu.uwm.cs351.LoadGraph;
import edu.uwm.cs351.util.Graph;

public class TestLoadGraph extends LockedTestCase {
	protected <T> void assertException(Class<?> excClass, Callable<T> f) {
		try {
			T x = f.call();
			assertFalse("Should have thrown an exception, not returned: " + x,true);
		} catch (Exception ex) {
			if (!excClass.isInstance(ex)) {
				ex.printStackTrace();
				assertFalse("Wrong kind of exception thrown: "+ ex.getClass().getSimpleName(),true);
			}
		}		
	}

	protected Graph<String> loadGraph(String s) {
		try {
			return LoadGraph.read(new StringReader(s));
		} catch (IOException e) {
			return null;
		}
	}
	
	
	/// Locked tests
	
	public void test() {
		test1(true);
	}
	
	protected void test1(boolean chain) {
		// Here's the input:
		// A
		// B
		// C
		//   D
		Graph<String> g = loadGraph("A\nB\nC\n D\n");
		assertEquals(Ti(537460312), g.order()); // number of vertices
		assertEquals(Ti(2105592676), g.size());  // number of edges
		System.out.println("vertex set = " + g.vertexSet()); // might help your debugging...
		assertEquals(true, g.containsEdge("C", "D"));
		assertEquals(Tb(246968169), g.containsEdge("D", "C")); // undirected graph
		assertEquals(false, g.containsEdge("A", "B"));
		test2(chain);
	}
	
	protected void test2(boolean chain) {
		// Here's the input:
		// A
		//   B
		// B
		//   A
		//   A
		// A
		//   B
		Graph<String> g = loadGraph("A\n B\nB\n A\n A\nA\n B\n");
		assertEquals(Ti(5620399), g.order()); // number of vertices
		assertEquals(Ti(525052933), g.size());  // number of edges
		test3(chain);
	}
	
	protected void test3(boolean chain) {
		// Here's the input:
		// A
		//   B
		//   C
		//   D
		// B
		//   A
		//   C
		//   D
		// C
		//   A
		//   B
		//   D
		// D
		//   A
		//   B
		//   C
		Graph<String> g = loadGraph("A\n B\n C\n D\nB\n A\n C\n D\nC\n A\n B\n D\nD\n A\n B\n C\n");
		assertEquals(Ti(362392046), g.order());
		assertEquals(Ti(1750988700), g.size());
	}
	
	public void testA() {
		Graph<String> g = loadGraph("");
		assertEquals(0, g.order()); 
		assertEquals(0, g.size());
	}
	
	public void testB() {
		Graph<String> g = loadGraph("A\n");
		assertEquals(1, g.order());
		assertEquals(0, g.size());
	}

	public void testC() {
		Graph<String> g = loadGraph("A\nB\n");
		assertEquals(2, g.order());
		assertEquals(0, g.size());
	}
	
	public void testD() {
		Graph<String> g = loadGraph("A\n\tB");
		assertEquals(2, g.order());
		assertEquals(1, g.size()); // tabs should be treated like spaces
	}
	
	public void testE() {
		Graph<String> g = loadGraph("A   \n  B   ");
		assertEquals(2, g.order());
		assertEquals(1, g.size());
		assertTrue(g.containsVertex("A")); // make sure to trim!
		assertTrue(g.containsVertex("B"));
	}
	
	public void testF() {
		assertException(IOException.class, () -> LoadGraph.read(new StringReader(" A\n B\n")));
	}
	
	public void testG() {
		Graph<String> g = loadGraph("A\n B C");
		assertEquals(2, g.order());
		assertEquals(1, g.size());
		assertTrue(g.containsVertex("B C")); // don't split, trim!
	}
	
	public void testH() {
		Graph<String> g = loadGraph("A\n B\n C \nB\n D\n");
		assertEquals(4, g.order());
		assertEquals(3, g.size());
		assertTrue(g.containsVertex("C"));
		assertTrue(g.containsEdge("A", "B"));
		assertTrue(g.containsEdge("A", "C"));
		assertTrue(g.containsEdge("B", "D"));
		assertFalse(g.containsEdge("A", "D"));
	}
	
	public void testI() {
		Graph<String> g = loadGraph("A\nB\nC\nD\n E\nB\n F\n");
		assertEquals(6, g.order());
		assertEquals(2, g.size());
		assertTrue(g.containsEdge("D", "E"));
		assertTrue(g.containsEdge("B", "F"));
	}
}
