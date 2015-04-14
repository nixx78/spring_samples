package lv.nixx.poc.ibank.helper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import lv.nixx.poc.si.ibank.application.domain.*;
import lv.nixx.poc.si.ibank.application.util.PaymentHelper;

import org.junit.Test;

public class PaymentHelperTest {

	@Test
	public void createPaymentList() {
		
		final String firstPmtID = "pmtID1";
		final String secondPmtID = "pmtID2";
		
		// PaymentID:PaymentChannel:PaymentType:PaymentDetails
		String[] payments = new String[]{
			firstPmtID + ":WEB:PTYPE1:2011.08.26#USD#200.88",
			secondPmtID + ":ATM:PTYPE2:2011.08.25#EUR#100.00"
		};
		
		PaymentList paymentList = PaymentHelper.createPaymentsList(payments);
		
		List<Payment> result = paymentList.getPaymentsList();
		
		assertEquals(payments.length, result.size());
		assertEquals(firstPmtID, result.get(0).getId());
		assertEquals(secondPmtID, result.get(1).getId());
		
		System.out.println( result.get(0).getDetails() );
		System.out.println( result.get(1).getDetails() );
		
	}
	
	@Test
	public void createStringFromPaymentResponse(){
		
		List<PaymentResponse> pmtRepsponseList = new ArrayList<PaymentResponse>();
		
		PaymentResponse pmtResponse1 = new PaymentResponse();
		pmtResponse1.setResponseDetails("ID1:OK");
		PaymentResponse pmtResponse2 = new PaymentResponse();
		pmtResponse2.setResponseDetails("ID2:FAIL");
		
		pmtRepsponseList.add(pmtResponse1);
		pmtRepsponseList.add(pmtResponse2);
		
		final String responseString = PaymentHelper.createPaymentResponseString(pmtRepsponseList);
		System.out.println(responseString);
		
		assertNotNull(responseString);
		assertEquals("ID1:OK#ID2:FAIL", responseString);
	}
	
	
	
}
