package xorrr.de.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.departure.DepartureInfo;

import com.google.common.base.Optional;


public class DepartureResourceService implements DepartureResource{

	private Integer defaultStation;
	private DepartureFinder finder;

	public DepartureResourceService(Integer defaultStation, DepartureFinder f) {
		this.finder = f;
		this.defaultStation = defaultStation;
	}

	@Override
	public Map<String, List<DepartureInfo>> tellDepartureInfos(Optional<Integer> stationId,
			Optional<Integer> limit) {
		RequestFields reqFields = new RequestFields();
		reqFields.setStation(stationId.or(defaultStation));
		reqFields.setLimit(limit.or(5));

		Map<String, List<DepartureInfo>> departures = new HashMap<>();
		departures.put("departures", finder.getDepatureInfos(reqFields));

		return departures;
	}
}
