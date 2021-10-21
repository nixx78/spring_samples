package lv.nixx.poc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppRunner extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppRunner.class);
	}

	//TODO https://spring.io/guides/gs/securing-web/
	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);
	}

}
