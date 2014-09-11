package xorrr.de;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.api.RequestFields;
import xorrr.de.api.vvs.XPathDepartureFinder;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.mocks.VVSFileConnectorMock;

public class VVSApiTest {

	private XPathDepartureFinder finder;
	private VVSFileConnectorMock connector;

	@Before
	public void setUp() {
		connector = new VVSFileConnectorMock();
		finder = new XPathDepartureFinder(connector);
	}

	@Test
	public void canGetDepartureInfos() {
		List<DepartureInfo> infos = finder.getDepatureInfos(new RequestFields());

		DepartureInfo firstInfo = infos.get(0);

		assertEquals(5, infos.size());

		assertEquals("42", firstInfo.getLine());
		assertEquals("Schlossplatz - Gablenberg - Hbf - Erwin-Schoettle-Platz", firstInfo.getRoute());
		assertEquals("23:29", firstInfo.getTime());
		assertEquals("Erwin-Schoettle-Platz", firstInfo.getDirection());
	}

}
