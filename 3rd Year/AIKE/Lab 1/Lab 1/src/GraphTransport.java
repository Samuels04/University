import java.util.*;
import java.util.Map.Entry;

public class GraphTransport {
	private Map<String, TreeMap<String, Double>> mGraph;
	private List<Segment> edges;
	private int mVertexCount;
	private int mEdgeCount;

	Map<String, Double> mDistances;
	Map<String, String> mPredecessors;

	public Map<String, Double> getSP_Distances() {
		return mDistances;
	}

	public Map<String, String> getSP_Predecessors() {
		return mPredecessors;
	}

	public Set<String> adjacentsTo(String v) {
		if (hasVertex(v))
			return mGraph.get(v).keySet();
		else
			return new HashSet<>();
	}

	public Set<String> getAllVertices() {
		return mGraph.keySet();
	}

	public int getVertexCount() {
		return mVertexCount;
	}

	public int getEdgeCount() {
		return mEdgeCount;
	}

	public GraphTransport() {
		mGraph = new TreeMap<String, TreeMap<String, Double>>();
		mVertexCount = 0;
		mEdgeCount = 0;
		edges = new ArrayList<>();
	}

	public GraphTransport(GraphTransport g) {
		mGraph = new TreeMap<String, TreeMap<String, Double>>(g.mGraph);
		mVertexCount = g.mVertexCount;
		mEdgeCount = g.mEdgeCount;
		edges = g.edges;
	}

	public boolean addVertex(String v) {
		if (hasVertex(v))
			return false; // Can't repeat vertices

		mVertexCount++;

		// Empty adjacency list
		TreeMap<String, Double> aux = new TreeMap<String, Double>();
		mGraph.put(v, aux); // Vertex added to the graph

		return true;
	}

	public boolean hasVertex(String v) {
		return mGraph.containsKey(v);
	}

	public boolean removeVertex(String v) {
		// Not in the graph
		if (!mGraph.containsKey(v))
			return false;

		// We have to remove first all the occurrences in the adjacency lists
		for (Map<String, Double> adjList : mGraph.values())
			adjList.remove(v);

		// We finally remove it from the graph
		mGraph.remove(v);
		mVertexCount--;

		return true;
	}

	public int InDegree(String v) {
		if (!mGraph.containsKey(v))
			return 0;

		int counter = 0;
		// Look for the vertex in all the adjacency lists
		for (Map<String, Double> adjList : mGraph.values())
			if (adjList.containsKey(v))
				counter++;

		return counter;
	}

	public int OutDegree(String v) {
		if (!mGraph.containsKey(v))
			return 0;

		Map<String, Double> adjList = mGraph.get(v);

		return adjList.size();
	}

	public boolean hasEdge(Segment edge) {
		/*
		 * if (from.equals(to))
		 * return true;
		 */
		String from = edge.getStartStop();
		String to = edge.getEndStop();

		if (!mGraph.containsKey(from) || !mGraph.containsKey(to))
			return false;

		Map<String, Double> adjList = mGraph.get(from);

		return (adjList.containsKey(to));
	}

	public boolean addEdge(Segment edge) {
		if (hasEdge(edge))
			return false;

		mEdgeCount++;
		edges.add(edge);

		addVertex(edge.getStartStop());
		addVertex(edge.getEndStop());

		Map<String, Double> adjList = mGraph.get(edge.getStartStop());
		adjList.put(edge.getEndStop(), edge.getLength());

		return true;
	}

	public boolean removeEdge(Segment edge) {
		if (!hasEdge(edge))
			return false;

		Map<String, Double> adjList = mGraph.get(edge.getStartStop());
		adjList.remove(edge.getEndStop());

		mEdgeCount--;
		edges.remove(edge);
		return true;
	}

	public Double edgeWeight(Segment edge) {
		/*
		 * if (from.equals(to))
		 * return 0.0;
		 */

		if (!hasEdge(edge))
			return Double.POSITIVE_INFINITY;

		Map<String, Double> adjList = mGraph.get(edge.getStartStop());
		return adjList.get(edge.getEndStop());
	}

	public void print() {
		for (String vertex : mGraph.keySet()) {
			System.out.print(vertex + " -> ");

			Map<String, Double> adjList = mGraph.get(vertex);
			for (Entry<String, Double> adjV : adjList.entrySet())
				System.out.printf("(%s, %g); ", adjV.getKey(), adjV.getValue());

			System.out.println();
		}

		System.out.println();
	}
}