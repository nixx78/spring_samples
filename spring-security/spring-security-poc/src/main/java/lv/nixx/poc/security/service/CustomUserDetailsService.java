package lv.nixx.poc.security.service;

import lv.nixx.poc.security.model.CustomUser;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static lv.nixx.poc.security.model.ViewName.*;

@Service
@Profile("prod")
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, CustomUser> users = Map.of(
            "admin", new CustomUser("admin", "1", List.of("ROLE_ADMIN"), View1, View2),
            "simple_user", new CustomUser("simple_user", "1", List.of("ROLE_SIMPLE_USER"), ViewX),
            "power_user", new CustomUser("power_user", "1", List.of("ROLE_ADMIN", "ROLE_POWER"), ViewX)
    );


    @Override
    public UserDetails loadUserByUsername(String s) {
        return Optional.ofNullable(users.get(s)).map(c -> {
            c.setLoginTime(new Date().toString());
            return c;
        }).orElseThrow(() -> new UsernameNotFoundException("User:" + s + " not exists"));
    }

}
