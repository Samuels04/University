import java.util.Collection;

import dataStructures.Graph;

public class GraphTest 
{
	public static void main(String[] args) 
	{
		Graph<String> graph = new Graph<String>();
		
		graph.addVertex("Gijón");
		graph.addVertex("Oviedo");
		graph.addVertex("Avilés");
		graph.addVertex("Villaviciosa");
		graph.addVertex("Cudillero");
		graph.addVertex("Mieres");
		graph.addVertex("León");
		
		graph.addEdge("Gijón", "Oviedo", Double.valueOf(30));
		graph.addEdge("Gijón", "Avilés", Double.valueOf(24));
		graph.addEdge("Gijón", "Villaviciosa", Double.valueOf(25));
		graph.addEdge("Gijón", "Mieres", Double.valueOf(35));
		
		graph.addEdge("Villaviciosa", "Oviedo", Double.valueOf(40));
		graph.addEdge("Oviedo", "Mieres", Double.valueOf(20));

		graph.addEdge("Avilés", "Cudillero", Double.valueOf(30));
		
		graph.addEdge("Mieres", "León", Double.valueOf(90));
		
		System.out.println("Initial graph");
		graph.print();
				
		System.out.println("Running Dijkstra's algorithm");
		graph.Dijkstra("Gijón");
		graph.printDijkstra();
		
		System.out.println("Running Dijkstra's algorithm (Heap version)");
		graph.DijkstraHeap("Gijón");
		graph.printDijkstra();
		
		System.out.println("Best path from Gijón to Cudillero");
		System.out.println("-----------------------");		
		Collection<String> path = graph.shortestPath("Gijón", "Cudillero");
		for (String node: path)
			System.out.print(node + " ");

		System.out.println();
		System.out.println();
		
		System.out.println("Best path from Villaciosa to León");
		System.out.println("-----------------------");		
		path = graph.shortestPath("Villaviciosa", "León");
		for (String node: path)
			System.out.print(node + " ");
		
		
		
		/*System.out.printf("\nIndegree for Oviedo: %d\n", graph.InDegree("Oviedo"));
		System.out.printf("Outdegree for Gijón: %d\n\n", graph.OutDegree("Gijón"));
		
		System.out.printf("Distance between Gijón and Oviedo: %g\n", graph.edgeWeight("Gijón", "Oviedo"));
		System.out.printf("Distance between Gijón and Cudillero: %g\n", graph.edgeWeight("Gijón", "Cudillero"));
		
		System.out.println("Removing edge between Gijón and Oviedo");
		graph.removeEdge("Gijón", "Oviedo");
		System.out.printf("Distance now between Gijón and Oviedo: %g\n", graph.edgeWeight("Gijón", "Oviedo"));
		
		System.out.println("\nRemoving vertex Gijón\n");
		graph.removeVertex("Gijón");
		graph.print();*/
	}
}
