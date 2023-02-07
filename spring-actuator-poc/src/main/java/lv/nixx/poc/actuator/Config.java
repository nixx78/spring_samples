package lv.nixx.poc.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

	@Bean
	public HealthIndicator serviceHealthIndicator(ServiceForMonitoring service) {
		return () -> Health.status(service.getStatus())
				.withDetail("detail", service.getDetails())
				.withDetail("message", service.getMessage())
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
