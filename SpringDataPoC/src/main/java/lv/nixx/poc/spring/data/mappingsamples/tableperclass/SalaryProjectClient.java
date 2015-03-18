package lv.nixx.poc.spring.data.mappingsamples.tableperclass;

import java.math.BigDecimal;

import javax.persistence.*;

import lv.nixx.poc.spring.data.mappingsamples.SimpleClient;

@Entity(name = "SalaryProjectClient.TPC")
@Table(schema = "table_per_class_sample", name = "SalaryProjectClient")
public class SalaryProjectClient extends SimpleClient {

	private BigDecimal salarySize;
	
	public SalaryProjectClient(){
		super();
	}
	
	public SalaryProjectClient(String name, String surname, String simpleClientDescription, BigDecimal salarySize) {
		super(name, surname, simpleClientDescription);
		this.salarySize = salarySize;
	}

	public BigDecimal getSalarySize() {
		return salarySize;
	}

}
