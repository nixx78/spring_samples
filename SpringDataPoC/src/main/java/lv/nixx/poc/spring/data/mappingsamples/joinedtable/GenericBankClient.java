package lv.nixx.poc.spring.data.mappingsamples.joinedtable;

import javax.persistence.*;

@Entity(name="BankClient.TablePerClassSample")
@Table(schema = "joined_table_sample",  name = "GenericClient")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "clientType")
public abstract class GenericBankClient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long clientId;

	private String name;
	private String surname;

	@Column(insertable = false, updatable = false)
	public String clientType;

	protected GenericBankClient() {
	}

	protected GenericBankClient(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

}
