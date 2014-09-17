package xorrr.de.resources;

import java.util.List;
import java.util.Map;

import xorrr.de.departure.DepartureInfo;

import com.google.common.base.Optional;

public interface DepartureResource {

	public Map<String, List<DepartureInfo>> tellDepartureInfos(
			Optional<Integer> stationId, Optional<Integer> limit);
}
