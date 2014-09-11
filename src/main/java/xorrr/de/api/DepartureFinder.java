package xorrr.de.api;

import java.util.List;

import xorrr.de.departure.DepartureInfo;

public interface DepartureFinder {
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields);
}
