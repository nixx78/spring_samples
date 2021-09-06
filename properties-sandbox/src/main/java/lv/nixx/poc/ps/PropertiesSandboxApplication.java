package lv.nixx.poc.ps;

import lv.nixx.poc.ps.configuration.ConfigurationPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationPropertiesConfig.class)
public class PropertiesSandboxApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		PropertyLoader.loadProperties();
		return application.sources(PropertiesSandboxApplication.class);
	}

	public static void main(String[] args) {
		PropertyLoader.loadProperties();
		SpringApplication.run(PropertiesSandboxApplication.class, args);
	}

}
