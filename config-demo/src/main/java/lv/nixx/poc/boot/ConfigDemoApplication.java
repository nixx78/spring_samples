package lv.nixx.poc.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigDemoApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "prod");
		SpringApplication.run(ConfigDemoApplication.class, args);
	}
}
