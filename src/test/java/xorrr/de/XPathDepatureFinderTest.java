package xorrr.de;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import xorrr.de.api.RequestFields;
import xorrr.de.api.vvs.XPathDepartureFinder;
import xorrr.de.mocks.VVSConnectorMock;

public class XPathDepatureFinderTest {

	VVSConnectorMock connectorMock;

	@Before
	public void setUp() {
		this.connectorMock = new VVSConnectorMock();
	}

	@Ignore
	@Test
	public void test() {
		XPathDepartureFinder finder = new XPathDepartureFinder(connectorMock);

		finder.getDepatureInfos(new RequestFields());

		assertTrue(connectorMock.documentReturned);
	}

}
