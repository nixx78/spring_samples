package lv.nixx.poc.spring.data.mappingsamples.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.math.*;

import lv.nixx.poc.spring.data.mappingsamples.VipClient;

@Entity(name="VisaGoldClient.ST")
@Table(schema="single_table_sample", name = "VisaGoldClient")
@DiscriminatorValue("VisaGoldClientType")
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
