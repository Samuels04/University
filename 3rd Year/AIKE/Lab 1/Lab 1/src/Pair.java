import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Pair<T>{

    private T weight;
    private List<String> lines;

    public Pair() {
        weight = null;
        lines = null;
    }

    public Pair(T weight, List<String> lines){
        this.weight = weight;
        this.lines = lines;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public boolean hasLine(String line) {
        return this.lines.contains(line);
    }
}