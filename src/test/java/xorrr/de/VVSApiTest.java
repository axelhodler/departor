package xorrr.de;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.departure.DepartureInfo;
import xorrr.de.mocks.VVSApiConnectorMock;
import xorrr.de.vvs.XPathDepartureFinder;

public class VVSApiTest {

	private XPathDepartureFinder finder;
	private VVSApiConnectorMock connector;

	@Before
	public void setUp() {
		connector = new VVSApiConnectorMock();
		finder = new XPathDepartureFinder();
	}

	@Test
	public void canGetDepartureInfos() {
		List<DepartureInfo> infos = finder.getDepatureInfos(connector.getDocument());

		DepartureInfo firstInfo = infos.get(0);

		assertEquals(5, infos.size());

		assertEquals("42", firstInfo.getLine());
		assertEquals("Schlossplatz - Gablenberg - Hbf - Erwin-Schoettle-Platz", firstInfo.getRoute());
		assertEquals("23:29", firstInfo.getTime());
		assertEquals("Erwin-Schoettle-Platz", firstInfo.getDirection());
	}

}
