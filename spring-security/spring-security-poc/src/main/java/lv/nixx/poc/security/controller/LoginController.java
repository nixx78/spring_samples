package lv.nixx.poc.security.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.nixx.poc.security.model.CustomUser;
import lv.nixx.poc.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/userDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomUser getUserInfo() {
        return loginService.getLoggedInUser();
    }

    // Еще один способ как можно получить данные о конкретном пользователе
    @GetMapping(value = "/userInfo")
    public UserInfo getUserInfo(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        if (authentication == null) {
            return new UserInfo("unknown", Collections.emptyList());
        }

        CustomUser principal = (CustomUser) authentication.getPrincipal();

        // роли также можно взять из principal
        Set<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return new UserInfo(principal.getUsername(), roles);
    }

    @AllArgsConstructor
    @Getter
    static class UserInfo {
        String user;
        Collection<String> roles;
    }
}
