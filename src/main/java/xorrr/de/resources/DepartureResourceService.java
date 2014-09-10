package xorrr.de.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xorrr.de.departure.DepartureFinder;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.vvs.RequestFields;
import xorrr.de.vvs.VVSApiConnector;

import com.google.common.base.Optional;


public class DepartureResourceService implements DepartureResource{

	private VVSApiConnector con;
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

		con = new VVSApiConnector(reqFields);

		Map<String, List<DepartureInfo>> departures = new HashMap<>();
		departures.put("departures", finder.getDepatureInfos(con.getDocument()));

		return departures;
	}
}
