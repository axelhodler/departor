package xorrr.de;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VVSDepartureConfiguration extends Configuration {

	@NotEmpty
	private String station;

	@JsonProperty
	public String getStation() {
		return station;
	}

	@JsonProperty
	public void setStation(String station) {
		this.station = station;
	}

}
