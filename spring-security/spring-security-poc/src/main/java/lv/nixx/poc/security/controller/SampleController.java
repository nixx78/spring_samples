package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.model.CustomUser;
import lv.nixx.poc.security.service.LoginService;
import lv.nixx.poc.security.service.SomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<String> getSample(@CookieValue(value = "count", required = false) Integer count , HttpServletResponse response) {
        int newCount = count == null ? 1 : count + 1;

        response.addCookie(new Cookie("count", String.valueOf(newCount)));
        LOG.info("/secured called, execution count [{}]", newCount);

        return new ResponseEntity<>("Success:secured", HttpStatus.OK);
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
