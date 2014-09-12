package xorrr.de;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VVSDepartureConfiguration extends Configuration implements Config {

	private int stationId;

	@Override
	@JsonProperty
	public int getStationId() {
		return stationId;
	}

	@JsonProperty
	public void setStationId(int station) {
		this.stationId = station;
	}

}
