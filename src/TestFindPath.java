import java.util.List;

import edu.uwm.cs.junit.LockedTestCase;
import edu.uwm.cs351.FindPath;
import edu.uwm.cs351.util.Graph;
import edu.uwm.cs351.util.HashGraph;

public class TestFindPath extends LockedTestCase {
	protected void assertException(Class<?> exc, Runnable r) {
		try {
			r.run();
			assertFalse("should have thrown an exception.",true);
		} catch (RuntimeException e) {
			if (exc == null) return;
			assertTrue("threw wrong exception type.",exc.isInstance(e));
		}
	}

	private Graph<String> graph;
	private FindPath<String> find;
	
	public void setUp() {
		graph = new HashGraph<>();
		find = new FindPath<>(graph);
	}
	
	public void test() {
		makeCompassGraph();
		test1(graph);
	}

	public void makeCompassGraph() {
		graph.addEdge("N", "W");
		graph.addEdge("N", "S");
		graph.addEdge("N", "E");
		graph.addEdge("S", "E");
	}
	
	public void test1(Graph<String> g) {
		// Here's the graph:
		//        N
		//      / | \
		//     /  |  \
		//    W   |   E
		//        |  /
		//        | /
		//        S
		List<String> w_w_path = find.shortestPath("W", "W");
		assertEquals(Ti(1386955787), w_w_path.size());
		List<String> w_e_path = find.shortestPath("W", "E");
		assertEquals(Ti(2106070703), w_e_path.size());
		assertEquals(Ts(13060978), w_e_path.get(0));
		assertEquals(Ts(842175584), w_e_path.get(1));
		assertEquals(Ts(331951034), w_e_path.get(2));
	}
	
	public void testNotPossible() {
		makeCompassGraph();
		graph.addVertex("A");
		assertNull(find.shortestPath("A", "N"));
		assertNull(find.shortestPath("N", "A"));
	}
	
	public void testNotInGraph() {
		makeCompassGraph();
		assertException(IllegalArgumentException.class, () -> find.shortestPath("A", "N"));
		assertException(IllegalArgumentException.class, () -> find.shortestPath("N", "B"));
		assertException(IllegalArgumentException.class, () -> find.shortestPath("A", "B"));
	}
	
	public void testEquals() {
		makeCompassGraph();
		assertEquals(3, find.shortestPath(new String("S"), new String("W")).size());
	}
}
