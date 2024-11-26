package edu.uwm.cs351;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import edu.uwm.cs351.util.Graph;
import edu.uwm.cs351.util.ImmutableStack;

/**
 * A class to find shortest paths in a graph.
 * @param T graph element type
 */
public class FindPath<T> {
	private Graph<T> graph;
	
	/**
	 * Set up the algorithm to work on the given graph.
	 * @param g graph to use, must not be null
	 */
	public FindPath(Graph<T> g) {
		graph = g;
	}

	/**
	 * Return the shortest path between two vertices in the graph
	 * @param v1 starting vertex
	 * @param v2 ending vertex
	 * @return path from the first to the second as a list of vertices, or null
	 * is no path exists.
	 * @throws IllegalArgumentException if v1 or v2 is not in the graph
	 */
	public List<T> shortestPath(T v1, T v2) {
		// TODO: Find shortest path from v1 to v2.  
		// Our solution works backwards because ImmutableStack works that way.  
		// Our solution is 16 lines long and uses the following other classes:
		// * ArrayDeque  (implementation of Queue)
		// * ImmutableStack  (to keep partial paths to the destination)
		// * HashSet  (for the visited set)
		// * ArrayList  (to return the final path)
		// You are welcome to write and use new helper methods, but not to add fields.
		return null; // no path found
	}
}