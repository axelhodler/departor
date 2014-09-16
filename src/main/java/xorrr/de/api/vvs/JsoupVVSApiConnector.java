package xorrr.de.api.vvs;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import xorrr.de.api.JsoupApiConnector;
import xorrr.de.api.RequestFields;

public class JsoupVVSApiConnector implements JsoupApiConnector {

	private final String API_URL = "http://www2.vvs.de/vvs/widget/XML_DM_REQUEST"
			+ "?zocationServerActive=1"
			+ "&lsShowTrainsExplicit=1"
			+ "&stateless=1"
			+ "&language=de"
			+ "&SpEncId=0"
			+ "&anySigWhenPerfectNoOtherMatches=1"
			+ "&limit=%s"
			+ "&depArr=departure"
			+ "&type_dm=any&anyObjFilter_dm=2"
			+ "&deleteAssignedStops=1"
			+ "&name_dm=%s"
			+ "&mode=direct&dmLineSelectionAll=1&"
			+ "itdDateYear=%s"
			+ "&itdDateMonth=%s"
			+ "&itdDateDay=%s"
			+ "&itdTimeHour=%s"
			+ "&itdTimeMinute=%s" + "&useRealtime=1";

	@Override
	public Document getDocument(RequestFields reqFields) {
		Document doc = null;
		try {
			doc = getDoc(addTimeRequestFields(reqFields));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	private Document getDoc(RequestFields reqFields) throws IOException {
		 return Jsoup.connect(
				String.format(API_URL, reqFields.getLimit(),
						reqFields.getStation(), reqFields.getYear(),
						reqFields.getMonth(), reqFields.getDay(),
						reqFields.getHour(), reqFields.getMinute())).get();
	}

	private RequestFields addTimeRequestFields(RequestFields reqFields) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
		calendar.setTime(date);

		reqFields.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		reqFields.setYear(calendar.get(Calendar.YEAR));
		reqFields.setMonth(calendar.get(Calendar.MONTH));
		reqFields.setMinute(calendar.get(Calendar.MINUTE));
		reqFields.setHour(calendar.get(Calendar.HOUR_OF_DAY));

		return reqFields;
	}
}
