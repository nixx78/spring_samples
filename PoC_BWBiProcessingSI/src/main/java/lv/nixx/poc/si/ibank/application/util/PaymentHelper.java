package lv.nixx.poc.si.ibank.application.util;

import java.util.*;

import lv.nixx.poc.si.ibank.application.domain.*;


public class PaymentHelper {
	
	public static PaymentList createPaymentsList(String[] payments){ 
		PaymentList paymentList = new PaymentList();
		for (int i = 0; i < payments.length; i++) {
			String singlePayment = payments[i];
			paymentList.addPaymentToList(createPayment(singlePayment));
		}
		return paymentList;
	}
	
	public static Payment createPayment(String paymentAsString) {
		Payment payment = new Payment();
		// PaymentID:PaymentChannel:PaymentType:PaymentDetails
		StringTokenizer st = new StringTokenizer(paymentAsString,":");
		payment.setId(st.nextToken());
		payment.setChannel(st.nextToken());
		payment.setType(st.nextToken());
		payment.setDetails(st.nextToken());
		
		return payment;
	}
	
	public static String createPaymentResponseString(List<PaymentResponse> paymentsResponse) {
		
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<PaymentResponse> iterator = paymentsResponse.iterator(); iterator.hasNext();) {
			PaymentResponse paymentResponse = iterator.next();

			sb.append(paymentResponse.getResponseDetails());
			if (iterator.hasNext()){
				sb.append("#");
			}
		}
		
		return sb.toString(); 
	}

	public static String createPaymentResponseString(PaymentResponseList paymentsResponse) {
		return paymentsResponse.getProcessingState() + ":" + createPaymentResponseString(paymentsResponse.getPaymentResponseList()); 
	}

}
