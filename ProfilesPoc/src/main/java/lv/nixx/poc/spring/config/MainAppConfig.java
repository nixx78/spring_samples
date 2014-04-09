package lv.nixx.poc.spring.config;

import lv.nixx.poc.spring.MainBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAppConfig {
	
	@Bean
	public MainBean mainBean(){
		return new MainBean();
	}
	
	@Bean(name="namedBean")
	public MainBean getNamedBean(){
		return new MainBean("namedBean");
	}

}
