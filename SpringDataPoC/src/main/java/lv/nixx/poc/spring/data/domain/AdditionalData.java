package lv.nixx.poc.spring.data.domain;

import javax.persistence.*;

@Entity
@Table(name="AdditionalData")
public class AdditionalData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String additionalData;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = MainData.class)
	@JoinColumn(name = "main_id")
	private MainData mainData;
	
	public AdditionalData(){
	}

	public AdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	
	public void setMainData(MainData mainData) {
		this.mainData = mainData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	public String toString() {
		return "AdditionalData [id=" + id + ", additionalData=" + additionalData + "]";
	}
}
