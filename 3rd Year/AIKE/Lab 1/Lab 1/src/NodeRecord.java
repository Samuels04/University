import java.util.List;
import java.util.Objects;

public class NodeRecord implements Comparable<NodeRecord> {
    private String node;           // The node itself.
    private long g;                // Accumulated cost from src to this node.
    private long f;                // Estimated total cost (g + heuristic).
    private NodeRecord parent;     // Predecessor in the optimal path.
    private List<String> currentLine;    // The line used to reach this node.

    public NodeRecord(String node, long g, long f, NodeRecord parent, List<String> currentLine) {
        this.node = node;
        this.g = g;
        this.f = f;
        this.parent = parent;
        this.currentLine = currentLine;
    }

    @Override
    public int compareTo(NodeRecord other) {
        return Long.compare(this.f, other.f);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeRecord that = (NodeRecord) obj;
        return Objects.equals(node, that.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public long getG() {
        return g;
    }

    public void setG(long g) {
        this.g = g;
    }

    public long getF() {
        return f;
    }

    public void setF(long f) {
        this.f = f;
    }

    public NodeRecord getParent() {
        return parent;
    }

    public void setParent(NodeRecord parent) {
        this.parent = parent;
    }

    public List<String> getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(List<String> currentLine) {
        this.currentLine = currentLine;
    }
}
