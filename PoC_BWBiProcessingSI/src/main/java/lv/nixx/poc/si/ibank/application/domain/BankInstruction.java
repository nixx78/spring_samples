package lv.nixx.poc.si.ibank.application.domain;

import java.io.Serializable;

public class BankInstruction implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String biType;
	private String biData;
	private String correlationID;
	
	public String getBiType() {
		return biType;
	}
	
	public void setBiType(String biType) {
		this.biType = biType;
	}
	
	public String getBiData() {
		return biData;
	}

	public void setBiData(String biData) {
		this.biData = biData;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}
	
}
