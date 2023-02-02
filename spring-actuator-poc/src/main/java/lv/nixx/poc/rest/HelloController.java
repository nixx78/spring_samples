package lv.nixx.poc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/message")
    public String getMessage() {
        return "Response:" + System.currentTimeMillis();
    }

}
