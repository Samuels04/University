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
                        Task1B(start, end, startTime);
                        break;
                }
                break;
            case 'p':
                Task1C(start, end, startTime);
                break;
        }
    }


    static void Task1A(String src, String target, LocalTime startTime) throws IOException {
        final String file = "C:\\Users\\samue\\Code\\University\\3rd Year\\AIKE\\Lab 1\\Lab 1\\src\\connection_graph.csv";
        Graph<String, Long> graph = new Graph<>();
        List<String> linesUsed = new ArrayList<>();
        String source = src.toLowerCase();
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
                //the weight of the edges will be the minutes in-between the stops
                long minutes = Duration.between(departure, arrival).toMinutes();
                graph.addEdge(start, end, minutes, lineT);
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
        String current = target.toLowerCase();
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
        printLines(linesUsed);
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
    static void printLines(List<String> lines) {

        Iterator<String> itr= lines.iterator();
        while(itr.hasNext()){
            System.out.printf("%s ", itr.next());
        }
        System.out.print("\n");

    }
    static void Task1B(String src, String target, LocalTime startTime) throws IOException {

    }
    // Heuristic function: in our case, we opt for an optimistic estimate,
    // assuming no additional transfers nor travel time, and hence return 0.
    // (More sophisticated heuristics may be incorporated if such information is available.)
    static long heuristic(String current, String goal) {
        return 0;
    }
    static void Task1C(String src, String trgt, LocalTime startTime) throws IOException{
        final String file = "C:\\Users\\samue\\Code\\University\\3rd Year\\AIKE\\Lab 1\\Lab 1\\src\\connection_graph.csv";
        Graph<String, Long> graph = new Graph<>();
        List<String> linesUsed = new ArrayList<>();
        String source = src.toLowerCase();
        String target = trgt.toLowerCase();
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

            if (departure.isAfter(startTime)) {
                //Only add those segments that departure after the start time of the journey
                graph.addVertex(start);
                graph.addVertex(end);
                //the weight of the edges will be the minutes in-between the stops
                long minutes = Duration.between(departure, arrival).toMinutes();
                graph.addEdge(start, end, minutes, lineT);
            } else
                continue;
        }
        // Define the penalty in minutes for transferring between lines.
        final long TRANSFER_PENALTY = 10;

        // Local class to encapsulate node records in our search.

        PriorityQueue<NodeRecord> open = new PriorityQueue<>();

        Map<String, NodeRecord> allNodes = new HashMap<>();
        Set<String> closed = new HashSet<>();

        // Initialize the starting node record.
        NodeRecord startRecord = new NodeRecord(source, 0, heuristic(source, target), null, null);
        open.add(startRecord);
        allNodes.put(source, startRecord);
        long iterations = 1;
        // Main loop of the A* algorithm.
        while (!open.isEmpty()) {
            iterations++;
            System.out.println(iterations);
            NodeRecord current = open.poll();

            // If the target is reached, break out of the loop.
            if (current.getNode().equals(target)) {
                allNodes.put(target, current);
                break;
            }

            closed.add(current.getNode());

            // Explore neighbours of the current node.
            for (String neighbor : graph.adjacentsTo(current.getNode())) {
                // Retrieve travel time (in minutes) and line information for the edge.
                Long travelTime = graph.edgeWeight(current.getNode(), neighbor);
                if (travelTime == null) continue;
                String edgeLine = graph.edgeLine(current.getNode(), neighbor);

                // Determine if a transfer occurs (i.e. change of line).
                long transferCost = 0;
                if (current.getCurrentLine() != null && !current.getCurrentLine().equals(edgeLine)) {
                    transferCost = TRANSFER_PENALTY;
                }

                // Calculate the tentative cost to reach the neighbour.
                long tentativeG = current.getG() + travelTime + transferCost;

                NodeRecord neighborRecord = allNodes.get(neighbor);
                if (neighborRecord == null) {
                    // Create a new record for an unvisited neighbour.
                    neighborRecord = new NodeRecord(
                            neighbor,
                            tentativeG,
                            tentativeG + heuristic(neighbor, target),
                            current,
                            edgeLine
                    );
                    allNodes.put(neighbor, neighborRecord);
                    open.add(neighborRecord);
                } else if (tentativeG < neighborRecord.getG()) {
                    // Update the record if a more efficient path is found.
                    neighborRecord.setG(tentativeG);
                    neighborRecord.setF(tentativeG + heuristic(neighbor, target));
                    neighborRecord.setParent(current);
                    neighborRecord.setCurrentLine(edgeLine);

                    // Refresh the priority queue by removing and reinserting the updated record.
                    open.remove(neighborRecord);
                    open.add(neighborRecord);
                }
            }
        }

        // Reconstruct the path from src to target if it exists.
        NodeRecord targetRecord = allNodes.get(target);
        if (targetRecord == null || !targetRecord.getNode().equals(target)) {
            // No path was found.
            System.out.println("No path was found from " + src + " to " + trgt + ".");
            System.exit(1);
        }

        LinkedList<String> path = new LinkedList<>();
        for (NodeRecord nodeRec = targetRecord; nodeRec != null; nodeRec = nodeRec.getParent()) {
            path.addFirst(nodeRec.getNode());
        }

        // Print the required details.
        System.out.println("Start node: " + src);
        System.out.println("End node: " + trgt);
        System.out.println("Path details:");

        // For each consecutive pair in the path, print the line used.
        Iterator<String> it = path.iterator();
        String from = it.next();
        while (it.hasNext()) {
            String to = it.next();
            // Retrieve the line used for the edge from 'from' to 'to'
            String lineUsed = graph.edgeLine(from, to);
            System.out.println("From: " + from + " to: " + to + " via line: " + lineUsed);
            from = to;
        }

        // Calculate arrival time by adding the total cost in minutes to the starting time.
        LocalTime arrivalTime = startTime.plusMinutes(targetRecord.getG());
        System.out.println("Arrival time: " + arrivalTime);

    }
    static void Task1D(){}
}
