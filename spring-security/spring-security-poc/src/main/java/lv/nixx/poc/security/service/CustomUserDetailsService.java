package lv.nixx.poc.security.service;

import lv.nixx.poc.security.model.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, CustomUser> users = Map.of(
            "admin", new CustomUser("admin", "1", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")), List.of(View1, View2)),
            "user", new CustomUser("user", "1", List.of(new SimpleGrantedAuthority("ROLE_USER")), List.of(ViewX))
    );


    @Override
    public UserDetails loadUserByUsername(String s) {
        return Optional.ofNullable(users.get(s)).map(c -> {
            c.setLoginTime(new Date().toString());
            return c;
        }).orElseThrow(() -> new UsernameNotFoundException("User:" + s + " not exists"));
    }

}
