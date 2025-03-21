import java.util.Map;
import java.util.Set;

public interface Graph {
    Set<String> adjacentsTo(String v);
    Set<String> getAllVertices();
    int getVertexCount();
    public int getEdgeCount();
    boolean addVertex(String v);
    boolean hasVertex(String v);
    boolean removeVertex(String v);
    boolean hasEdge(Segment edge);
    boolean removeEdge(Segment edge);
}