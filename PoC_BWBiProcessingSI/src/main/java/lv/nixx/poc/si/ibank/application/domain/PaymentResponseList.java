package lv.nixx.poc.si.ibank.application.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaymentResponseList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<PaymentResponse> paymentResponseList = new ArrayList<PaymentResponse>();
	private boolean processingState = true;

	public void addPaymentResponseToList(PaymentResponse paymentResponse){
		boolean pmtResp = paymentResponse.isProcessingState();
		
		// at least one payment processing result is false
		if (!pmtResp && processingState ) {
			this.processingState = false;
		}
		
		this.paymentResponseList.add(paymentResponse);
	}
	
	public List<PaymentResponse> getPaymentResponseList(){
		return paymentResponseList;
	}
	
	public boolean getProcessingState(){
		return processingState;
	}

}
