package xorrr.de.mocks;

import org.w3c.dom.Document;

import xorrr.de.api.ApiConnector;
import xorrr.de.api.RequestFields;

public class VVSConnectorMock implements ApiConnector {

	public boolean documentReturned;

	@Override
	public Document getDocument(RequestFields reqFields) {
		documentReturned = true;
		return null;
	}

}
