package xorrr.de.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import xorrr.de.departure.DepartureInfo;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

@Path("/departures")
@Produces(MediaType.APPLICATION_JSON)
public interface DepartureResource {

	@GET
	@Timed
	public Map<String, List<DepartureInfo>> tellDepartureInfos(
			@QueryParam("station") Optional<Integer> stationId,
			@QueryParam("limit") Optional<Integer> limit);
}
