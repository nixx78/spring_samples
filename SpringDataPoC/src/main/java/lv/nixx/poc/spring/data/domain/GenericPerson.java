package lv.nixx.poc.spring.data.domain;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="Generic_Person")

/*
 * Можно выносить поля в супер класс, JPA будет их видеть, единсвенное, 
 * супер класс должен быть с аннотацией: @MappedSuperclass
 */
public class GenericPerson extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;

	@Embedded
	private PersonExtension extension;
	/* Поля класса PersonExtension будут хранится в той-же самой таблице что и сущность GenericPerson */
	
	public GenericPerson(){
	}

	public GenericPerson(String name, String surname, PersonExtension ext) {
		this.name = name;
		this.surname = surname;
		this.extension = ext;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public PersonExtension getExtension() {
		return extension;
	}

	@Override
	public String toString() {
		return "GenericPerson [name=" + name + ", surname=" + surname + ", extension=" + extension + "]";
	}
	
	

}
