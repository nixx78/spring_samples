package lv.nixx.poc.saml.app;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public AppUser getLoggedUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
