package lv.nixx.poc.saml.app;

import lv.nixx.poc.saml.app.config.CustomAuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoginService {

    public AppUser getLoggedUser() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (details instanceof CustomAuthenticationPrincipal) {
            return ((CustomAuthenticationPrincipal) details).getAppUser();
        } else {
            return new AppUser("unknown", "unknown", Collections.emptyList());
        }
    }

}
