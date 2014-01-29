package lv.nixx.poc.rest.domain;

import java.util.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	private UUID id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	
	// this constructor without parameters is needed for JSON converter
	public Person(){
		this.id = UUID.randomUUID();
	}
	
	public Person(String name, String surname, Date dateOfBirth ){
		this.id = UUID.randomUUID();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	@XmlAttribute(name="id")
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id){
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public String toString(){
		return id + ":" + name + ":" + surname + ":" + dateOfBirth;
	}
	
}
