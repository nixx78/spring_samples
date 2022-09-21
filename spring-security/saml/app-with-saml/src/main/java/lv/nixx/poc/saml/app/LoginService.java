package lv.nixx.poc.saml.app;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoginService {

    public AppUser getLoggedUser() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof AppUser) {
            return (AppUser) details;
        } else {
            return new AppUser("unknown", "unknown", Collections.emptyList());
        }
    }

}
