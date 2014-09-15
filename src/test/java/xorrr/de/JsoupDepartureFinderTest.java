package xorrr.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.mocks.JsoupApiConnectorMock;
import xorrr.de.mocks.TimeFormatterMock;

public class JsoupDepartureFinderTest {

	private JsoupApiConnectorMock con;

	@Before
	public void setUp() {
		con = new JsoupApiConnectorMock();
	}

	@Test
	public void canGetDepartureInfos() {
		DepartureFinder f = new JsoupDepartureFinder(con);

		List<DepartureInfo> departureInfos = f.getDepatureInfos(new RequestFields(), new TimeFormatterMock());

		DepartureInfo firstInfo = departureInfos.get(0);

		assertEquals(5, departureInfos.size());
		assertTrue(con.gotDocument);

		assertEquals("42", firstInfo.getLine());
		assertEquals("Schlossplatz - Gablenberg - Hbf - Erwin-Schoettle-Platz", firstInfo.getRoute());
		assertEquals("Erwin-Schoettle-Platz", firstInfo.getDirection());
	}

}
