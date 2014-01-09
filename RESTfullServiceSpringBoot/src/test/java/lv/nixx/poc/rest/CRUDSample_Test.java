package lv.nixx.poc.rest;

import static org.junit.Assert.*;

import java.util.Date;

import lv.nixx.poc.rest.domain.Person;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// Before test execution, sample server must be launched !!!
public class CRUDSample_Test {
	
	@Test
	public void getPersonAsObject(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Person> response = restTemplate.getForEntity("http://localhost:8080/person/1000", Person.class);
		Person p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person: " + p);
	}
	
	@Test
	public void getPersonAsJSON(){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/person/1000", String.class);
		String p = response.getBody();
		
		assertNotNull(p);
		System.out.println("person: " + p);
	}
	
	@Test
	public void addPerson(){
		Person newEmp = new Person("name", "surname", new Date());
		HttpEntity<Person> entity = new HttpEntity<Person>(newEmp);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Person> response = restTemplate.postForEntity("http://localhost:8080/person", entity, Person.class);
		Person p = response.getBody();
		
		assertNotNull(p);

		
		System.out.println("person " + p);
	}
	
	@Test
	public void getGreeting(){

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/get_xml_string", String.class);
		String p = response.getBody();
		
		assertNotNull(p);
		
		System.out.println("greeting " + p);
	}
	

}
