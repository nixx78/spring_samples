package lv.nixx.poc.rest;

import java.util.*;
import lv.nixx.poc.rest.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/" + PersonController.person)
public class PersonController {
	
	static final String person = "person";
	
	Map<UUID, UUID[]> removeBatch = new HashMap<>();
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Person> addPerson(@RequestBody Person p, UriComponentsBuilder builder) {
		log.debug("Adding person [{}]", p);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(p.getId()).toUri());
		
		return new ResponseEntity<Person>(p, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/xml", produces="application/xml")
	public @ResponseBody Person getPersonAsXML(@PathVariable String id) {
		log.debug("Get person by [{}] as XML", id);
		
		Person p = new Person("person.name", "person.surname", new Date());
		p.setId(UUID.fromString(id));
				
		return p;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable(name="id") String id) {
		
		if (id.equalsIgnoreCase("2000")){ 
			// just fake behavior, we expect, that Person with id = '2000' not exists
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		Person p = new Person("person.name", "person.surname", new Date());
		p.setId(UUID.fromString(id));
		
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public @ResponseBody Person updatePerson(@RequestBody Person p, @PathVariable String id) {
		log.debug("Update person, id [{}]", id);
		return p;
	}
		
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public @ResponseBody void removePerson(@PathVariable String id) {
		log.debug("remove person, id [{}]", id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/deletes")
	public @ResponseBody ResponseEntity<String> postPersonRemoveBatch(@RequestBody UUID[] ids, UriComponentsBuilder builder) {
        final UUID batchId = UUID.randomUUID();
        log.debug("Add persons to batch id [{}]", batchId);
        Arrays.stream(ids).forEach(t->log.debug(t.toString()));
        
		removeBatch.put(batchId, ids);
		
        final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path(person + "/deletes/{id}").buildAndExpand(batchId).toUri());
		
		return new ResponseEntity<String>(batchId.toString(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deletes/{batchId}")
	public @ResponseBody ResponseEntity<String> removePersonBatch(@PathVariable(name="batchId") UUID batchId) {
		log.debug("Delete persons from batch id [{}]", batchId);
		
		if (removeBatch.containsKey(batchId)) {
			UUID[] uuids = removeBatch.remove(batchId);
	        Arrays.stream(uuids).forEach(t->log.debug(t.toString()));
			return new ResponseEntity<String>(batchId.toString(), HttpStatus.OK);		
		}

		return new ResponseEntity<String>(batchId.toString(), HttpStatus.NOT_FOUND);
	}
	
}
