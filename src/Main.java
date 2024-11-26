import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.uwm.cs351.FindPath;
import edu.uwm.cs351.LoadGraph;
import edu.uwm.cs351.util.Graph;

public class Main {
	private static final int MAX_ROUTES = 3; // print this many routes from start
	
	private Graph<String> graph;
	
	void usage() {
		System.err.println("usage: Main <mapfilename> [<start> [<end>]]");
	}
	
	void loadMap(String filename) {
		try {
			graph = LoadGraph.read(new FileReader(filename));
		} catch (IOException e) {
			System.err.println("Cannot read '" + filename + "': " + e.getLocalizedMessage());
			System.exit(1);
		}
	}
	
	void printNames() {
		Set<String> names = new TreeSet<>(graph.vertexSet());
		System.out.println("Locations on Map:");
		for (String s : names) {
			System.out.println(s);
		}
	}
	
	void printRoutes(String start) {
		int printed = 0;
		graph.addVertex(start);
		for (String end : graph.vertexSet()) {
			printShortest(start, end);
			if (++printed >= MAX_ROUTES) break;
		}
	}
	
	void printShortest(String start, String end) {
		graph.addVertex(start);
		graph.addVertex(end);
		FindPath<String> fp = new FindPath<>(graph);
		System.out.print("Path from " + start + " to " + end + ": ");
		List<String> p = fp.shortestPath(start, end);
		if (p == null) System.out.println("<not possible>");
		else System.out.println(p);
	}
	
	public void run(String[] args) {
		switch (args.length) {
		default: 
			usage();
			break;
		case 1: 
			loadMap(args[0]);
			printNames();
			break;
		case 2:
			loadMap(args[0]);
			printRoutes(args[1]);
			break;
		case 3:
			loadMap(args[0]);
			printShortest(args[1], args[2]);
			break;
		}		
	}

	public static void main(String[] args) {
		new Main().run(args);
	}
}
