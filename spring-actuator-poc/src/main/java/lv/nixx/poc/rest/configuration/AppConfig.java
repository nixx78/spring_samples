package lv.nixx.poc.rest.configuration;

import lv.nixx.poc.rest.service.ApplicationComponent;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

	@Bean
	public HealthIndicator comp1HealthIndicator(ApplicationComponent applicationComponent) {
		return () -> Health.status(Status.UP)
				.withDetail("detail1", "text1")
				.withDetail("message", applicationComponent.getStatusMessage())
				.build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource.alpha")
	public DataSource alphaDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public HealthIndicator alphaDsHealthIndicator(DataSource alphaDataSource) {
		return new DataSourceHealthIndicator(alphaDataSource, "select now()");
	}


}
