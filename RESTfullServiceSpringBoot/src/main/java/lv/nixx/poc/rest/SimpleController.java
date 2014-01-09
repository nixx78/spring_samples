package lv.nixx.poc.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/get_json", produces="application/json", method=RequestMethod.GET)
    public @ResponseBody Greeting getGreetingAsJSON(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping(value="/get_xml", produces="application/xml", method=RequestMethod.GET)
    public @ResponseBody Greeting getGreetingAsXML(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping(value="/get_string")
    public @ResponseBody String getGreetingsAsString(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	return "string, name [" + name + "]";
    }
    

    @RequestMapping(value="/get_xml_string")
    public @ResponseBody String getGreetingsAsXMLString(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	return "<response><greeting>" + name + "</greeting></response>";
    }

    
    
}