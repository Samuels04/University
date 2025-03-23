import org.jetbrains.annotations.NotNull;

public class Pair<T>{

    private T weight;
    private char line;

    public Pair() {
        weight = null;
        line = ' ';
    }

    public Pair(T weight, char line){
        this.weight = weight;
        this.line = line;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    public char getLine() {
        return line;
    }

    public void setLine(char line) {
        this.line = line;
    }
}