package edu.uwm.cs351;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import edu.uwm.cs351.util.Graph;
import edu.uwm.cs351.util.HashGraph;

public class LoadGraph {
	
	/**
	 * Read in a graph from the given reader.
	 * Each line (once trimmed of whitespace) names a vertex (a string),
	 * but before we trim, we check to see if the line begins with whitespace, 
	 * in which case this vertex is the second vertex of an edge that
	 * begins with the last vertex that was on a line not starting with whitespace.
	 * <p> For example, the following text:
	 * <pre>
	 * A
	 * B
	 *   A
	 *   C
	 * </pre>
	 * is a graph with three vertices (A, B and C) and two
	 * edges, A-B and B-C.
	 * @param r
	 * @return graph represented by text of reader.
	 * @throws IOException if error reading or if the first line is indented.
	 */
	public static Graph<String> read(Reader r) throws IOException {
		Graph<String> result = new HashGraph<>();
		// TODO: Read in the graph from the reader.
		// Our solution is 15 lines long, and it uses the following library classes:
		// * BufferedReader
		BufferedReader br = new BufferedReader(r);	    
	    String lv = null;	    
	    for (String str;(str = br.readLine()) != null;) {
	        if (str.trim().isEmpty()) continue;	        
	        if (!Character.isWhitespace(str.charAt(0))) {
	            if (!result.addVertex(str.trim())) {}
	            lv = str.trim();
	        } else {
	            if (lv == null) throw new IOException("First line is indented");	            
	            result.addVertex(str.trim());
	            result.addEdge(lv, str.trim());
	        }
	    }	    
	    return result;
	}
}
