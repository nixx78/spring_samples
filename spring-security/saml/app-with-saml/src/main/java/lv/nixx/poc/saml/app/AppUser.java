package lv.nixx.poc.saml.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AppUser {

    @Getter
    private final String id;
    private final Collection<GrantedAuthority> roles;

    public Collection<String> getRoles() {
        return roles.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

}
