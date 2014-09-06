package xorrr.de;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DepartureApplication extends Application<VVSDepartureConfiguration> {
	public static void main(String[] args) throws Exception {
		new DepartureApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<VVSDepartureConfiguration> bootstrap) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run(VVSDepartureConfiguration conf, Environment env)
			throws Exception {
		final DepartureResource resource = new DepartureResource(
				conf.getStation()
		);

		env.jersey().register(resource);
	}

}
