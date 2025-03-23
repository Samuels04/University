import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        String start = args[0];
        String end = args[1];
        char optimizationCriterion = args[2].charAt(0);
        LocalTime startTime = LocalTime.parse(args[3]);
        Scanner sc = new Scanner(System.in);


        switch(optimizationCriterion){
            case 't':
                System.out.print("Do you want to use Dijkstra's or A* [D/A]: ");
                char algorithm = sc.next().charAt(0);
                switch (algorithm){
                    case 'D':
                        Task1A(start, end, startTime);
                        break;
                    case 'A':
                        Task1B();
                        break;
                }
                break;
            case 'p':
                Task1C();
                break;
        }
    }


    static void Task1A(String source, String target, LocalTime startTime) throws IOException {
        final String file = "C:\\Users\\samue\\Code\\University\\3rd Year\\AIKE\\Lab 1\\Lab 1\\src\\connection_graph.csv";
        Graph<String, Long> graph = new Graph<>();
        List<Character> linesUsed = new ArrayList<>();
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
            LocalTime departure = LocalTime.parse(currentRow[3]);
            LocalTime arrival = LocalTime.parse(currentRow[4]);
            String start = currentRow[5].toLowerCase();
            String end = currentRow[6].toLowerCase();
            double startLat = Double.parseDouble(currentRow[7]);
            double startLon = Double.parseDouble(currentRow[8]);
            double endLat = Double.parseDouble(currentRow[9]);
            double endLon = Double.parseDouble(currentRow[10]);

            if(departure.isAfter(startTime)){
                //Only add those segments that departure after the start time of the journey
                graph.addVertex(start);
                graph.addVertex(end);
                //the weight of the edges will be the seconds in-between the stops
                graph.addEdge(start, end, Duration.between(departure, arrival).toSeconds(), lineT.charAt(0));
            } else
                continue;

        }
        //Create all necessary structures for the algorithm
        Map<String, Long> mDistances = new TreeMap<String, Long>();  //Distances from start vertex to another one
        Map<String, String> mPredecessors = new TreeMap<String, String>();
        Set<String> visited = new HashSet<String>(graph.getVertexCount()*2); //Twice the number of vertices to reduce the number of collisions and increase the efficiency
        Set<String> unvisited = new HashSet<String>(graph.getAllVertices());

        for(String v : unvisited){
            if(source.equals(v))
                mDistances.put(v, 0L);
            else {
                if (!graph.hasEdge(source, v))
                    mDistances.put(v, Long.MAX_VALUE);
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
                long dist_w = mDistances.get(w);
                long dist_next_w = mDistances.get(next) + graph.edgeWeight(next, w);
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
            if(!path.contains(current))
                path.add(current);
            else {
                System.out.println("Reached a loop");
                break;
            }
            linesUsed.add(graph.edgeLine(current, mPredecessors.get(current)));
            current = mPredecessors.get(current);
        }
        path.add(source);
        linesUsed.add(graph.edgeLine(current, source));
        Collections.reverse(path);

    /*
        I must now print the output of this algorithm
     */

        System.out.printf("The path starts in: %s\n", source);
        System.out.printf("The path ends in: %s\n", target);
        System.out.print("The path uses this lines: ");
        System.out.printf("The path began at: %s", startTime.toString());

    }
    //Method for calculating the next vertex with the minimum distance for Dijkstra's algorithm
    static String minDistDijkstra(Map<String, Long> distances, Set<String> visited) {
        long minWeight = Long.MAX_VALUE;
        String selected = null;

        for (Map.Entry<String, Long> entry : distances.entrySet()) {
            long weight = entry.getValue();
            String v = entry.getKey();
            if (!visited.contains(v) && weight < minWeight ) {
                minWeight = weight;
                selected = v;
            }
        }
        return selected;
    }
    static void printLines(List<Character> lines) {

        Iterator<Character> itr= lines.iterator();
        while(itr.hasNext()){
            System.out.printf("%c ", itr.next());
        }
        System.out.print("\n");

    }
    static void Task1B(){}
    static void Task1C(){}
    static void Task1D(){}
}
