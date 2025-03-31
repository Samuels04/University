import java.time.LocalTime;
import java.util.*;

public class Graph<T>{
	private Map<T, TreeMap<T, Pair>> mGraph;
	private int mVertexCount;
	private int mEdgeCount;

	public Set<T> adjacentsTo(T v)
	{
		if (hasVertex(v))
			return mGraph.get(v).keySet();
		else
			return new HashSet<>();
	}

	public Set<T> getAllVertices()
	{
		return mGraph.keySet();
	}

	public int getVertexCount()
	{
		return mVertexCount;
	}

	public int getEdgeCount()
	{
		return mEdgeCount;
	}

	public Graph()
	{
		mGraph = new TreeMap<T, TreeMap<T, Pair>> ();
		mVertexCount=0;
		mEdgeCount=0;
	}

	public Graph(Graph<T> g)
	{
		mGraph = new TreeMap<T, TreeMap<T, Pair>> (g.mGraph);
		mVertexCount = g.mVertexCount;
		mEdgeCount = g.mEdgeCount;
	}

	public boolean addVertex(T v)
	{
		if (hasVertex(v))
			return false;   //Can't repeat vertices

		mVertexCount++;

		//Empty adjacency list
		TreeMap<T, Pair> aux = new TreeMap<T, Pair>();
		mGraph.put(v, aux);  //Vertex added to the graph

		return true;
	}

	public boolean hasVertex(T v)
	{
		return mGraph.containsKey(v);
	}

	public boolean removeVertex(T v)
	{
		//Not in the graph
		if (!mGraph.containsKey(v))
			return false;

		//We have to remove first all the occurrences in the adjacency lists
		for (Map<T, Pair> adjList : mGraph.values())
			adjList.remove(v);

		//We finally remove it from the graph
		mGraph.remove(v);
		mVertexCount--;

		return true;
	}

	public int InDegree(T v)
	{
		if (!mGraph.containsKey(v))
			return 0;

		int counter = 0;
		//Look for the vertex in all the adjacency lists
		for (Map<T, Pair> adjList : mGraph.values())
			if (adjList.containsKey(v))
				counter++;

		return counter;
	}

	public int OutDegree(T v)
	{
		if (!mGraph.containsKey(v))
			return 0;

		Map<T, Pair> adjList = mGraph.get(v);

		return adjList.size();
	}

	public boolean hasEdge(T from, T to)
	{
		if (from.equals(to))
			 return true;

		if (!mGraph.containsKey(from) || !mGraph.containsKey(to))
			return false;

		Map<T, Pair> adjList = mGraph.get(from);

		return (adjList.containsKey(to));
	}

	public boolean addEdge(T from, T to, String line, String departure, String arrival)
	{
		if (hasEdge(from,to) && this.edgeLines(from, to) != null){
			if(this.edgeLines(from, to).contains(line)) {
				return false;
			} else {
				this.edgeLines(from, to).addLast(line);
				return true;
			}
		}


		mEdgeCount++;

		addVertex(from);
		addVertex(to);

		Map<T, Pair> adjList = mGraph.get(from);
		List<String> lines = new ArrayList<>();
		lines.add(line);
		adjList.put(to, new Pair(departure, arrival, lines));

		return true;
	}

	public boolean removeEdge(T from, T to)
	{
		if (!hasEdge(from, to))
			return false;

		Map<T, Pair> adjList = mGraph.get(from);
		adjList.remove(to);

		mEdgeCount--;

		return true;
	}

	public long edgeWeight(T from, T to)
	{
		 /*if (from.equals(to))
			 return 0.0;*/

		if (!hasEdge(from, to))
			throw new IllegalArgumentException("There is no edge from " + from.toString() + " to " + to.toString());

		Map<T, Pair> adjList = mGraph.get(from);
		return adjList.get(to).getWeight();
	}

	public List<String> edgeLines(T from, T to) {
		if (!hasEdge(from, to) || from.equals(to))
			return null;

		Map<T, Pair> adjList = mGraph.get(from);
		return adjList.get(to).getLines();
	}

}