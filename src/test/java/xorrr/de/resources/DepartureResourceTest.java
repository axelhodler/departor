package xorrr.de.resources;

import org.junit.Before;
import org.junit.Test;

import xorrr.de.mocks.DepartureFinderMock;

public class DepartureResourceTest {

	private DepartureFinderMock finderMock;

	@Before
	public void setUp() {
		finderMock = new DepartureFinderMock();
		DepartureResource res = new DepartureResourceService(52, finderMock);
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
