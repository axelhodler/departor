package xorrr.de.util;

public final class JsoupSelectors {
	private JsoupSelectors() {}

	public static final String DEPARTURES = "itdRequest > itdDepartureMonitorRequest"
			+ " > itdDepartureList > itdDeparture";
	public static final String ROUTE = XmlTags.SERVING_LINE + " > itdRouteDescText";
	public static final String TIME = "itdDateTime > itdTime";
	public static final String DATE = "itdDateTime > itdDate";
}
