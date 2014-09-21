package xorrr.de.resources;

import javax.ws.rs.core.Response;

import com.google.common.base.Optional;

public interface DepartureResource {

	public Response tellDepartureInfos(
			Optional<Integer> stationId, Optional<Integer> limit);
}
