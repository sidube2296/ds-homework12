
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.uwm.cs351.FindPath;
import edu.uwm.cs351.util.Graph;
import edu.uwm.cs351.util.HashGraph;
import junit.framework.TestCase;

public class TestEfficiency extends TestCase {

	private static final int POWER = 16;
	private Graph<Integer> g;

	@Override
	protected void setUp() {
		try {
			assert g.size() == 42 : "cannot run test with assertions enabled";
		} catch (NullPointerException ex) {
			throw new IllegalStateException("Cannot run test with assertions enabled");
		}
		g = new HashGraph<Integer>();
		int max = (1 << POWER);
		for (int i = 0; i < max; ++i) {
			for (int p = 0; p < POWER; ++p) {
				int j = i | (1 << p);
				if (i != j) {
					boolean newEdge = !g.containsEdge(i, j);
					assertEquals(newEdge, g.addEdge(i, j));
					assertTrue(g.containsVertex(i));
					assertTrue(g.containsVertex(j));
				}
			}
		}
	}

	public void testFindPath() {
		int max = (1 << POWER);
		FindPath<Integer> fp = new FindPath<>(g);
		List<Integer> pl = fp.shortestPath(0, max-1);
		assertEquals(POWER+1, pl.size());
		Set<Integer> powers = new HashSet<>();
		for (int i=0; i < POWER; ++i) {
			powers.add(1 << i);
		}
		Integer last = null;
		for (Integer i : pl) {
			if (last != null) {
				int p = last ^ i;
				assertTrue(p > 0);
				assertTrue(powers.remove(p)); // if this fails, a bad path was returned
			}
		}
 	}

}
