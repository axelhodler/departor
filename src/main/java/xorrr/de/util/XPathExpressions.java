package xorrr.de.util;

public final class XPathExpressions {
	private XPathExpressions() {
	}

	public static final String DEPARTURES = "/itdRequest/itdDepartureMonitorRequest/"
			+ "itdDepartureList/itdDeparture";
	public static final String SERVING_LINE = "itdServingLine";
	public static final String ROUTE = "itdServingLine/itdRouteDescText";
	public static final String TIME = "itdDateTime/itdTime";
}
