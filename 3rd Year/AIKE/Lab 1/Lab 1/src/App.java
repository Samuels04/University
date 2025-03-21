import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

    }


    static void Task1A(String source, String target, int startTime) throws IOException {
        final String file = "C:\\Users\\samue\\Code\\University\\3rd Year\\AIKE\\Lab 1\\Lab 1\\src\\connection_graph.csv";
        Graph<String, Integer> graph = new Graph<>();
        // Create an object of filereader
        // class with CSV file as a parameter.
        FileReader filereader = new FileReader(file);
        BufferedReader br = new BufferedReader(filereader);
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            //Get the next line in the CSV file
            String[] currentRow = line.split(",");

            //Extract all the fields in the line
            int id = Integer.parseInt(currentRow[0]);
            String company = currentRow[1];
            String lineT = currentRow[2];
            int departure = Integer.parseInt(currentRow[3]);
            int arrival = Integer.parseInt(currentRow[4]);
            String start = currentRow[5];
            String end = currentRow[6];
            double startLat = Double.parseDouble(currentRow[7]);
            double startLon = Double.parseDouble(currentRow[8]);
            double endLat = Double.parseDouble(currentRow[9]);
            double endLon = Double.parseDouble(currentRow[10]);
            if(departure > startTime){
                //Only add those segments that departure after the start time of the journey
                graph.addVertex(start);
                graph.addVertex(end);
                graph.addEdge(start, end, arrival-departure);
            } else
                continue;

        }
        //Create all necessary structures for the algorithm
        Map<String, Integer> mDistances = new TreeMap<String, Integer>();  //Distances from start vertex to another one
        Map<String, String> mPredecessors = new TreeMap<String, String>();
        Set<String> visited = new HashSet<String>(graph.getVertexCount()*2); //Twice the number of vertices to reduce the number of collisions and increase the efficiency
        Set<String> unvisited = new HashSet<String>(graph.getAllVertices());

        for(String v : unvisited){
            if(source.equals(v))
                mDistances.put(v, 0);
            else {
                if (!graph.hasEdge(source, v))
                    mDistances.put(v, Integer.MAX_VALUE);
                else {
                    mDistances.put(v, graph.edgeWeight(source, v));
                    mPredecessors.put(v, source);
                }
            }
        }
        visited.add(source);
        unvisited.remove(source);


        while(!unvisited.isEmpty()){
            String next = minDistDijkstra(mDistances, visited);
            visited.add(next);
            unvisited.remove(next);

            if (next == null)
                break; //Possible isolated vertices

            Set<String> adjacents = graph.adjacentsTo(next);
            for (String w: adjacents) {
                int dist_w = mDistances.get(w);
                int dist_next_w = mDistances.get(next) + graph.edgeWeight(next, w);
                if (dist_w > dist_next_w) {
                    mDistances.put(w, dist_next_w);
                    mPredecessors.put(w, next);
                }
            }
        }

        /*Here finishes Dijkstra's algorithm, now I must find the path
        */
        List<String> path = new ArrayList<>();
        String current = target;
        while (current != null && !current.equals(source)) {
            path.add(current);
            current = mPredecessors.get(current);
        }
        path.add(source);
        Collections.reverse(path);



    }
    //Method for calculating the next vertex with the minimum distance for Dijkstra's algorithm
    static String minDistDijkstra(Map<String, Integer> distances, Set<String> visited) {
        int minWeight = Integer.MAX_VALUE;
        String selected = null;

        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            int weight = entry.getValue();
            String v = entry.getKey();
            if (!visited.contains(v) && weight < minWeight ) {
                minWeight = weight;
                selected = v;
            }
        }
        return selected;
    }
    static void Task2B(){}
    static void Task3C(){}
    static void Task4D(){}
}
