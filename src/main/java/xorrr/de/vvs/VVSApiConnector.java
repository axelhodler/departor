package xorrr.de.vvs;

import java.io.IOException;
import java.io.StringReader;

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

	private final String apiUrl = "http://www2.vvs.de/vvs/widget/XML_DM_REQUEST"
			+ "?zocationServerActive=1&lsShowTrainsExplicit=1&stateless=1&language=de"
			+ "&SpEncId=0&anySigWhenPerfectNoOtherMatches=1&limit=5&depArr=departure"
			+ "&type_dm=any&anyObjFilter_dm=2&deleteAssignedStops=1&name_dm=5006206"
			+ "&mode=direct&dmLineSelectionAll=1&itdDateYear=2014&itdDateMonth=09"
			+ "&itdDateDay=02&itdTimeHour=23&itdTimeMinute=26&useRealtime=1";

	public Document getDocument() {
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(apiUrl);
		Document doc = null;
		try {
			doc = createDocument(httpclient, getRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return doc;
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
