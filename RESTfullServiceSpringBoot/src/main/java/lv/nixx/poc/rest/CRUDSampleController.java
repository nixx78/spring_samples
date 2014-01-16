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
	
	@RequestMapping(method=RequestMethod.POST, value="/person")
	public @ResponseBody ResponseEntity<Person> addPerson(@RequestBody Person p, UriComponentsBuilder builder) {
		System.out.println("adding person [" + p + "]");
		
		// personService.createPerson(p);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(p.getId()).toUri());
		
		return new ResponseEntity<Person>(p, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/person/xml/{id}", produces="application/xml")
	public @ResponseBody Person getPersonAsXML(@PathVariable String id) {
		System.out.println("get [" + id + "]");
		
		Person p = new Person("person.name", "person.surname", new Date());
		p.setId(UUID.fromString(id));
				
		return p;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/person/{id}")
	public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable String id) {
		
		if (id.equalsIgnoreCase("2000")){ 
			// just fake behavior, we expect, that Person with id = '2000' not exists
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		Person p = new Person("person.name", "person.surname", new Date());
		p.setId(UUID.fromString(id));
		
		return new ResponseEntity<Person>(p, HttpStatus.OK);
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
