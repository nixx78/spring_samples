package lv.nixx.poc.rest.domain;

import java.util.Date;

public class Person {

	private String name;
	private String surname;
	private Date dateOfBirth;
	
	// this constructor is needed for JSON converter
	public Person(){
	}
	
	public Person(String name, String surname, Date dateOfBirth ){
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
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
		return name + ":" + surname + ":" + dateOfBirth;
	}
	
}
