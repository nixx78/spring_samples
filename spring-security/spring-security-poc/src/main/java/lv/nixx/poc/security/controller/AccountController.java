package lv.nixx.poc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("")
    public String getAllAccounts() {
        return "AllAccounts";
    }

    @GetMapping("/{id}")
    public String getAccountInfo(@PathVariable String id) {
        return "Account:" + id + " info";
    }

}
