package lv.nixx.poc.spring.data.mappingsamples;

import javax.persistence.MappedSuperclass;

import lv.nixx.poc.spring.data.mappingsamples.singletable.GenericBankClient;

@MappedSuperclass
public abstract class SimpleClient extends GenericBankClient {

	protected String simpleClientDescription;

	public SimpleClient() {
		super();
	}

	public SimpleClient(String name, String surname, String simpleClientDescription) {
		super(name, surname);
		this.simpleClientDescription = simpleClientDescription;
	}

}
