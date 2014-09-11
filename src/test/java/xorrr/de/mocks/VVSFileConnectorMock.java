package xorrr.de.mocks;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import xorrr.de.api.ApiConnector;
import xorrr.de.api.RequestFields;

public class VVSFileConnectorMock implements ApiConnector {

	@Override
	public Document getDocument(RequestFields reqFields) {
		ClassLoader classloader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("response.xml");
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = builder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}
