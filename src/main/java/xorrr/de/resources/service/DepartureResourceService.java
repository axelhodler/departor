package xorrr.de.resources.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import xorrr.de.Config;
import xorrr.de.api.DepartureFinder;
import xorrr.de.api.RequestFields;
import xorrr.de.departure.DepartureInfo;
import xorrr.de.resources.DepartureResource;
import xorrr.de.util.StringTimeFormatter;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/departures")
@Api(value = "/departures", description = "Get departures")
@Produces(MediaType.APPLICATION_JSON)
public class DepartureResourceService implements DepartureResource {

	private DepartureFinder finder;
	private Config conf;

	public DepartureResourceService(Config conf, DepartureFinder f) {
		this.conf = conf;
		this.finder = f;
	}

	@Override
	@GET
	@Timed
	@ApiOperation(value = "Find pet by ID",
		notes = "More notes about this method", response = DepartureInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid station ID supplied"),
			@ApiResponse(code = 404, message = "departures not found") })
	public Map<String, List<DepartureInfo>> tellDepartureInfos(
			@ApiParam(value = "station id to fetch", required = false) @QueryParam("station") Optional<Integer> stationId,
			@ApiParam(value = "amount of departures to fetch", required = false) @QueryParam("limit") Optional<Integer> limit) {

		RequestFields reqFields = new RequestFields();
		reqFields.setStation(stationId.or(conf.getStationId()));
		reqFields.setLimit(limit.or(5));

		Map<String, List<DepartureInfo>> departures = new HashMap<>();
		departures.put("departures", finder.getDepatureInfos(reqFields,
				new StringTimeFormatter()));

		return departures;
	}
}
