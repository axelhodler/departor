package xorrr.de;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import xorrr.de.api.vvs.JsoupVVSApiConnector;
import xorrr.de.resources.service.DepartureResourceService;

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
		JsoupDepartureFinder finder = new JsoupDepartureFinder(new JsoupVVSApiConnector());
		final DepartureResourceService resource = new DepartureResourceService(conf, finder);

		env.jersey().register(resource);
	}

}
