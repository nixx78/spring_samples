package lv.nixx.poc.rest;

import java.util.Date;

import lv.nixx.poc.rest.domain.Person;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CRUDSampleController {
	
	@RequestMapping(method=RequestMethod.GET, value="/person/{id}")
	public @ResponseBody Person getPerson(@PathVariable String id) {
		return new Person("person.name", "person.surname", new Date());
	}

	@RequestMapping(method=RequestMethod.POST, value="/create_person")
	public @ResponseBody Person addPerson(@RequestBody Person p) {
		System.out.println("add person" + p);
		return p;
	}
		
	@RequestMapping(method=RequestMethod.PUT, value="/emp/{id}")
	public @ResponseBody Person updatePerson(@RequestBody Person p, @PathVariable String id) {
		System.out.println("update person" + p);
		return p;
	}
		
	@RequestMapping(method=RequestMethod.DELETE, value="/emp/{id}")
	public @ResponseBody void removePerson(@PathVariable String id) {
		System.out.println("remove person " + id);
	}
	
}
