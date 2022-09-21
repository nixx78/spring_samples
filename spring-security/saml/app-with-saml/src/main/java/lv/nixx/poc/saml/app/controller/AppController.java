package lv.nixx.poc.saml.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/authSuccess")
    public String getSuccessMessage() {
        return "SuccessMessage:" + System.currentTimeMillis();
    }

    @GetMapping("/authFail")
    public String getFailMessage() {
        return "failMessage:" + System.currentTimeMillis();
    }

    @GetMapping("/endpointForAdmin")
    public String getMessageForAdmin() {
        return "message for Admin:" + System.currentTimeMillis();
    }

    @GetMapping("/endpointForAll")
    public String getMessageForAll() {
        return "message for All:" + System.currentTimeMillis();
    }

    @GetMapping("/openEndpoint")
    public String getMessageOpenEndpoint() {
        return "message for 'OpenEndpoint':" + System.currentTimeMillis();
    }

}
