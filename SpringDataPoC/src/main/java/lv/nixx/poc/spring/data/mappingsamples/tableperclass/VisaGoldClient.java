package lv.nixx.poc.spring.data.mappingsamples.tableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.math.*;

import lv.nixx.poc.spring.data.mappingsamples.VipClient;

@Entity(name = "VisaGoldClient.TPC")
@Table(schema = "table_per_class_sample", name = "VisaGoldClient")
public class VisaGoldClient extends VipClient {

	private BigDecimal cardLimit;

	public VisaGoldClient() {
		super();
	}

	public VisaGoldClient(String name, String surname, Long vipClientLevel, BigDecimal cardLimit) {
		super(name, surname, vipClientLevel);
		this.cardLimit = cardLimit;
	}

	public BigDecimal getCardLimit() {
		return cardLimit;
	}

}
