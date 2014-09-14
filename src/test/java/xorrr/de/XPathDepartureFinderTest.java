package xorrr.de;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.api.RequestFields;
import xorrr.de.api.vvs.XPathDepartureFinder;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.mocks.TimeFormatterMock;
import xorrr.de.mocks.VVSFileConnectorMock;

public class XPathDepartureFinderTest {

	private XPathDepartureFinder finder;
	private TimeFormatterMock timeFormatter;

	@Before
	public void setUp() {
		finder = new XPathDepartureFinder(new VVSFileConnectorMock());
		timeFormatter = new TimeFormatterMock();
	}

	@Test
	public void canGetDepartureInfos() {
		List<DepartureInfo> infos = finder.getDepatureInfos(new RequestFields(), timeFormatter);

		DepartureInfo firstInfo = infos.get(0);

		assertTrue(timeFormatter.minuteFormatted);

		assertEquals(5, infos.size());

		assertEquals("42", firstInfo.getLine());
		assertEquals("Schlossplatz - Gablenberg - Hbf - Erwin-Schoettle-Platz", firstInfo.getRoute());
		assertEquals("23:29", firstInfo.getTime());
		assertEquals("Erwin-Schoettle-Platz", firstInfo.getDirection());
	}

}
