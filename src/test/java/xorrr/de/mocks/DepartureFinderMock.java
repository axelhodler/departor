package xorrr.de.mocks;

import java.util.List;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.departure.DepartureInfo;

public class DepartureFinderMock implements DepartureFinder {

	@Override
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields) {
		return null;
	}

}
