package lv.nixx.poc.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Accessors(chain = true)
public class CustomUser extends User {

    @Setter
    private String loginTime;
    private final Collection<ViewName> availableViews;

    public CustomUser(String username, String password, Collection<String> roles, ViewName... availableViews) {
        super(username, password, roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));

        this.availableViews = Stream.of(availableViews).collect(Collectors.toSet());
    }


    public static CustomUser empty() {
        return new CustomUser("unknown", "none", Collections.emptyList());
    }

    public boolean isAdmin() {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(t-> t.equalsIgnoreCase("ROLE_ADMIN"));
    }

}
