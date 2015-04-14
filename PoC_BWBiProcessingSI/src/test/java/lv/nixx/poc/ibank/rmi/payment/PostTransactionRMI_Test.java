package lv.nixx.poc.ibank.rmi.payment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lv.nixx.poc.ibank.rmi.GenericRMIServerTest;
import lv.nixx.poc.si.ibank.application.ibank.BOISManager;

import org.junit.Test;


public class PostTransactionRMI_Test extends GenericRMIServerTest {

	@Test
	public void sendPaymentFromATMChannel() throws MalformedURLException, RemoteException, NotBoundException {
		
		BOISManager bois = getConnectionToServer();

		String[] payments = new String[]{
				"pmt10001:ATM:PTYPE2:2011.08.25#EUR#100.00",
		};

		String paymentResponse = bois.post_transaction(payments);

		System.out.println("response on payment [" + paymentResponse + "]");
		
		assertNotNull(paymentResponse);
		assertEquals("false:pmt10001:ATM_CHANNEL_NOT_SUPPORTED", paymentResponse);
	}

}
