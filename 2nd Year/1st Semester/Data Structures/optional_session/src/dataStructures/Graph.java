package dataStructures;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class Graph<T> 
{
	private Map<T, TreeMap<T, Double>> mGraph;
	private int mVertexCount;   	
	private int mEdgeCount;
	
	//Dijkstra's SP algorithm output
	Map<T, Double> mDistances;
	Map<T, T> mPredecessors;
	
	public Map<T, Double> getSP_Distances() 
	{
		return mDistances;
	}

	public Map<T, T> getSP_Predecessors() 
	{
		return mPredecessors;
	}
  	
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
		mGraph = new TreeMap<T, TreeMap<T,Double>> ();
		mVertexCount=0;
		mEdgeCount=0;
	}
	
	public Graph(Graph<T> g) 
	{
		mGraph = new TreeMap<T, TreeMap<T, Double>> (g.mGraph);
		mVertexCount = g.mVertexCount;
		mEdgeCount = g.mEdgeCount;
	}

	public boolean addVertex(T v)
	{
		if (hasVertex(v)) 
			return false;   //Can't repeat vertices
		
		mVertexCount++;
        
		//Empty adjacency list
        TreeMap<T, Double> aux = new TreeMap<T, Double>(); 		
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
		for (Map<T, Double> adjList : mGraph.values())
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
		for (Map<T, Double> adjList : mGraph.values())
			if (adjList.containsKey(v))
				counter++;
				 
		return counter;
	}
	 
	public int OutDegree(T v)
	{
		if (!mGraph.containsKey(v))
			 return 0;
		 
		Map<T, Double> adjList = mGraph.get(v);
		 
		return adjList.size();
	}

	public boolean hasEdge(T from, T to)
	{
		/*if (from.equals(to))
			 return true;*/
		 
		if (!mGraph.containsKey(from) || !mGraph.containsKey(to))
			 return false;
		 
		Map<T, Double> adjList = mGraph.get(from);
		 
		return (adjList.containsKey(to));		 
	}
	 
	public boolean addEdge(T from, T to, Double weight)
	{
		if (hasEdge(from,to))  
			return false;
		
		mEdgeCount++;
		
		addVertex(from); 
		addVertex(to);      
		
		Map<T, Double> adjList = mGraph.get(from);
		adjList.put(to, weight);
		
		return true;	
	} 

	public boolean removeEdge(T from, T to)
	{
		if (!hasEdge(from, to))
			return false;
		 
		Map<T, Double> adjList = mGraph.get(from);		 
		adjList.remove(to);
		 
		mEdgeCount--;
		 
		return true;
	}
	 
	public Double edgeWeight(T from, T to)
	{
		 /*if (from.equals(to))
			 return 0.0;*/
		 
		if (!hasEdge(from, to))
			return Double.POSITIVE_INFINITY;
		 
		Map<T, Double> adjList = mGraph.get(from);
		return adjList.get(to);
	}
	 
	public void print() 
	{
		for (T vertex: mGraph.keySet())
		{
			System.out.print(vertex + " -> ");
			 
			Map<T, Double> adjList = mGraph.get(vertex);
			for (Entry<T, Double> adjV: adjList.entrySet())
				 System.out.printf("(%s, %g); ", adjV.getKey(), adjV.getValue());
			 
			System.out.println();
		}
		 
		System.out.println();
	}
	 
	public void Dijkstra(T startVertex)
	{
		mDistances = new TreeMap<T, Double>();  //Distances from start vertex to another one
		mPredecessors = new TreeMap<T, T>();  
		Set<T> visited = new HashSet<T>(getVertexCount()*2); //Twice the number of vertices to reduce the number of collisions and increase the efficiency		 
		Set<T> unvisited = new HashSet<T>(mGraph.keySet());
		 
		for (T current: unvisited)
		{
			if (startVertex.equals(current))
				mDistances.put(current, 0.0);
			else
			{
				if (!hasEdge(startVertex, current))
					mDistances.put(current, Double.POSITIVE_INFINITY);
				else
				{
					mDistances.put(current, edgeWeight(startVertex, current));
					mPredecessors.put(current, startVertex);
				}
			}
		}
		 
		visited.add(startVertex);
		unvisited.remove(startVertex);
		 
		while (!unvisited.isEmpty())
		{
			T next = minDistDijkstra(mDistances, visited);   					
			visited.add(next);
			unvisited.remove(next);
			
			if (next == null)
				break; //Possible isolated vertices
			
			Set<T> adjacents = adjacentsTo(next);
			for (T w: adjacents)
			{
		        double dist_w = (double)mDistances.get(w);
		        double dist_next_w = (double)mDistances.get(next) + (double)edgeWeight(next, w);
		        if (dist_w > dist_next_w) 
		        {
		        	mDistances.put(w, dist_next_w);
		          	mPredecessors.put(w, next);
		        }
			}
		}		 
	}
	 
	private T minDistDijkstra(Map<T, Double> distances, Set<T> visited)
	{
		double minWeight=Double.POSITIVE_INFINITY;
		T selected = null;
		
		for (Entry<T, Double> entry : distances.entrySet())
		{
			double weight=entry.getValue().doubleValue();    
			T v = entry.getKey();			
			if (!visited.contains(v) && weight < minWeight)
			{
		       minWeight=weight;
		       selected=v;
  			}
		}
		return selected;
	}
	 	 	 
	 
	public void printDijkstra()
	{		 
		if ((mDistances == null) || (mPredecessors == null))
		{
			System.out.println("Run Dijkstra's algorithm first\n");
			return;
		}

		System.out.println("Dijkstra's output\n");
		 
		System.out.println("Distances");
		System.out.println("-----------------------");
		for (Entry<T, Double> edge: mDistances.entrySet())
		{
			System.out.printf("(%s; %g) ", edge.getKey(), edge.getValue());
		}
		System.out.println("");
		 
		System.out.println("\nPredecessors");
		System.out.println("-----------------------");
		for (Entry<T, T> edge: mPredecessors.entrySet())
		{
			System.out.printf("(%s; %s) ", edge.getKey(), edge.getValue());
		}
		System.out.println("\n");
	}
}
