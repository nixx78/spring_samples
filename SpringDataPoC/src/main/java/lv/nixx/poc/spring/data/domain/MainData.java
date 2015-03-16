package lv.nixx.poc.spring.data.domain;

import javax.persistence.*;

@Entity
@Table(name="MainData")
@NamedQuery(name="MainData.FindAll",query = "Select m from MainData m")
public class MainData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String mainData;

    @OneToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy="mainData", targetEntity=AdditionalData.class, orphanRemoval=true)
    private AdditionalData additionalData;
	
	public MainData(){
	}
	
	public MainData(String mainData, AdditionalData additionalData) {
		this.mainData = mainData;
		this.additionalData = additionalData;
		if (this.additionalData !=null)
			this.additionalData.setMainData(this);
	}

	@Override
	public String toString() {
		return "MainData [id=" + id + ", mainData=" + mainData	+ ", additionalData=" + additionalData + "]";
	}

}
