package xorrr.de.vvs;

import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class VVSApiConnector implements ApiConnector {

	private RequestFields reqFields;
	private final String apiUrl = "http://www2.vvs.de/vvs/widget/XML_DM_REQUEST"
			+ "?zocationServerActive=1&lsShowTrainsExplicit=1&stateless=1&language=de"
			+ "&SpEncId=0&anySigWhenPerfectNoOtherMatches=1"
			+ "&limit=%s"
			+ "&depArr=departure"
			+ "&type_dm=any&anyObjFilter_dm=2&deleteAssignedStops=1"
			+ "&name_dm=%s"
			+ "&mode=direct&dmLineSelectionAll=1&"
			+ "itdDateYear=%s"
			+ "&itdDateMonth=%s"
			+ "&itdDateDay=%s"
			+ "&itdTimeHour=%s"
			+ "&itdTimeMinute=%s"
			+ "&useRealtime=1";


	public VVSApiConnector(RequestFields reqFields) {
		this.reqFields = reqFields;
	}

	public Document getDocument() {
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(fillRequest(apiUrl));
		Document doc = null;
		try {
			doc = createDocument(httpclient, getRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	private String fillRequest(String apiUrl) {
		addTimeRequestFields();

		String formattedUrl = String.format(apiUrl,
				reqFields.getLimit(),
				reqFields.getStation(),
				reqFields.getYear(),
				reqFields.getMonth(),
				reqFields.getDay(),
				reqFields.getHour(),
				reqFields.getMinute());

		return formattedUrl;
	}

	private RequestFields addTimeRequestFields() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Berlin"));
		calendar.setTime(date);

		reqFields.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		reqFields.setYear(calendar.get(Calendar.YEAR));
		reqFields.setMonth(calendar.get(Calendar.MONTH));
		reqFields.setMinute(calendar.get(Calendar.MINUTE));
		reqFields.setHour(calendar.get(Calendar.HOUR));

		return reqFields;
	}

	private Document createDocument(HttpClient httpclient, HttpGet getRequest)
			throws IOException, ClientProtocolException,
			ParserConfigurationException, SAXException {
		HttpResponse resp = httpclient.execute(getRequest);
		String content = EntityUtils.toString(resp.getEntity());
		return buildDocument(content);
	}

	private Document buildDocument(String content)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(content));
		return builder.parse(is);
	}
}
