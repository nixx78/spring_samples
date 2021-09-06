package lv.nixx.poc.ps.service;

import lv.nixx.poc.ps.PropertyWrapper;
import lv.nixx.poc.ps.configuration.ApplicationConfig;
import lv.nixx.poc.ps.configuration.ConfigurationPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class ServiceWithJavaConfiguration {

    private final ApplicationConfig applicationConfig;
    private final ConfigurationPropertiesConfig configurationPropertiesConfig;
    private final String propertyAsBean;

    @Autowired
    public ServiceWithJavaConfiguration(ApplicationConfig applicationConfig,
                                        ConfigurationPropertiesConfig configurationPropertiesConfig,
                                        PropertyWrapper propertyInjectAsBean
    ) {
        this.applicationConfig = applicationConfig;
        this.configurationPropertiesConfig = configurationPropertiesConfig;
        this.propertyAsBean = propertyInjectAsBean.getProperty();
    }

    public String process() {
        return "Property from 'ApplicationConfig' - " + applicationConfig.getPropertyFromAppConfig() +
                " customApplicationProperty - " + configurationPropertiesConfig.getCustomApplicationProperty() +
                " propertyAsBean - " + propertyAsBean;
    }

}
