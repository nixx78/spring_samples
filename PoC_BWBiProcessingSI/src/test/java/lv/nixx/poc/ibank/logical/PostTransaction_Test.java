package lv.nixx.poc.ibank.logical;

import static org.junit.Assert.*;

import java.util.List;

import lv.nixx.poc.si.ibank.application.domain.*;
import lv.nixx.poc.si.ibank.application.gateway.PaymentGateway;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:app-config.xml")
public class PostTransaction_Test {

	@Autowired
	private PaymentGateway pmtGateway = null;
	
	@Test
	public void sendPaymentGroup(){
		PaymentList paymentList = new PaymentList();
		Payment atmPayment = new Payment();
		atmPayment.setId("ID1");
		atmPayment.setDetails("2011.08.26#USD#200.88");
		atmPayment.setType("PTYPE1");
		atmPayment.setChannel("ATM");

		Payment webPayment = new Payment();
		webPayment.setId("ID2");
		webPayment.setDetails("2011.08.25#USD#10.34");
		webPayment.setType("PTYPE2");
		webPayment.setChannel("WEB");

		Payment smsPayment = new Payment();
		smsPayment.setId("ID3");
		smsPayment.setDetails("2011.08.27#USD#100.00");
		smsPayment.setType("PTYPE3");
		smsPayment.setChannel("SMS");
		
		paymentList.addPaymentToList(atmPayment);
		paymentList.addPaymentToList(smsPayment);
		paymentList.addPaymentToList(webPayment);
		
		PaymentResponseList paymentResponseObj = pmtGateway.sendTransaction(paymentList);
		
		assertNotNull(paymentResponseObj);
		assertFalse(paymentResponseObj.getProcessingState());
		
		List<PaymentResponse> responseList = paymentResponseObj.getPaymentResponseList();
		assertEquals(3, responseList.size());
		
		for (PaymentResponse paymentResponse : responseList) {
			System.out.println(paymentResponse);
		}	
	
	}
	
	@Test
	public void sendPaymentToErrorChannel(){
		PaymentList paymentList = new PaymentList();
		Payment atmPayment = new Payment();
		atmPayment.setId("ID1");
		atmPayment.setDetails("2011.08.26#USD#200.88");
		atmPayment.setType("PTYPE1");
		atmPayment.setChannel("ATM");

		Payment errorPayment = new Payment();
		errorPayment.setId("ID2");
		errorPayment.setDetails("2011.08.25#USD#10.34");
		errorPayment.setType("PTYPE2");
		errorPayment.setChannel("ERROR");
		
		Payment smsPayment = new Payment();
		smsPayment.setId("ID3");
		smsPayment.setDetails("2011.08.27#USD#100.00");
		smsPayment.setType("PTYPE3");
		smsPayment.setChannel("SMS");

		
		paymentList.addPaymentToList(errorPayment);
		paymentList.addPaymentToList(atmPayment);
		paymentList.addPaymentToList(smsPayment);
		
		PaymentResponseList paymentResponseObj = pmtGateway.sendTransaction(paymentList);
		
		assertNotNull(paymentResponseObj);
		assertFalse(paymentResponseObj.getProcessingState());
		
		List<PaymentResponse> responseList = paymentResponseObj.getPaymentResponseList();
		assertEquals(1, responseList.size() );
	}

}
