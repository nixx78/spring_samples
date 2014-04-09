package lv.nixx.poc.spring.config;

import lv.nixx.poc.spring.MainBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAppConfig {
	
	@Bean
	public MainBean getMainBean(){
		return new MainBean();
	}

}
