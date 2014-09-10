package xorrr.de.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xorrr.de.vvs.DepartureFinder;
import xorrr.de.vvs.DepartureInfo;
import xorrr.de.vvs.RequestFields;
import xorrr.de.vvs.VVSApiConnector;

import com.google.common.base.Optional;


public class DepartureResourceService implements DepartureResource{

	private VVSApiConnector con;
	private Integer defaultStation;

	public DepartureResourceService(Integer defaultStation) {
		this.defaultStation = defaultStation;
	}

	@Override
	public Map<String, List<DepartureInfo>> tellDepartureInfos(Optional<Integer> stationId,
			Optional<Integer> limit) {
		RequestFields reqFields = new RequestFields();
		reqFields.setStation(stationId.or(defaultStation));
		reqFields.setLimit(limit.or(5));

		con = new VVSApiConnector(reqFields);

		DepartureFinder f = new DepartureFinder(con.getDocument());

		Map<String, List<DepartureInfo>> departures = new HashMap<>();
		departures.put("departures", f.getDepatureInfos());

		return departures;
	}
}
