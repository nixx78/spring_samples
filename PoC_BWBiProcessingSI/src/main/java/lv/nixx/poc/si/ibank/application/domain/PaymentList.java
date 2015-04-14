package lv.nixx.poc.si.ibank.application.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaymentList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Payment> paymentsList = new ArrayList<Payment>();
	
	public void addPaymentToList(Payment payment) {
		this.paymentsList.add(payment);
	}
	
	public List<Payment> getPaymentsList(){
		return paymentsList;
	}
	

}
