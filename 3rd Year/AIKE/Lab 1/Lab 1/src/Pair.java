import org.jetbrains.annotations.NotNull;

public class Pair<T>{

    private T weight;
    private String line;

    public Pair() {
        weight = null;
        line = "";
    }

    public Pair(T weight, String line){
        this.weight = weight;
        this.line = line;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}