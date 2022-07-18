package lv.nixx.poc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//TODO Create Login and Login controller test
//TODO Continue to execute original request
@SpringBootApplication
public class AppRunner extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppRunner.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);
	}

}
