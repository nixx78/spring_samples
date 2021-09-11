package lv.nixx.poc.ps.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Map;

@Configuration
@ConfigurationProperties
@PropertySource(value = "file:${external.properties.location}/settings.yml", factory = YamlPropertySourceFactory.class)
@Setter
@Getter
@Validated
public class YmlSettings {

    private String name;

    private Collection<String> aliases;

    private Collection<String> sequence;

    private CacheConfig accountCache;

    private Collection<CacheConfig> caches;

    private Map<String, UserRole> roles;

    private Map<Integer, String> map;

}
