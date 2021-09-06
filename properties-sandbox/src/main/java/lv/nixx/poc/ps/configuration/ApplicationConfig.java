package lv.nixx.poc.ps.configuration;

import lv.nixx.poc.ps.PropertyWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ApplicationConfig {

    @Value("${property.for.app.config}")
    private String propertyFromAppConfig;

    @Value("${property.injected.as.bean}")
    private String propertyInjectAsBean;

    public String getPropertyFromAppConfig() {
        return propertyFromAppConfig;
    }

    @Bean
    @RefreshScope
    public PropertyWrapper getPropertyInjectAsBean() {
        return new PropertyWrapper(propertyInjectAsBean);
    }
}
