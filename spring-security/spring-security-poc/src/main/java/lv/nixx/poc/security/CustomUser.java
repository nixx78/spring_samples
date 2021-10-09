package lv.nixx.poc.security;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Accessors(chain = true)
public class CustomUser extends User {

    private String loginTime;
    private Collection<String> availableViews;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static CustomUser empty() {
        return new CustomUser("unknown", "none", Collections.emptyList());
    }

}
