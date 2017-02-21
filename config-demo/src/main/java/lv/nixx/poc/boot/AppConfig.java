package lv.nixx.poc.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Value("${connection}")
	String connection;
	
	@Value("${server.port}")
	int port;
	
	@Value("${server.name}")
	String name;
	
	@Bean
	public String getProperty() {
		System.out.println(name + ":" + connection + ":" + port);
		return connection;
	}

}
