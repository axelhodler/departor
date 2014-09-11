package xorrr.de.resources;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.mocks.DepartureFinderMock;

import com.google.common.base.Optional;

public class DepartureResourceTest {

	private DepartureFinderMock finderMock;
	private DepartureResource res;

	@Before
	public void setUp() {
		finderMock = new DepartureFinderMock();
		res = new DepartureResourceService(52, finderMock);
	}

	@Test
	public void canTellDepartureInfos() {
		res.tellDepartureInfos(Optional.of(5), Optional.of(5));

		//assertTrue(finderMock.getDepatureInfos(doc))
	}
}
