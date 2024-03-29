package lv.nixx.poc.security.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/urlBased")
public class ControllerWithSecuredUrls {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerWithSecuredUrls.class);

    @GetMapping("/secured")
    public ResponseEntity<String> getSample(@CookieValue(value = "count", required = false) Integer count, HttpServletResponse response) {
        int newCount = count == null ? 1 : count + 1;

        response.addCookie(new Cookie("count", String.valueOf(newCount)));
        LOG.info("/secured called, execution count [{}]", newCount);

        return new ResponseEntity<>("Success:secured", HttpStatus.OK);
    }

    @GetMapping("/basicSecured")
    public String getBasicSecured() {
        return "Success:basicSecured";
    }

    @GetMapping("/action")
    public String getAvailableAction() {
        LOG.info("/action called");
        return "'Action' available";
    }

    @GetMapping("/process")
    public String process(@Parameter String action) {
        LOG.info("/process called");
        return "Process action: " + action;
    }

    @PostMapping("/action")
    public String processAction(@RequestBody String action) {
        LOG.info("Process action [{}]", action);
        return String.format("Action: '%s' processed", action);
    }

    @GetMapping("/home")
    public String homeSample() {
        LOG.info("/home called");
        return "Success:home";
    }

    @GetMapping("/twoRolesRequired")
    public String twoRolesRequired() {
        LOG.info("/twoRolesRequired called");
        return "Success:twoRolesRequired";
    }

    @GetMapping("/process/{name}")
    public String processActionWithPathVariable(@PathVariable String name) {
        LOG.info("Process action [{}]", name);
        return "Success:action:" + name;
    }

}
