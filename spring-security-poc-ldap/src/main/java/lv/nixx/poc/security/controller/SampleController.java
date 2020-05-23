package lv.nixx.poc.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/secured")
    @ResponseBody
    public String getSample() {
        return "Success:get";
    }


}
