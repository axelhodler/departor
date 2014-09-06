package xorrr.de;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xorrr.de.vvs.DepartureFinder;
import xorrr.de.vvs.DepartureInfo;
import xorrr.de.vvs.VVSApiConnector;

import com.codahale.metrics.annotation.Timed;

@Path("/departures")
@Produces(MediaType.APPLICATION_JSON)
public class DepartureResource {

	private DepartureFinder f;

	public DepartureResource(String defaultStation) {
		VVSApiConnector con = new VVSApiConnector();
		f = new DepartureFinder(con.getDocument());
	}

	@GET
	@Timed
	public Map<String, List<DepartureInfo>> tellDepartureInfos() {
		Map<String, List<DepartureInfo>> departures = new HashMap<>();
		departures.put("departures", f.getDepatureInfos());
		
		return departures;
	}
}
