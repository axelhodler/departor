package xorrr.de.vvs;


public class DepartureInfo {

	private String line;
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
