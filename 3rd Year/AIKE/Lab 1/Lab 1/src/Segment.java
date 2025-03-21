import java.sql.Time;

public class Segment {

	static final int EQUATORIAL_EARTH_RADIUS = 6378;

	private int ID;
	private String company;
	private String line;
	private Time departureTime;
	private Time arrivalTime;
	private String startStop;
	private String endStop;
	private double startLat;
	private double startLon;
	private double endLat;
	private double endLon;

	public Segment() {
		this.ID = 0;
		this.company = null;
		this.line = null;
		this.departureTime = null;
		this.arrivalTime = null;
		this.startStop = null;
		this.endStop = null;
		this.startLat = 0.0;
		this.startLon = 0.0;
		this.endLat = 0.0;
		this.endLon = 0.0;
	}

	public Segment(int ID, String company, String line, Time departureTime, Time arrivalTime, String startStop,
			String endStop,
			double startLat, double startLon, double endLat, double endLon) {

		this.ID = ID;
		this.company = company;
		this.line = line;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.startStop = startStop;
		this.endStop = endStop;
		this.startLat = startLat;
		this.startLon = startLon;
		this.endLat = endLat;
		this.endLon = endLon;
	}

	public Segment(Segment s) {
		this.ID = s.ID;
		this.company = s.company;
		this.line = s.line;
		this.departureTime = s.departureTime;
		this.arrivalTime = s.arrivalTime;
		this.startStop = s.startStop;
		this.endStop = s.endStop;
		this.startLat = s.startLat;
		this.startLon = s.startLon;
		this.endLat = s.endLat;
		this.endLon = s.endLon;
	}

	public int getID() {
		return ID;
	}

	public String getCompany() {
		return company;
	}

	public String getLine() {
		return line;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public String getStartStop() {
		return startStop;
	}

	public String getEndStop() {
		return endStop;
	}

	public double getStartLat() {
		return startLat;
	}

	public double getStartLon() {
		return startLon;
	}

	public double getEndLat() {
		return endLat;
	}

	public double getEndLon() {
		return endLon;
	}

	public double getLength() {
		double distance = 0.0;
		// If the coordinates are the same, distance is 0.
		if (startLat == endLat && startLon == endLon)
			return distance;

		/**
		 * Equirectangular method for calculating distances between 2 points on the
		 * surface
		 * of the earth
		 */
		double startLatRad = Math.toRadians(startLat);
		double endLatRad = Math.toRadians(endLat);
		double startLonRad = Math.toRadians(startLon);
		double endLonRad = Math.toRadians(endLon);

		double x = (endLonRad - startLonRad) * Math.cos((startLatRad + endLatRad) / 2);
		double y = (endLatRad - startLatRad);
		distance = Math.sqrt(x * x + y * y) * EQUATORIAL_EARTH_RADIUS;

		return distance;
	}

}