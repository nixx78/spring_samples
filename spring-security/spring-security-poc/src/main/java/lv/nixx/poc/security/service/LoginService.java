package lv.nixx.poc.security.service;

import lv.nixx.poc.security.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public CustomUser getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth == null ? CustomUser.empty() : (CustomUser) auth.getPrincipal();
    }

    public String getUserName() {
        return getLoggedInUser().getUsername();
    }

    public boolean isViewIsAllowed(String viewName) {
        return getLoggedInUser().getAvailableViews().contains(viewName);
    }
}
