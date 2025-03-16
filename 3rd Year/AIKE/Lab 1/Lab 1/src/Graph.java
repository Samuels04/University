import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Graph<T> {
	private Map<T, TreeMap<T, Double>> mGraph;
	private int mVertexCount;
	private int mEdgeCount;

	Map<T, Double> mDistances;
	Map<T, T> mPredecessors;

	public Map<T, Double> getSP_Distances() {
		return mDistances;
	}

	public Map<T, T> getSP_Predecessors() {
		return mPredecessors;
	}

	public Set<T> adjacentsTo(T v) {
		if (hasVertex(v))
			return mGraph.get(v).keySet();
		else
			return new HashSet<>();
	}

	public Set<T> getAllVertices() {
		return mGraph.keySet();
	}

	public int getVertexCount() {
		return mVertexCount;
	}

	public int getEdgeCount() {
		return mEdgeCount;
	}

	public Graph() {
		mGraph = new TreeMap<T, TreeMap<T, Double>>();
		mVertexCount = 0;
		mEdgeCount = 0;
	}

	public Graph(Graph<T> g) {
		mGraph = new TreeMap<T, TreeMap<T, Double>>(g.mGraph);
		mVertexCount = g.mVertexCount;
		mEdgeCount = g.mEdgeCount;
	}

	public boolean addVertex(T v) {
		if (hasVertex(v))
			return false; // Can't repeat vertices

		mVertexCount++;

		// Empty adjacency list
		TreeMap<T, Double> aux = new TreeMap<T, Double>();
		mGraph.put(v, aux); // Vertex added to the graph

		return true;
	}

	public boolean hasVertex(T v) {
		return mGraph.containsKey(v);
	}

	public boolean removeVertex(T v) {
		// Not in the graph
		if (!mGraph.containsKey(v))
			return false;

		// We have to remove first all the occurrences in the adjacency lists
		for (Map<T, Double> adjList : mGraph.values())
			adjList.remove(v);

		// We finally remove it from the graph
		mGraph.remove(v);
		mVertexCount--;

		return true;
	}

	public int InDegree(T v) {
		if (!mGraph.containsKey(v))
			return 0;

		int counter = 0;
		// Look for the vertex in all the adjacency lists
		for (Map<T, Double> adjList : mGraph.values())
			if (adjList.containsKey(v))
				counter++;

		return counter;
	}

	public int OutDegree(T v) {
		if (!mGraph.containsKey(v))
			return 0;

		Map<T, Double> adjList = mGraph.get(v);

		return adjList.size();
	}

	public boolean hasEdge(T from, T to) {
		/*
		 * if (from.equals(to))
		 * return true;
		 */

		if (!mGraph.containsKey(from) || !mGraph.containsKey(to))
			return false;

		Map<T, Double> adjList = mGraph.get(from);

		return (adjList.containsKey(to));
	}

	public boolean addEdge(T from, T to, Double weight) {
		if (hasEdge(from, to))
			return false;

		mEdgeCount++;

		addVertex(from);
		addVertex(to);

		Map<T, Double> adjList = mGraph.get(from);
		adjList.put(to, weight);

		return true;
	}

	public boolean removeEdge(T from, T to) {
		if (!hasEdge(from, to))
			return false;

		Map<T, Double> adjList = mGraph.get(from);
		adjList.remove(to);

		mEdgeCount--;

		return true;
	}

	public Double edgeWeight(T from, T to) {
		/*
		 * if (from.equals(to))
		 * return 0.0;
		 */

		if (!hasEdge(from, to))
			return Double.POSITIVE_INFINITY;

		Map<T, Double> adjList = mGraph.get(from);
		return adjList.get(to);
	}

	public void print() {
		for (T vertex : mGraph.keySet()) {
			System.out.print(vertex + " -> ");

			Map<T, Double> adjList = mGraph.get(vertex);
			for (Entry<T, Double> adjV : adjList.entrySet())
				System.out.printf("(%s, %g); ", adjV.getKey(), adjV.getValue());

			System.out.println();
		}

		System.out.println();
	}
}