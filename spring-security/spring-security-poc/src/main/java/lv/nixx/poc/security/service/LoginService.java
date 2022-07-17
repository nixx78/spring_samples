package lv.nixx.poc.security.service;

import lv.nixx.poc.security.model.CustomUser;
import lv.nixx.poc.security.model.ViewName;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public CustomUser getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth == null || auth.getPrincipal().equals("anonymousUser") ? CustomUser.empty() : (CustomUser) auth.getPrincipal();
    }

    public String getUserName() {
        return getLoggedInUser().getUsername();
    }

    public boolean isViewIsNotAllowed(ViewName viewName) {
        return !getLoggedInUser().getAvailableViews().contains(viewName);
    }

}
