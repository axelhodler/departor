package xorrr.de;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.JsoupApiConnector;
import xorrr.de.api.RequestFields;
import xorrr.de.model.DepartureInfo;
import xorrr.de.util.JsoupSelectors;
import xorrr.de.util.TimeFormatter;
import xorrr.de.util.XmlAttributes;
import xorrr.de.util.XmlTags;

public class JsoupDepartureFinder implements DepartureFinder {

	private JsoupApiConnector api;
	private TimeFormatter tf;

	public JsoupDepartureFinder(JsoupApiConnector con) {
		this.api = con;
	}

	@Override
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields,
			TimeFormatter tf) {
		this.tf = tf;
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
		return getHour(e) + ":" + getMinute(e);
	}

	private String getMinute(Element e) {
		return tf.formatMinute(e.select(JsoupSelectors.TIME).attr(
				XmlAttributes.DEPARTURE_MINUTE));
	}

	private String getHour(Element e) {
		return e.select(JsoupSelectors.TIME).attr(XmlAttributes.DEPARTURE_HOUR);
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
