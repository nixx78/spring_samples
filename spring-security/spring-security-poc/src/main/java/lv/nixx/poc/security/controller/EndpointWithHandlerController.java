package lv.nixx.poc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointWithHandlerController {

    @GetMapping("/endpointWithHandler")
    public String process() {
        return "EndpointWithHandler:" + System.currentTimeMillis() + " processed";
    }

}
