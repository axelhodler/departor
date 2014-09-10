package xorrr.de.mocks;

import java.util.List;

import org.w3c.dom.Document;

import xorrr.de.departure.DepartureFinder;
import xorrr.de.departure.DepartureInfo;

public class DepartureFinderMock implements DepartureFinder {

	@Override
	public List<DepartureInfo> getDepatureInfos(Document doc) {
		return null;
	}

}
