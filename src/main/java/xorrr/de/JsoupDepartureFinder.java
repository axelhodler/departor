package xorrr.de;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.JsoupApiConnector;
import xorrr.de.api.RequestFields;
import xorrr.de.model.DepartureInfo;
import xorrr.de.util.JsoupSelectors;
import xorrr.de.util.XmlAttributes;
import xorrr.de.util.XmlTags;

public class JsoupDepartureFinder implements DepartureFinder {

	private JsoupApiConnector api;

	public JsoupDepartureFinder(JsoupApiConnector con) {
		this.api = con;
	}

	@Override
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields) {
		Elements departures = getDepartures(api.getDocument(reqFields));

		List<DepartureInfo> infos = extractDepartures(departures);

		return infos;
	}

	private List<DepartureInfo> extractDepartures(Elements departures) {
		List<DepartureInfo> infos = new ArrayList<>();

		for (Element e : departures) {
			infos.add(createDepartureInfo(e));
		}
		return infos;
	}

	private DepartureInfo createDepartureInfo(Element e) {
		DepartureInfo info = new DepartureInfo();
		info.setLine(getLine(e));
		info.setRoute(getRoute(e));
		info.setDirection(getDirection(e));
		info.setTime(getTime(e));
		return info;
	}

	private String getTime(Element e) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
		calendar.clear();

		calendar.set(getYear(e), getMonth(e), getDay(e),
				getHour(e), getMinute(e));

		return String.valueOf(calendar.getTimeInMillis());
	}

	private int getMinute(Element e) {
		return Integer.valueOf(e.select(JsoupSelectors.TIME).attr(
				XmlAttributes.DEPARTURE_MINUTE));
	}

	private int getHour(Element e) {
		return Integer.valueOf(e.select(JsoupSelectors.TIME).attr(
				XmlAttributes.DEPARTURE_HOUR));
	}

	private int getYear(Element e) {
		return Integer.valueOf(e.select(JsoupSelectors.DATE).attr(
				XmlAttributes.DEPARTURE_YEAR));
	}

	private int getDay(Element e) {
		return Integer.valueOf(e.select(JsoupSelectors.DATE).attr(
				XmlAttributes.DEPARTURE_DAY));
	}

	private int getMonth(Element e) {
		return Integer.valueOf(e.select(JsoupSelectors.DATE).attr(
				XmlAttributes.DEPARTURE_MONTH));
	}

	private Elements getDepartures(Document doc) {
		return doc.select(JsoupSelectors.DEPARTURES);
	}

	private String getDirection(Element e) {
		return e.select(XmlTags.SERVING_LINE).attr(XmlAttributes.DIRECTION);
	}

	private String getRoute(Element e) {
		return e.select(JsoupSelectors.ROUTE).text();
	}

	private String getLine(Element e) {
		return e.select(XmlTags.SERVING_LINE).attr(XmlAttributes.LINE_NUMBER);
	}

}
