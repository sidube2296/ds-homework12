package edu.uwm.cs351.util;

/**
 * An undirected edge between two non-null vertices.
 * 
 * The Edge (a,b) is equal to the Edge (b,a).
 * 
 * @param T type of vertices
 */
public interface Edge<T> {
	/**
	 * Check if this edge contains a specific vertex.
	 * 
	 * @param vertex
	 * @return true if this edge contains the vertex
	 */
	public boolean contains(T vertex);

	public T vertex(boolean b);
}
