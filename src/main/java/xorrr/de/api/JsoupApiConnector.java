package xorrr.de.api;

import org.jsoup.nodes.Document;

public interface JsoupApiConnector {
	public Document getDocument(RequestFields reqFields);
}
