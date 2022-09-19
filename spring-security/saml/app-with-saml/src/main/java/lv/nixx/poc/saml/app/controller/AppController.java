package lv.nixx.poc.saml.app.controller;

import lv.nixx.poc.saml.app.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    private final LoginService loginService;

    @Autowired
    public AppController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("user", loginService.getLoggedUser());
        return "index";
    }

}
