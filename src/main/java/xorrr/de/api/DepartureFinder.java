package xorrr.de.api;

import java.util.List;

import xorrr.de.model.DepartureInfo;
import xorrr.de.util.TimeFormatter;

public interface DepartureFinder {
	public List<DepartureInfo> getDepatureInfos(RequestFields reqFields, TimeFormatter tf);
}
