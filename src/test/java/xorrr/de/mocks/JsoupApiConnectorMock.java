package xorrr.de.mocks;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import xorrr.de.api.JsoupApiConnector;
import xorrr.de.api.RequestFields;

public class JsoupApiConnectorMock implements JsoupApiConnector{

	public boolean gotDocument;

	@Override
	public Document getDocument(RequestFields reqFields) {
		gotDocument = true;
		ClassLoader classloader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("response.xml");

		Document doc = null;
		try {
			doc = Jsoup.parse(is, "UTF-8", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

}
