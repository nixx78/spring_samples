package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.CustomUser;
import lv.nixx.poc.security.service.LoginService;
import lv.nixx.poc.security.service.SomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private static final Logger LOG = LoggerFactory.getLogger(SampleController.class);

    private final SomeService service;
    private final LoginService loginService;

    @Autowired
    public SampleController(SomeService service, LoginService loginService) {
        this.service = service;
        this.loginService = loginService;
    }

    @GetMapping("/secured")
    public String getSample() {
        LOG.info("/secured called");
        return "Success:secured";
    }

    @GetMapping("/basicSecured")
    public String getBasicSecured() {
        LOG.info("/getBasicSecured called");
        return "Success:basicSecured";
    }

    @GetMapping(value = "/userDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomUser getUserInfo() {
        return loginService.getLoggedInUser();
    }

    @GetMapping("/home")
    public String homeSample() {
        LOG.info("/home called");
        return "Success:home";
    }

    @GetMapping("/adminEndpoint")
    public String endpointForAdmin() {
        return service.methodForAdmin();
    }

    @GetMapping("/userEndpoint")
    public String endpointForUser() {
        return service.methodForUser();
    }

    @GetMapping("/allEndpoint")
    public String allEndpoint() {
        return service.methodForAll();
    }


}
