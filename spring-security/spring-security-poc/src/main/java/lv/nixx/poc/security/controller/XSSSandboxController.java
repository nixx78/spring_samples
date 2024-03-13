package lv.nixx.poc.security.controller;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class XSSSandboxController {

    @GetMapping("/xss-path-variable/{param}")
    public Map<String, String> processPathVariable(@PathVariable("param") String param) {
        return Map.of("response", "Text " + param);
    }

    @GetMapping("/xss-param-string-response")
    public String processParamAndReturnString(@RequestParam("param") String param) {
        return param;
    }

    @GetMapping("/xss-param-escaped-string-response")
    public String processParamAndReturnEscapedString(@RequestParam("param") String param) {
        return StringEscapeUtils.escapeHtml4(param);
    }

    @PostMapping("/xss")
    public Map<String, String> processBodyAndReturnJSON(@RequestBody String param) {
        return Map.of("response", "Text " + param);
    }

    @PostMapping("/xss/escaped-response")
    public Map<String, String> processBodyAndReturnJSONWithEscape(@RequestBody String param) {
        return Map.of("response", "Text " + StringEscapeUtils.escapeHtml4(param));
    }

    @PutMapping("/xss")
    public String processBodyAndReturnString(@RequestBody String param) {
        return param;
    }

}
