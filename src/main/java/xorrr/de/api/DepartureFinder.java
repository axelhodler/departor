package xorrr.de.api;

import java.util.List;

import xorrr.de.model.DepartureInfo;

public interface DepartureFinder {
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields);
}
