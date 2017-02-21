package lv.nixx.poc.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Engine {
	
	@Autowired
	private MyService service;
	
	public void process() {
		service.serve();
		System.out.println("Engine: process");
	}

}
