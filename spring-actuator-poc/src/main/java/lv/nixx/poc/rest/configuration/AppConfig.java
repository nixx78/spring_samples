package lv.nixx.poc.rest.configuration;

import lv.nixx.poc.rest.service.Component1;
import org.apache.derby.jdbc.ClientDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

	private Component1 component1;

	@Autowired
	public void setComponent1(Component1 component1) {
		this.component1 = component1;
	}

	@Bean
	public HealthIndicator comp1HealthIndicator() {

		return () -> Health.status(Status.UP)
				.withDetail("detail1", "text1")
				.withDetail("message", component1.getStatusMessage())
				.build();
	}

	@Bean
	public DataSource derbyDataSource(){
		ClientDataSource ds = new ClientDataSource();
		ds.setDataSourceName("DS_NAME");
		ds.setDatabaseName("DB_NAME");
		ds.setServerName ("localhost");
		ds.setPortNumber(1527);
		ds.setDatabaseName ("derbyDB");
		ds.setUser("admin");
		ds.setPassword("admin");
		return ds;
	}

	@Bean
	public HealthIndicator dbName1HealthIndicator() {
		return new DataSourceHealthIndicator(derbyDataSource(), "select current_timestamp from sysibm.sysdummy1");
	}


}
