package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.CustomUser;
import lv.nixx.poc.security.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private SomeService service;

    @Autowired
    public SampleController(SomeService service) {
        this.service = service;
    }

    @GetMapping("/secured")
    @ResponseBody
    public String getSample() {
        return "Success:get";
    }

    @GetMapping(value = "/userDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomUser getUserInfo(Authentication auth) {
        return (CustomUser) auth.getPrincipal();
    }

    @GetMapping("/home")
    @ResponseBody
    public String homeSample() {
        return "Success:home";
    }

    @GetMapping("/adminEndpoint")
    public String endpointForAdmin() {
        return service.methodForAdmin();
    }


}
