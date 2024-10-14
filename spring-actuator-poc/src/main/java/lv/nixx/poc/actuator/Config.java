package lv.nixx.poc.actuator;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class Config {

	private final static Logger log = LoggerFactory.getLogger(Config.class);

	@Bean
	public HealthIndicator serviceHealthIndicator(ServiceForMonitoring service) {
		return () -> Health.status(service.getStatus())
				.withDetail("detail", service.getDetails())
				.withDetail("message", service.getMessage())
				.build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.alpha")  // Настройки для основного DataSource
	public DataSourceProperties alphaDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.alpha.hikari")  // Настройки для HikariCP
	public DataSource alphaDataSource(DataSourceProperties alphaDataSourceProperties) {
        return alphaDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
	}


	@Bean
	@ConfigurationProperties("spring.datasource.beta")  // Настройки для основного DataSource
	public DataSourceProperties betaDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.beta.hikari")  // Настройки для HikariCP
	public DataSource betaDataSource(DataSourceProperties betaDataSourceProperties) {
		return betaDataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}


	@Bean
	public HealthIndicator alphaDsHealthIndicator(DataSource alphaDataSource) {
		return new DataSourceHealthIndicator(alphaDataSource, "select now()");
	}


	@Bean
	public ApplicationRunner validateDataSource(DataSource alphaDataSource, DataSource betaDataSource) {
		return args -> {
			try (Connection connection = alphaDataSource.getConnection()) {
			} catch (Exception ex) {
				log.error("Problem with connection to AlphaDataSource", ex);
			}
		};
	}

}
