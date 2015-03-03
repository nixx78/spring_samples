package lv.nixx.poc.spring.data.domain;

import javax.persistence.*;

@Entity
public class CustomerType {

	private String type;
	
	private String description;
	
	public CustomerType(){}
	
	public CustomerType(String type, String description) {
		this.type = type;
		this.description = description;
	}

	@Id
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CustomerType [type=" + type + ", description=" + description + "]";
	}

}
