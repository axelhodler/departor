package xorrr.de;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import xorrr.de.api.vvs.VVSApiConnector;
import xorrr.de.api.vvs.XPathDepartureFinder;
import xorrr.de.resources.DepartureResourceService;

public class DepartureApplication extends Application<VVSDepartureConfiguration> {
	public static void main(String[] args) throws Exception {
		new DepartureApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<VVSDepartureConfiguration> bootstrap) {
	}

	@Override
	public void run(VVSDepartureConfiguration conf, Environment env)
			throws Exception {
		XPathDepartureFinder finder = new XPathDepartureFinder(new VVSApiConnector());
		final DepartureResourceService resource = new DepartureResourceService(
				conf.getStationId(), finder
		);

		env.jersey().register(resource);
	}

}
