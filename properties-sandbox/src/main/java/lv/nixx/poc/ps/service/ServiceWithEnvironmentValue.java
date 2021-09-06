package lv.nixx.poc.ps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class ServiceWithEnvironmentValue {

    private final Environment environment;

    @Autowired
    public ServiceWithEnvironmentValue(Environment environment) {
        this.environment = environment;
    }

    public String process() {
        return "Property from 'Environment' - " + environment.getProperty("property.for.environment") +
                " property from JSON('json.property') - " + environment.getProperty("json.property");
    }
}
