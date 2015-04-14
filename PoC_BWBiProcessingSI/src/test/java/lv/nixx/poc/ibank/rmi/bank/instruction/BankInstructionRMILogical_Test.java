package lv.nixx.poc.ibank.rmi.bank.instruction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.StringTokenizer;

import lv.nixx.poc.ibank.rmi.GenericRMIServerTest;
import lv.nixx.poc.si.ibank.application.ibank.BOISManager;

import org.junit.Test;

public class BankInstructionRMILogical_Test extends GenericRMIServerTest{

    @Test
    public void sendBi_BI_TYPE1() throws Exception {
	    BOISManager rmiServer = getConnectionToServer();
	    
	    final String correlationID = System.currentTimeMillis() + "";
	    final String biType = "BI_TYPE1";
	    
	    String response = rmiServer.post_bank_instruction(correlationID + ":" + biType + ":BI data");
	
		assertNotNull(response);
		
		StringTokenizer st = new StringTokenizer(response, ":");
		assertEquals(correlationID, st.nextToken());
		assertEquals(biType, st.nextToken());
		
		System.out.println("BI response [" + response + "]");
    }

    @Test
    public void sendBi_BI_TYPE2() throws Exception {
	    BOISManager rmiServer = getConnectionToServer();
	    
	    final String correlationID = System.currentTimeMillis() + "";
	    final String biType = "BI_TYPE2";
	    
	    String response = rmiServer.post_bank_instruction(correlationID + ":" + biType + ":BI data");
	
		assertNotNull(response);
		
		StringTokenizer st = new StringTokenizer(response, ":");
		assertEquals(correlationID, st.nextToken());
		assertEquals(biType, st.nextToken());
		
		System.out.println("BI response [" + response + "]");
    }


    @Test
    public void sendBi_BI_TYPE_UNSUPORTED() throws Exception {
	    BOISManager rmiServer = getConnectionToServer();
	    
	    final String correlationID = System.currentTimeMillis() + "";
	    final String biType = "BI_TYPE_UNSUPORTED";
	    
	    String response = rmiServer.post_bank_instruction(correlationID + ":" + biType + ":BI data");
	
		assertNotNull(response);
		
		StringTokenizer st = new StringTokenizer(response, ":");
		assertEquals(correlationID, st.nextToken());
		assertEquals("NOT_SUPPORTED", st.nextToken());
		
		System.out.println("BI response [" + response + "]");
    }

 
}
