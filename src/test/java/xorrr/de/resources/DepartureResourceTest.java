package xorrr.de.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.Config;
import xorrr.de.mocks.ConfigurationMock;
import xorrr.de.mocks.DepartureFinderMock;
import xorrr.de.resources.service.DepartureResourceService;

import com.google.common.base.Optional;

public class DepartureResourceTest {

	private DepartureFinderMock finderMock;
	private Config configMock;
	private DepartureResource res;

	@Before
	public void setUp() {
		finderMock = new DepartureFinderMock();
		configMock = new ConfigurationMock();
		res = new DepartureResourceService(configMock, finderMock);
	}

	@Test
	public void canTellDepartureInfos() {
		Response departureInfos = res.tellDepartureInfos(Optional.of(5),
				Optional.of(5));

		assertTrue(finderMock.correctRequestFieldsUsed);
		assertTrue(finderMock.gotDepartureInfos);
		assertNotNull(departureInfos);
	}

	@Test
	public void accessControlAllowOriginHeaderUsed() {
		Response departureInfos = res.tellDepartureInfos(Optional.of(5),
				Optional.of(5));

		assertEquals("*", getACAOheader(departureInfos));
	}

	private Object getACAOheader(Response departureInfos) {
		return departureInfos.getMetadata().get("Access-Control-Allow-Origin")
				.get(0);
	}
}
