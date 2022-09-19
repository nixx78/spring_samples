package lv.nixx.poc.saml.app.controller;

import lv.nixx.poc.saml.app.AppUser;
import lv.nixx.poc.saml.app.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/userInfo")
    public AppUser getUserInfo() {
        return loginService.getLoggedUser();
    }

}
