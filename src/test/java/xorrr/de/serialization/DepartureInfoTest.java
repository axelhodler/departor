package xorrr.de.serialization;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;

import org.junit.Test;

import xorrr.de.model.DepartureInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DepartureInfoTest {

	private static final String JSON_FIXTURE = "fixtures/departureinfo.json";
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final DepartureInfo info = createSampleDepartureInfo();

		assertThat(MAPPER.writeValueAsString(info)).isEqualTo(
				MAPPER.writeValueAsString(MAPPER.readValue(
						fixture(JSON_FIXTURE), JsonNode.class)));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final DepartureInfo info = createSampleDepartureInfo();

		assertThat(MAPPER.readValue(fixture(JSON_FIXTURE), DepartureInfo.class))
				.isEqualTo(info);
	}

	private DepartureInfo createSampleDepartureInfo() {
		final DepartureInfo info = new DepartureInfo();
		info.setLine("U2");
		info.setDirection("Botnang");
		info.setTime("21:16");
		info.setRoute("Neugereut - Charlottenplatz - Vogelsang - Botnang");
		return info;
	}
}
