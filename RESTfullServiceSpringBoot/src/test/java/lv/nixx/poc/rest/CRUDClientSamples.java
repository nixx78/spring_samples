package lv.nixx.poc.rest;

import static org.junit.Assert.*;
import static lv.nixx.poc.rest.PersonFixtures.*;

import java.util.Date;
import java.util.UUID;

import lv.nixx.poc.rest.domain.Person;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

// Before test execution, REST sample server must be launched !!!

public class CRUDClientSamples {
	
	private final String URL = "http://localhost:8080/person";

	private final UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void addPerson(){
		Person p = new Person("name", "surname", new Date());
	
		ResponseEntity<Person> response = restTemplate.postForEntity(URL, p, Person.class);
		p = response.getBody();
		
		assertNotNull(p);
		String location = response.getHeaders().getFirst("Location");
		HttpStatus statusCode = response.getStatusCode();

		assertNotNull("location", location ); 
		assertEquals("status code", HttpStatus.CREATED, statusCode);
		
		System.out.println("HTTP header 'Location' " + location);
		System.out.println("created person " + p + " status " + statusCode );
	}
	
	@Test
	public void updatePerson(){
		Person p = new Person("new.name", "new.surname", new Date());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL + "/{id}", p, p.getId());
	}
	
	@Test
	public void deletePerson(){
		restTemplate.delete(URL + "/{id}", key);
	}

	@Test
	public void getPersonAsXML(){
		ResponseEntity<String> response = restTemplate.getForEntity(URL + "/{id}/xml", String.class, key);
		String p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person as XML " + p);
	}
	
	@Test
	public void getPersonAsObject(){
		ResponseEntity<Person> response = restTemplate.getForEntity(URL + "/{id}", Person.class, key);
		Person p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person: " + p + " code " + response.getStatusCode());
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void tryGetNotExistingPerson(){
		try {
			restTemplate.getForEntity(URL + "/{id}", Person.class, "2000");
		} catch (HttpClientErrorException ex){
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode() );
			System.out.println(ex.getStatusCode() + " " + ex.getStatusText());
			throw ex;
		}
	}
	
	@Test
	public void getPersonAsJSON(){
		ResponseEntity<String> response = restTemplate.getForEntity(URL + "/{id}", String.class, key.toString());
		String p = response.getBody();
				
		assertNotNull(p);
		System.out.println(response.getStatusCode() + " person: " + p);
	}
	
	@Test
	public void updatePersonAsJSON(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(createPersonJSON(key), headers);
		restTemplate.put(URL + "/{id}", entity, key);
	}

	@Test
	public void updatePersonAsXML(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<String> entity = new HttpEntity<String>(createPersonXML(key), headers);
		restTemplate.put(URL + "/{id}", entity, key);
	}
	
	@Test
	public void addPersonAsJSON(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(createPersonJSON(key), headers);
		restTemplate.postForLocation(URL, entity, String.class);
	}
	
	@Test
	public void addPersonAsXML(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		
		HttpEntity<String> entity = new HttpEntity<String>(createPersonXML(key),headers);
		restTemplate.postForLocation(URL, entity, String.class);
	} 

}
