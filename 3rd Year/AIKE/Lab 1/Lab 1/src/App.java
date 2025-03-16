import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        final String file = "connection_graph.csv";
        Graph<String> transportGraph = new Graph<>();

        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            List<String[]> csv = csvReader.readAll();
            Iterator<String[]> fileIterator = csv.iterator();

            while (fileIterator.hasNext()) {
                String[] currentRow = fileIterator.next();

                // The first element in a row is the index, we will skip that
                // The second element is the company that manages the segment, always the same
                // The third element is the departure time from one station to the other

            }

            csvReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
