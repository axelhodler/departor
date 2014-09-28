package xorrr.de.mocks;

import java.util.ArrayList;
import java.util.List;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.model.DepartureInfo;
import xorrr.de.util.TimeFormatter;

public class DepartureFinderMock implements DepartureFinder {

	public boolean gotDepartureInfos;
	public boolean correctRequestFieldsUsed;

	@Override
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields, TimeFormatter tf) {
		if (reqFields.getLimit() == 5 && reqFields.getStation() == 5) {
			correctRequestFieldsUsed = true;
		}
		gotDepartureInfos = true;
		return new ArrayList<>();
	}

}
