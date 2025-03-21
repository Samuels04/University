import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Time;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        final String file = "C:\\Users\\samue\\Code\\University\\3rd Year\\AIKE\\Lab 1\\Lab 1\\src\\connection_graph.csv";
        Graph transportGraph;
        List<Segment> segments = new ArrayList<>();


    }

    static void readFile(String file, Graph transportGraph) {

        try {
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
                Time departure = Time.valueOf(currentRow[3]);
                Time arrival = Time.valueOf(currentRow[4]);
                String start = currentRow[5];
                String end = currentRow[6];
                double startLat = Double.parseDouble(currentRow[7]);
                double startLon = Double.parseDouble(currentRow[8]);
                double endLat = Double.parseDouble(currentRow[9]);
                double endLon = Double.parseDouble(currentRow[10]);

                //Create a new segment with the fields
                Segment segment = new Segment(id, company, lineT, departure, arrival, start, end, startLat, startLon, endLat, endLon);

                //Add the segment and the stops to the graph
                transportGraph.addVertex(segment.getStartStop());
                transportGraph.addVertex(segment.getEndStop());
                if(transportGraph instanceof GraphTransportDistance)
                    ((GraphTransportDistance) transportGraph)
                            .addEdge(segment, segment.getLength());
            }

            } catch (Exception e) {
            e.printStackTrace();
            }
        }

        static void Task1(){}
        static void Task2(){}
        static void Task3(){}
        static void Task4(){}
}
