package lv.nixx.poc.security.service;

import lv.nixx.poc.security.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, CustomUser> users;

    public CustomUserDetailsService() {
        users = Map.of(
                "admin", new CustomUser("admin", "1", List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )),
                "user", new CustomUser("user", "1", List.of(
                        new SimpleGrantedAuthority("ROLE_USER")
                ))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return Optional.ofNullable(users.get(s)).map(c -> {
            c.setLoginTime(new Date().toString());
            return c;
        }).orElseThrow(() -> new UsernameNotFoundException("User:" + s + " not exists"));
    }

}
