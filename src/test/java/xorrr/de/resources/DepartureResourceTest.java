package xorrr.de.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.Config;
import xorrr.de.departure.DepartureInfo;
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
		Map<String, List<DepartureInfo>> departureInfos = res.tellDepartureInfos(Optional.of(5), Optional.of(5));

		assertTrue(finderMock.correctRequestFieldsUsed);
		assertTrue(finderMock.gotDepartureInfos);
		assertNotNull(departureInfos);
	}
}
