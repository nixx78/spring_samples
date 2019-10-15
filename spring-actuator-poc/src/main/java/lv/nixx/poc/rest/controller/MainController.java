package lv.nixx.poc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MainController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@GetMapping(value="/hello")
	public @ResponseBody String request()  {
		return "Hello World, time:" + System.currentTimeMillis();
	}


}
