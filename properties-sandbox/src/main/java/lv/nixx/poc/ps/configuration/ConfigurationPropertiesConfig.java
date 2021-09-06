package lv.nixx.poc.ps.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ConfigurationPropertiesConfig {

    public String customApplicationProperty;

    public void setCustomApplicationProperty(String customApplicationProperty) {
        this.customApplicationProperty = customApplicationProperty;
    }

    public String getCustomApplicationProperty() {
        return customApplicationProperty;
    }
}
