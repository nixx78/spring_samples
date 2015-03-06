package lv.nixx.poc.spring.data.domain;

import java.util.*;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;


/*
 * Можно выносить поля в супер класс, JPA будет их видеть, единсвенное, 
 * супер класс должен быть с аннотацией: @MappedSuperclass
 */
@Entity
@Table(name="Generic_Person")
public class GenericPerson extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;

	@Embedded
	private PersonExtension extension;
	/* Поля класса PersonExtension будут хранится в той-же самой таблице что и сущность GenericPerson */
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> aliase = new TreeSet<>();

	/* Данные из коллекции хранятся в отдельной таблице, имя которой мы указали в аннотации, обязательное условие, для класса AdditionalField
	 * должна стоять аннотация @@Embeddable
	 */
	@ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "Additional_Field")
	private Set<AdditionalField> additionalFields = new HashSet<>();

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
	
	public void addAliase(String alias){
		this.aliase.add(alias);
	}

	public Set<String> getAliases() {
		return aliase;
	}
	
	public Set<AdditionalField> getAdditionalFields() {
		return additionalFields;
	}

	public void addAdditionalField(AdditionalField additionalField) {
		this.additionalFields.add(additionalField);
	}

	@Override
	public String toString() {
		return "GenericPerson [name=" + name + ", surname=" + surname + ", extension=" + extension + "]";
	}
	
	

}
