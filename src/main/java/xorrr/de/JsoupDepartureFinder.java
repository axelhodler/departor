package xorrr.de;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.JsoupApiConnector;
import xorrr.de.api.RequestFields;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.util.TimeFormatter;
import xorrr.de.util.XmlAttributes;
import xorrr.de.util.XmlTags;

public class JsoupDepartureFinder implements DepartureFinder {

	private JsoupApiConnector api;

	public JsoupDepartureFinder(JsoupApiConnector con) {
		this.api = con;
	}

	@Override
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields,
			TimeFormatter tf) {
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
		return info;
	}

	private Elements getDepartures(Document doc) {
		return doc.select("itdRequest > itdDepartureMonitorRequest > itdDepartureList > itdDeparture");
	}

	private String getDirection(Element e) {
		return e.select(XmlTags.SERVING_LINE).attr(XmlAttributes.DIRECTION);
	}

	private String getRoute(Element e) {
		return e.select("itdServingLine > itdRouteDescText").text();
	}

	private String getLine(Element e) {
		return e.select(XmlTags.SERVING_LINE).attr(XmlAttributes.LINE_NUMBER);
	}

}
