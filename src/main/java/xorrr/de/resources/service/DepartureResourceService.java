package xorrr.de.resources.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xorrr.de.Config;
import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.resources.DepartureResource;
import xorrr.de.util.StringTimeFormatter;

import com.google.common.base.Optional;


public class DepartureResourceService implements DepartureResource{

	private DepartureFinder finder;
	private Config conf;

	public DepartureResourceService(Config conf, DepartureFinder f) {
		this.conf = conf;
		this.finder = f;
	}

	@Override
	public Map<String, List<DepartureInfo>> tellDepartureInfos(Optional<Integer> stationId,
			Optional<Integer> limit) {
		RequestFields reqFields = new RequestFields();
		reqFields.setStation(stationId.or(conf.getStationId()));
		reqFields.setLimit(limit.or(5));

		Map<String, List<DepartureInfo>> departures = new HashMap<>();
		departures.put("departures", finder.getDepatureInfos(reqFields, new StringTimeFormatter()));

		return departures;
	}
}
