package lv.nixx.poc.rest.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/* Domain object for sample
 * 
 */

@XmlRootElement
public class Person {

	private String id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	
	// this constructor is needed for JSON converter
	public Person(){
	}
	
	public Person(String id, String name, String surname, Date dateOfBirth ){
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
