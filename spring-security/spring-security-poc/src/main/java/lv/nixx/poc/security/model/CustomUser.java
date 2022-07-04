package lv.nixx.poc.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Accessors(chain = true)
public class CustomUser extends User {

    @Setter
    private String loginTime;
    private final Collection<ViewName> availableViews;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Collection<ViewName> availableViews) {
        super(username, password, authorities);
        this.availableViews = availableViews;
    }

    public static CustomUser empty() {
        return new CustomUser("unknown", "none", Collections.emptyList(), Collections.emptyList());
    }

    public boolean isAdmin() {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(t-> t.equalsIgnoreCase("ROLE_ADMIN"));
    }

}
