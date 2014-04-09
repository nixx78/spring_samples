package lv.nixx.poc.spring.config;

import lv.nixx.poc.spring.ProfiledBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class AppConfigDev {
	
	@Bean
	public ProfiledBean getBean(){
		return new ProfiledBean("dev");
	}

}
