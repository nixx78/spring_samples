package lv.nixx.poc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//TODO Continue to execute original request
//TODO rule based on parameter value -> https://stackoverflow.com/questions/53726109/spring-boot-ant-matchers-parameters

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
