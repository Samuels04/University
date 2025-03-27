import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    final static int MAX_ITERATIONS = 500;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String start, end, task;
        LocalTime startTime;

        System.out.print("What task would you like to execute? [1/2]: ");
        task = sc.next();

        switch (task) {
            case "1":

                System.out.print("Enter the optimization criterion [t/p]: ");
                char optimizationCriterion = sc.next().charAt(0);

                switch (optimizationCriterion) {
                    case 't':

                        System.out.print("Enter the starting stop: ");
                        start = sc.next();

                        System.out.print("Enter the ending stop: ");
                        end = sc.next();

                        System.out.print("Enter the start time [hh:mm]: ");
                        startTime = LocalTime.parse(sc.next());

                        System.out.print("Do you want to use Dijkstra's or A* [D/A]: ");
                        char algorithm = sc.next().charAt(0);

                        switch (algorithm) {
                            case 'D':
                                Task1A(start, end, startTime);
                                break;
                            case 'A':
                                Task1B(start, end, startTime);
                                break;
                        }
                        break;
                    case 'p':
                        System.out.print("Enter the starting stop: ");
                        start = sc.nextLine();

                        System.out.print("Enter the ending stop: ");
                        end = sc.nextLine();

                        System.out.print("Enter the start time [hh:mm]: ");
                        startTime = LocalTime.parse(sc.next());

                        Task1C(start, end, startTime);
                        break;
                }
                break;
            case "2":
                System.out.print("Which part do you want to check now? [q for exit]: ");
                char part = sc.next().charAt(0);

                start = "Babimojska";
                startTime = LocalTime.parse("19:00");
                List<String> stopsToVisit = Arrays.asList("park biznesu", "wrocławski park przemysłowy");

                switch (part) {
                    case 'a':
                        Task2A(start, startTime, stopsToVisit);
                        break;
                    case 'b':
                        Task2B();
                        break;
                    case 'c':
                        Task2C();
                        break;
                    case 'd':
                        Task2D();
                        break;
                    default:
                        System.exit(1);
                }
                break;
        }

        sc.close();

    }

    static Graph<String, Long> readFileToGraph(LocalTime departureTime) throws IOException {
        final String file = "C:\\Users\\samue\\Code\\University\\3rd Year\\AIKE\\Lab 1\\Lab 1\\src\\connection_graph.csv";

        Graph<String, Long> graph = new Graph<>();
        // Create an object of filereader
        // class with CSV file as a parameter.
        FileReader filereader = new FileReader(file);
        BufferedReader br = new BufferedReader(filereader);
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            // Get the next line in the CSV file
            String[] currentRow = line.split(",");

            // Extract all the fields in the line
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

            if (departure.isAfter(departureTime)) {
                // Only add those segments that departure after the start time of the journey
                graph.addVertex(start);
                graph.addVertex(end);
                // the weight of the edges will be the minutes in-between the stops
                long minutes = Duration.between(departure, arrival).toMinutes();
                graph.addEdge(start, end, minutes, lineT);
            } else
                continue;
        }
        return graph;
    }

    // #region Task 1
    static void Task1A(String src, String target, LocalTime startTime) throws IOException {

        Graph<String, Long> graph = readFileToGraph(startTime);
        List<List<String>> linesUsed = new ArrayList<>();
        String source = src.toLowerCase();
        // Create all necessary structures for the algorithm
        Map<String, Long> mDistances = new TreeMap<String, Long>(); // Distances from start vertex to another one
        Map<String, String> mPredecessors = new TreeMap<String, String>();
        Set<String> visited = new HashSet<String>(graph.getVertexCount() * 2); // Twice the number of vertices to reduce
                                                                               // the number of collisions and increase
                                                                               // the efficiency
        Set<String> unvisited = new HashSet<String>(graph.getAllVertices());

        for (String v : unvisited) {
            if (source.equals(v))
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

        while (!unvisited.isEmpty()) {
            String next = minDistDijkstra(mDistances, visited);
            visited.add(next);
            unvisited.remove(next);

            if (next == null)
                break; // Possible isolated vertices

            Set<String> adjacents = graph.adjacentsTo(next);
            for (String w : adjacents) {
                long dist_w = mDistances.get(w);
                long dist_next_w = mDistances.get(next) + graph.edgeWeight(next, w);
                if (dist_w > dist_next_w) {
                    mDistances.put(w, dist_next_w);
                    mPredecessors.put(w, next);
                }
            }
        }

        /*
         * Here finishes Dijkstra's algorithm, now I must find the path
         */
        List<String> path = new ArrayList<>();
        String current = target.toLowerCase();
        while (current != null && !current.equals(source)) {
            if (!path.contains(current))
                path.add(current);
            else {
                System.out.println("Reached a loop");
                break;
            }
            linesUsed.add(graph.edgeLines(current, mPredecessors.get(current)));
            current = mPredecessors.get(current);
        }
        path.add(source);
        linesUsed.add(graph.edgeLines(current, source));
        Collections.reverse(path);

        /*
         * I must now print the output of this algorithm
         */

        System.out.printf("The path starts in: %s\n", source);
        System.out.printf("The path ends in: %s\n", target);
        System.out.print("The path uses this lines: ");
        printLines(linesUsed);
        System.out.printf("The path began at: %s", startTime.toString());

    }

    // Method for calculating the next vertex with the minimum distance for
    // Dijkstra's algorithm
    static String minDistDijkstra(Map<String, Long> distances, Set<String> visited) {
        long minWeight = Long.MAX_VALUE;
        String selected = null;

        for (Map.Entry<String, Long> entry : distances.entrySet()) {
            long weight = entry.getValue();
            String v = entry.getKey();
            if (!visited.contains(v) && weight < minWeight) {
                minWeight = weight;
                selected = v;
            }
        }
        return selected;
    }

    static void printLines(List<List<String>> lines) {

        Iterator<List<String>> itr1 = lines.iterator();
        while (itr1.hasNext()) {
            List<String> line = itr1.next();
            if (line != null) {
                Iterator<String> itr = line.iterator();
                while (itr.hasNext()) {
                    System.out.printf("%s ", itr.next());
                }
            }
        }
        System.out.print("\n");

    }

    static void Task1B(String src, String trgt, LocalTime startTime) throws IOException {
        Graph<String, Long> graph = readFileToGraph(startTime);
        String source = src.toLowerCase();
        String target = trgt.toLowerCase();

        PriorityQueue<NodeRecord> open = new PriorityQueue<>();
        Map<String, NodeRecord> allNodes = new HashMap<>();
        Set<String> closed = new HashSet<>();

        // Initialize the starting node record.
        NodeRecord startRecord = new NodeRecord(source, 0, 0, null, null);
        open.add(startRecord);
        allNodes.put(source, startRecord);

        // Start counting the time here as it is when we start looking for the shortest
        // path
        long startTimeNano = System.nanoTime();
        // Main loop of the A* algorithm.
        while (!open.isEmpty()) {
            NodeRecord current = open.poll();

            // If the target is reached, break out of the loop.
            if (current.getNode().equals(target)) {
                allNodes.put(target, current);
                break;
            }

            closed.add(current.getNode());

            // Explore neighbours of the current node.
            for (String neighbor : graph.adjacentsTo(current.getNode())) {
                // Retrieve travel time (in minutes) and the lines used.
                Long travelTime = graph.edgeWeight(current.getNode(), neighbor);
                List<String> linesUsed = graph.edgeLines(current.getNode(), neighbor);
                if (travelTime == null)
                    continue;

                // Calculate the tentative cost to reach the neighbour.
                long tentativeG = current.getG() + travelTime;

                NodeRecord neighborRecord = allNodes.get(neighbor);
                if (neighborRecord == null) {
                    // Create a new record for an unvisited neighbour.
                    neighborRecord = new NodeRecord(neighbor, tentativeG, tentativeG, current, linesUsed);
                    allNodes.put(neighbor, neighborRecord);
                    open.add(neighborRecord);
                } else if (tentativeG < neighborRecord.getG()) {
                    // Update the record if a more efficient path is found.
                    neighborRecord.setG(tentativeG);
                    neighborRecord.setF(tentativeG);
                    neighborRecord.setParent(current);
                    neighborRecord.setCurrentLine(linesUsed);

                    // Refresh the priority queue by removing and reinserting the updated record.
                    open.remove(neighborRecord);
                    open.add(neighborRecord);
                }
            }
        }

        long endTimeNano = System.nanoTime(); // End timing the main loop
        double executionTimeMillis = (endTimeNano - startTimeNano) / 1_000_000.0;
        // Reconstruct the path from src to target if it exists.
        NodeRecord targetRecord = allNodes.get(target);
        if (targetRecord == null || !targetRecord.getNode().equals(target)) {
            // No path was found.
            System.out.println("No path was found from " + src + " to " + trgt + ".");
            return;
        }

        LinkedList<String> path = new LinkedList<>();
        LinkedList<List<String>> lines = new LinkedList<>();
        for (NodeRecord nodeRec = targetRecord; nodeRec != null; nodeRec = nodeRec.getParent()) {
            path.addFirst(nodeRec.getNode());
            if (nodeRec.getParent() != null) {
                lines.addFirst(graph.edgeLines(nodeRec.getParent().getNode(), nodeRec.getNode()));
            }
        }

        // Print the required details.
        System.out.println("Start node: " + src);
        System.out.println("End node: " + target);
        System.out.println("Path details:");

        // For each consecutive pair in the path, print the travel time and lines used.
        Iterator<String> it = path.iterator();
        Iterator<List<String>> lineIt = lines.iterator();
        String from = it.next();
        long totalTime = 0;
        while (it.hasNext()) {
            String to = it.next();
            List<String> lineList = lineIt.hasNext() ? lineIt.next() : Collections.singletonList("Unknown");
            // Retrieve the travel time for the edge from 'from' to 'to'
            Long travelTime = graph.edgeWeight(from, to);
            System.out.println("From: " + from + " to: " + to + " via lines: " + String.join(", ", lineList)
                    + " (Time: " + travelTime + " min)");
            totalTime += (travelTime != null ? travelTime : 0);
            from = to;
        }

        // Calculate arrival time by adding the total travel time to the starting time.
        LocalTime arrivalTime = startTime.plusMinutes(totalTime);
        System.out.println("Total travel time: " + totalTime + " min");
        System.out.println("Arrival time: " + arrivalTime);
        System.err.println("Pathfinding execution time: " + executionTimeMillis + " ms");
    }

    /**
     * Heuristic function: in our case, we opt for an optimistic estimate,
     * assuming no additional transfers nor travel time, and hence return 0.
     * (More sophisticated heuristics may be incorporated if such information is
     * available.)
     */
    static long heuristic(String current, String goal) {
        return 0;
    }

    static void Task1C(String src, String trgt, LocalTime startTime) throws IOException {
        Graph<String, Long> graph = readFileToGraph(startTime);
        String source = src.toLowerCase();
        String target = trgt.toLowerCase();

        PriorityQueue<NodeRecord> open = new PriorityQueue<>();
        Map<String, NodeRecord> allNodes = new HashMap<>();
        Set<String> closed = new HashSet<>();

        // Initialize the starting node record.
        NodeRecord startRecord = new NodeRecord(source, 0, 0, null, null);
        open.add(startRecord);
        allNodes.put(source, startRecord);

        // Main loop of the A* algorithm.
        while (!open.isEmpty()) {
            NodeRecord current = open.poll();

            // If the target is reached, break out of the loop.
            if (current.getNode().equals(target)) {
                allNodes.put(target, current);
                break;
            }

            closed.add(current.getNode());

            // Explore neighbours of the current node.
            for (String neighbor : graph.adjacentsTo(current.getNode())) {

                // Retrieve travel time (in minutes) and the lines used.
                Long travelTime = graph.edgeWeight(current.getNode(), neighbor);

                List<String> linesUsed = graph.edgeLines(current.getNode(), neighbor);
                if (travelTime == null || linesUsed == null || linesUsed.isEmpty())
                    continue;

                // Determine if a transfer occurs.
                int transferPenalty = 0;

                if (current.getCurrentLine() != null && Collections.disjoint(current.getCurrentLine(), linesUsed)) {
                    transferPenalty = 1; // Count transfer if no shared line exists
                }

                // Calculate the tentative cost to reach the neighbour (minimizing transfers).
                long tentativeG = current.getG() + transferPenalty;

                NodeRecord neighborRecord = allNodes.get(neighbor);

                if (neighborRecord == null) {
                    // Create a new record for an unvisited neighbour.
                    neighborRecord = new NodeRecord(neighbor, tentativeG, tentativeG, current, linesUsed);
                    allNodes.put(neighbor, neighborRecord);
                    open.add(neighborRecord);
                } else if (tentativeG < neighborRecord.getG()) {
                    // Update the record if a more efficient path is found.
                    neighborRecord.setG(tentativeG);
                    neighborRecord.setF(tentativeG);
                    neighborRecord.setParent(current);
                    neighborRecord.setCurrentLine(linesUsed);

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
            return;
        }

        LinkedList<String> path = new LinkedList<>();
        LinkedList<List<String>> lines = new LinkedList<>();
        for (NodeRecord nodeRec = targetRecord; nodeRec != null; nodeRec = nodeRec.getParent()) {
            path.addFirst(nodeRec.getNode());
            if (nodeRec.getParent() != null) {
                lines.addFirst(graph.edgeLines(nodeRec.getParent().getNode(), nodeRec.getNode()));
            }
        }

        // Print the required details.
        System.out.println("Start node: " + src);
        System.out.println("End node: " + target);
        System.out.println("Path details:");

        // For each consecutive pair in the path, print the travel time and lines used.
        Iterator<String> it = path.iterator();
        Iterator<List<String>> lineIt = lines.iterator();
        String from = it.next();
        int totalTransfers = 0;
        while (it.hasNext()) {
            String to = it.next();
            List<String> lineList = lineIt.hasNext() ? lineIt.next() : Collections.singletonList("Unknown");

            // Determine if a transfer occurs.
            if (!lineList.isEmpty() && it.hasNext()) {
                List<String> nextLines = lineIt.hasNext() ? lineIt.next() : Collections.emptyList();
                if (Collections.disjoint(lineList, nextLines)) {
                    totalTransfers++;
                }
            }

            // Retrieve the travel time for the edge from 'from' to 'to'
            Long travelTime = graph.edgeWeight(from, to);
            System.out.println("From: " + from + " to: " + to + " via lines: " + String.join(", ", lineList)
                    + " (Time: " + travelTime + " min)");
            from = to;
        }

        // Print total transfers.
        System.out.println("Total transfers: " + totalTransfers);
    }
    // #endregion

    static void Task2A(String src, LocalTime departureTime, List<String> mustVisit) throws IOException {

        // * Define the variables
        Graph<String, Long> graph = readFileToGraph(departureTime);
        String source = src.toLowerCase();

        // ! Record the start time for performance measurement.
        long searchStartTime = System.nanoTime();

        // Use ArrayDeque for the solutions (current and best paths).
        Deque<String> bestSolution = new ArrayDeque<>();
        Deque<String> currentSolution = new ArrayDeque<>();

        // Tabu list is a Set of stops.
        Set<String> tabuList = new LinkedHashSet<>();

        // Initialize both solutions with the source stop.
        currentSolution.addLast(source);
        bestSolution.addLast(source);
        Long bestCost = Long.MAX_VALUE;

        // - Main search loop, iterating up to MAX_ITERATIONS times.
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            // Get the last stop in the current path.
            String currentStop = currentSolution.peekLast();

            // ? Retrieve and sort neighbours of the current stop:
            // ? Filter out stops that are already visited (unless they are mandatory).
            // ? Sort the remaining neighbours based on the travel time (edge weight).
            List<String> neighbours = graph.adjacentsTo(currentStop)
                    .stream()
                    .filter(n -> {
                        if (!currentSolution.contains(n))
                            return true;
                        if (mustVisit.contains(n))
                            return true;
                        if (n.equals(source) && currentSolution.containsAll(mustVisit))
                            return true;
                        return false;
                    })
                    .sorted(Comparator.comparingLong(n -> graph.edgeWeight(currentStop, n)))
                    .collect(Collectors.toList());

            String bestNeighbour = null;
            Long bestTotalCost = null;

            // * Iterate through sorted neighbours to find the best candidate.
            for (String neighbour : neighbours) {
                // Skip this neighbour if it is in the tabu list.
                if (tabuList.contains(neighbour))
                    continue;

                // - Create a new potential solution by copying currentSolution and appending
                // - the
                // - neighbour.
                Deque<String> newSolution = new ArrayDeque<>(currentSolution);
                newSolution.addLast(neighbour);

                // ? Calculate the total travel time for the new solution.
                long totalCost = 0;
                Iterator<String> it = newSolution.iterator();
                if (it.hasNext()) {
                    String prev = it.next();
                    while (it.hasNext()) {
                        String curr = it.next();
                        totalCost += graph.edgeWeight(prev, curr);
                        prev = curr;
                    }
                }

                // % Update bestNeighbour if this new solution is better.
                if (bestTotalCost == null || totalCost < bestTotalCost) {
                    bestNeighbour = neighbour;
                    bestTotalCost = totalCost;
                }
            }

            // If no valid neighbour was found, exit the loop.
            if (bestNeighbour == null)
                break;

            // Add the best neighbour to the current solution.
            currentSolution.addLast(bestNeighbour);
            // Add the best neighbour to the tabu list to avoid immediate revisiting.
            tabuList.add(bestNeighbour);

            // Check if the current solution visits all required stops and returns to the
            // source.
            if (currentSolution.containsAll(mustVisit) && currentSolution.peekLast().equals(source)) {

                long totalCost = 0;

                Iterator<String> it = currentSolution.iterator();

                if (it.hasNext()) {

                    String prev = it.next();

                    while (it.hasNext()) {

                        String curr = it.next();
                        totalCost += graph.edgeWeight(prev, curr);
                        prev = curr;

                    }

                }
                // Update bestSolution if this complete tour is better.
                if (totalCost < bestCost) {
                    bestCost = totalCost;
                    bestSolution = new ArrayDeque<>(currentSolution);
                }
            }
        }

        // ! Calculate the execution duration.
        long searchEndTime = System.nanoTime();
        long execDuration = searchEndTime - searchStartTime;

        // - Print the detailed path information.
        long totalTravelTime = 0;
        LocalTime currentTime = departureTime;
        List<String> bestSolutionList = new ArrayList<>(bestSolution);

        for (int i = 0; i < bestSolutionList.size() - 1; i++) {

            String from = bestSolutionList.get(i);
            String to = bestSolutionList.get(i + 1);

            long travelTime = graph.edgeWeight(from, to);
            totalTravelTime += travelTime;

            LocalTime arrivalTime = currentTime.plusMinutes(travelTime);
            List<String> lines = graph.edgeLines(from, to);

            if (lines != null) {

                System.out.printf("From: %s to: %s via lines: %s (Start: %s, End: %s)\n", from, to,
                        String.join(", ", lines), currentTime.toString(), arrivalTime.toString());

            }
            currentTime = arrivalTime;

        }

        LocalTime finalArrivalTime = departureTime.plusMinutes(totalTravelTime);

        System.out.printf("Search completed. Source: %s, Arrival time: %s", source, finalArrivalTime);
        System.err.printf("Minimized travel time: %d", bestCost);
        System.err.printf("Execution time (nanoseconds): %d", execDuration);
    }

    static void Task2B() {
        System.out.print(
                "I am really, really sorry, but I did not have time to do this because part A was giving me so much trouble");
    }

    static void Task2C() {
        System.out.print(
                "I am really, really sorry, but I did not have time to do this because part A was giving me so much trouble");
    }

    static void Task2D() {
        System.out.print(
                "I am really, really sorry, but I did not have time to do this because part A was giving me so much trouble");
    }
}
