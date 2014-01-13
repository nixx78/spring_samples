package lv.nixx.poc.rest;

import static org.junit.Assert.*;

import java.util.Date;

import lv.nixx.poc.rest.domain.Person;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

// Before test execution, sample server must be launched !!!
public class RESTClientSample {
	
	private final String URL = "http://localhost:8080/person/"; 
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void addPerson(){
		Person p = new Person("1000", "name", "surname", new Date());
	
		ResponseEntity<Person> response = restTemplate.postForEntity("http://localhost:8080/person", p, Person.class);
		p = response.getBody();
		
		assertNotNull(p);
		assertEquals("1000", p.getId());
		
		System.out.println("HTTP header 'Location' " + response.getHeaders().getFirst("Location"));
		System.out.println("created person " + p + " status " + response.getStatusCode() );
	}
	
	@Test
	public void updatePerson(){
		Person p = new Person("2000", "new.name", "new.surname", new Date());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL + "{id}", p, p.getId());
	}
	
	@Test
	public void deletePerson(){
		restTemplate.delete("URL + {id}", 1000);
	}
	
	@Test
	public void getGreeting(){
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/person/get_xml_string", String.class);
		String p = response.getBody();
		
		assertNotNull(p);
		
		System.out.println("greeting " + p);
	}

	@Test
	public void getPersonAsXML(){
		ResponseEntity<String> response = restTemplate.getForEntity(URL + "/xml/1000", String.class);
		String p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person as XML " + p);
	}
	
	@Test
	public void getPersonAsObject(){
		ResponseEntity<Person> response = restTemplate.getForEntity(URL + "/person/{id}", Person.class, 1000);
		Person p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person: " + p + " code " + response.getStatusCode());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void tryGetNotExistingPerson(){
		try {
			restTemplate.getForEntity("http://localhost:8080/person/2000", Person.class);
		} catch (HttpClientErrorException ex){
			System.out.println(ex.getStatusCode() + " " + ex.getStatusText());
			throw ex;
		}
	}
	
	@Test
	public void getPersonAsJSON(){
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/person/1000", String.class);
		String p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person: " + p);
	}

}
