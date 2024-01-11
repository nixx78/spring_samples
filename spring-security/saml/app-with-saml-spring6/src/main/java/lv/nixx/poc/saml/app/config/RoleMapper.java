package lv.nixx.poc.saml.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app.roles")
@Setter
@Getter
public class RoleMapper {

    private Map<String, String> mapping;

    public GrantedAuthority mapRole(String t) {
        return new SimpleGrantedAuthority(mapping.getOrDefault(t, "ROLE_UNKNOWN"));
    }

}
