package lv.nixx.poc.rest;

import lv.nixx.poc.rest.domain.Person;

public interface PersonService {
	
	public Person createPerson(Person person);
	public Person getPersonById(String id);
	public Person updatePerson(Person person);
	public void deletePerson(String id);
	
	public String[] getAllPersonsId();

}
