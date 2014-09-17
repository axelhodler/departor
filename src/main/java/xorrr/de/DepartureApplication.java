package xorrr.de;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import xorrr.de.api.vvs.JsoupVVSApiConnector;
import xorrr.de.resources.service.DepartureResourceService;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

public class DepartureApplication extends
		Application<VVSDepartureConfiguration> {
	public static void main(String[] args) throws Exception {
		new DepartureApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<VVSDepartureConfiguration> bootstrap) {
	}

	@Override
	public void run(VVSDepartureConfiguration conf, Environment env)
			throws Exception {
		JsoupDepartureFinder finder = new JsoupDepartureFinder(
				new JsoupVVSApiConnector());
		final DepartureResourceService resource = new DepartureResourceService(
				conf, finder);

		env.jersey().register(resource);
		// Swagger Resource
		env.jersey().register(new ApiListingResourceJSON());

		// Swagger providers
		env.jersey().register(new ApiDeclarationProvider());
		env.jersey().register(new ResourceListingProvider());

		// Swagger Scanner, which finds all the resources for @Api Annotations
		ScannerFactory.setScanner(new DefaultJaxrsScanner());

		// Add the reader, which scans the resources and extracts the resource
		// information
		ClassReaders.setReader(new DefaultJaxrsApiReader());

		// Set the swagger config options
		SwaggerConfig config = ConfigFactory.config();
		config.setApiVersion("1.0.1");
		config.setBasePath("http://localhost:8080/");
	}

}
