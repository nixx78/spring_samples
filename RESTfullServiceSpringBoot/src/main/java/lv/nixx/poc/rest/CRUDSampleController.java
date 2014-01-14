package lv.nixx.poc.rest;

import java.util.Date;
import java.util.UUID;

import lv.nixx.poc.rest.domain.Person;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class CRUDSampleController {
	
	private PersonService personService;
	
	@RequestMapping(method=RequestMethod.POST, value="/person")
	public @ResponseBody ResponseEntity<Person> addPerson(@RequestBody Person p, UriComponentsBuilder builder) {
		System.out.println("add person [" + p + "]");
		
		// personService.createPerson(p);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(p.getId()).toUri());
		
		return new ResponseEntity<Person>(p, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/person/xml/{id}", produces="application/xml")
	public @ResponseBody Person getPersonAsXML(@PathVariable String id) {
		return new Person(UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13"), "person.name", "person.surname", new Date());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/person/{id}")
	public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable String id) {
		
		if (id.equalsIgnoreCase("2000")){ 
			// just fake behavior, we expect, that Person with id = '2000' not exists
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(new Person(UUID.fromString(id), "person.name", "person.surname", new Date()), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/person/{id}")
	public @ResponseBody Person updatePerson(@RequestBody Person p, @PathVariable String id) {
		System.out.println("update person, id [" + p + "]");
		return p;
	}
		
	@RequestMapping(method=RequestMethod.DELETE, value="/person/{id}")
	public @ResponseBody void removePerson(@PathVariable String id) {
		System.out.println("remove person, id [" + id + "]");
	}
	
}
