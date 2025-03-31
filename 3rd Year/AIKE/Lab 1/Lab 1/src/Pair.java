import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Pair {

    private long weight;
    private List<String> lines;
    private LocalTime arrival;
    private LocalTime departure;
    private boolean isArrivalNextDay;
    private boolean isDepartureNextDay;

    public Pair() {
        weight = 0;
        lines = new ArrayList<>();
        arrival = null;
        departure = null;
        isArrivalNextDay = false;
        isDepartureNextDay = false;
    }

    public Pair(String departure, String arrival, List<String> lines) {
        this();
        this.lines = lines;
        setArrival(arrival);
        setDeparture(departure);
    }

    public long getWeight() {
        return weight;
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

    public LocalTime getArrival() {
        return this.arrival;
    }

    public LocalTime getDeparture() {
        return this.departure;
    }
    /**
     * Indica si el tiempo de llegada pertenece al día siguiente.
     */
    public boolean isArrivalNextDay() {
        return isArrivalNextDay;
    }

    /**
     * Indica si el tiempo de salida pertenece al día siguiente.
     */
    public boolean isDepartureNextDay() {
        return isDepartureNextDay;
    }

    public void setArrival(String arrival) {
        NormalizedTime normalized = normalizeTimeString(arrival);
        this.isArrivalNextDay = normalized.isNextDay();
        this.arrival = normalized.getTime();
        updateWeight();
    }

    public void setDeparture(String departure) {
        NormalizedTime normalized = normalizeTimeString(departure);
        this.isDepartureNextDay = normalized.isNextDay();
        this.departure = normalized.getTime();
        updateWeight();
    }

    private void updateWeight() {
        if(this.departure == null)
            this.departure = LocalTime.parse("00:00");
        else if (this.arrival != null) {
            // Ajustamos conceptualmente si alguno de los tiempos pertenece al día siguiente
            LocalTime adjustedDeparture = isDepartureNextDay ? this.departure.plusHours(24) : this.departure;
            LocalTime adjustedArrival = isArrivalNextDay ? this.arrival.plusHours(24) : this.arrival;
            this.weight = Duration.between(adjustedDeparture, adjustedArrival).toMinutes();
        } else {
            throw new IllegalArgumentException("Arrival is null");
        }
    }

    private NormalizedTime normalizeTimeString(String timeString) {
        // Dividir el tiempo en horas, minutos y segundos
        String[] parts = timeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        // Determinar si el tiempo pertenece al día siguiente
        boolean isNextDay = hours >= 24;

        // Ajustar las horas al rango válido (0-23)
        if (isNextDay) {
            hours -= 24;
        }

        // Crear un objeto LocalTime con el tiempo ajustado
        LocalTime normalizedTime = LocalTime.of(hours, minutes, seconds);

        return new NormalizedTime(normalizedTime, isNextDay);
    }

    static class NormalizedTime {
        private final LocalTime time;
        private final boolean isNextDay;

        public NormalizedTime(LocalTime time, boolean isNextDay) {
            this.time = time;
            this.isNextDay = isNextDay;
        }

        public LocalTime getTime() {
            return time;
        }

        public boolean isNextDay() {
            return isNextDay;
        }
    }

}