package xorrr.de.api;

import org.w3c.dom.Document;

public interface ApiConnector {
	public Document getDocument(RequestFields reqFields);
}
