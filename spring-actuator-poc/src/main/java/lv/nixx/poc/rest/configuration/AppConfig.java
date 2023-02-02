package lv.nixx.poc.rest.configuration;

import lv.nixx.poc.rest.service.ApplicationComponent;
import org.apache.derby.jdbc.ClientDataSource;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
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
	public DataSource derbyDataSource(){
		ClientDataSource ds = new ClientDataSource();
		ds.setServerName ("localhost");
		ds.setPortNumber(1527);
		ds.setDatabaseName ("derbyDB");
		ds.setUser("admin");
		ds.setPassword("admin");
		return ds;
	}

    @Bean
    public DataSource derbyDataSource1(){
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName ("localhost");
        ds.setPortNumber(1527);
        ds.setDatabaseName ("derbyDB");
        ds.setUser("admin");
        ds.setPassword("admin");
        return ds;
    }

	@Bean
	public HealthIndicator dbName1HealthIndicator() {
	    // Custom DB checker
		return new DataSourceHealthIndicator(derbyDataSource(), "select current_timestamp from sysibm.sysdummy1");
	}


}
