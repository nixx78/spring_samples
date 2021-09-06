package lv.nixx.poc.ps.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class ServiceWithValueProperties {

    @Value("${property.from.value}")
    private String propertyFromValue;

    public String process() {
        return "Property from @Value  - " + propertyFromValue;
    }


}
