package lv.nixx.poc.spring.config;

import lv.nixx.poc.spring.ProfiledBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class AppConfigTest {
	
	@Bean
	public ProfiledBean testProfiledBean(){
		return new ProfiledBean("test");
	}

}
