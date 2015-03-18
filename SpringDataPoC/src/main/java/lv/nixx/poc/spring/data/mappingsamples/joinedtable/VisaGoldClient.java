package lv.nixx.poc.spring.data.mappingsamples.joinedtable;

import javax.persistence.*;

import java.math.*;

import lv.nixx.poc.spring.data.mappingsamples.VipClient;

@Entity(name = "VisaGoldClient.JS")
@Table(schema = "joined_table_sample", name = "VisaGoldClient")
@DiscriminatorValue("VisaGoldClientType.JS")
public class VisaGoldClient extends VipClient {

	private BigDecimal cardLimit;

	public VisaGoldClient() {
		super();
	}

	public VisaGoldClient(String name, String surname, Long vipClientLevel,	BigDecimal cardLimit) {
		super(name, surname, vipClientLevel);
		this.cardLimit = cardLimit;
	}

	public BigDecimal getCardLimit() {
		return cardLimit;
	}

}
