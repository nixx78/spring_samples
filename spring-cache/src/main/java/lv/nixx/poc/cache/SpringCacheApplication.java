package lv.nixx.poc.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheApplication {

	//TODO Review & Implement
	// https://docs.spring.io/spring-framework/docs/5.1.3.RELEASE/spring-framework-reference/integration.html#cache

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheApplication.class, args);
	}

}
