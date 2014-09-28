package xorrr.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.mocks.JsoupApiConnectorMock;
import xorrr.de.mocks.TimeFormatterMock;
import xorrr.de.model.DepartureInfo;

public class JsoupDepartureFinderTest {

	private JsoupApiConnectorMock con;
	private TimeFormatterMock tfMock;

	@Before
	public void setUp() {
		con = new JsoupApiConnectorMock();
		tfMock = new TimeFormatterMock();
	}

	@Test
	public void canGetDepartureInfos() {
		DepartureFinder f = new JsoupDepartureFinder(con);

		List<DepartureInfo> departureInfos = f.getDepatureInfos(new RequestFields(), tfMock);

		DepartureInfo firstInfo = departureInfos.get(0);

		assertEquals(5, departureInfos.size());
		assertTrue(con.gotDocument);
		assertTrue(tfMock.minuteFormatted);

		assertEquals("42", firstInfo.getLine());
		assertEquals("Schlossplatz - Gablenberg - Hbf - Erwin-Schoettle-Platz", firstInfo.getRoute());
		assertEquals("Erwin-Schoettle-Platz", firstInfo.getDirection());
		assertEquals("23:29", firstInfo.getTime());
	}

}
