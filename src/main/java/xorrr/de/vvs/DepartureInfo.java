package xorrr.de.vvs;


public class DepartureInfo {

	private String line;
	private String direction;
	private String time;
	private String route;

	public DepartureInfo() {
		// Jackson deserialization
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
