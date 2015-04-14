package lv.nixx.poc.si.ibank.application.domain;

import java.io.Serializable;

public class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String channel;
	private String details;
	private String type;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return id + ":" + channel +":" + type + ":" + details;
	}
}
