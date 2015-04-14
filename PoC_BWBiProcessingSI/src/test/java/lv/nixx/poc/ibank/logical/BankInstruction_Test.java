package lv.nixx.poc.ibank.logical;

import static org.junit.Assert.*;
import lv.nixx.poc.si.ibank.application.domain.*;
import lv.nixx.poc.si.ibank.application.gateway.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:app-config.xml")
public class BankInstruction_Test {

	@Autowired
	private BankInstructionGateway biGateway = null;
	
	@Test
	public void callBiType1(){
    	
    	BankInstruction bankInstruction = new BankInstruction();
    	bankInstruction.setCorrelationID("correlationID");
    	bankInstruction.setBiType("BI_TYPE1");
    	bankInstruction.setBiData("BI data");
    	
		BankInstructionResponse bankInstructuinResponse = biGateway.sendBankInstruction(bankInstruction);
		
		System.out.println("BI response [" + bankInstructuinResponse + "]");
		
		assertNotNull(bankInstructuinResponse);
		assertEquals(bankInstruction.getCorrelationID(), bankInstructuinResponse.getCorrelationID() );
		assertEquals("correlationID:BI_TYPE1:ResponseBI data", bankInstructuinResponse.toString());
	}

	@Test
	public void callBiType2(){
    	BankInstruction bankInstruction = new BankInstruction();
    	bankInstruction.setCorrelationID("correlationID");
    	bankInstruction.setBiType("BI_TYPE2");
    	bankInstruction.setBiData("BI data");
    	
		BankInstructionResponse bankInstructionResponse = biGateway.sendBankInstruction(bankInstruction);
		
		System.out.println("BI response [" + bankInstructionResponse + "]");
		
		assertNotNull(bankInstructionResponse);
		assertEquals(bankInstruction.getCorrelationID(), bankInstructionResponse.getCorrelationID() );
		assertEquals("correlationID:BI_TYPE2:ResponseBI data", bankInstructionResponse.toString());
	}

	@Test
	public void callNotSupportedBiType(){
    	BankInstruction bankInstruction = new BankInstruction();
    	bankInstruction.setCorrelationID("correlationID");
    	bankInstruction.setBiType("NOT_SUPPORTED_BI_TYPE");
    	bankInstruction.setBiData("BI data");
    	
		BankInstructionResponse bankInstructionResponse = biGateway.sendBankInstruction(bankInstruction);  
		
		System.out.println("BI response [" + bankInstructionResponse + "]");
		
		assertNotNull(bankInstructionResponse);
		assertEquals(bankInstruction.getCorrelationID(), bankInstructionResponse.getCorrelationID() );
		assertEquals("correlationID:NOT_SUPPORTED:FAIL", bankInstructionResponse.toString());
	}

	@Test
	public void callErrorBiType(){
    	BankInstruction bankInstruction = new BankInstruction();
    	bankInstruction.setCorrelationID("correlationID");
    	bankInstruction.setBiType("ERROR_BI");
    	bankInstruction.setBiData("ERROR_BI data");
    	
		BankInstructionResponse bankInstructionResponse = biGateway.sendBankInstruction(bankInstruction);
		assertNotNull(bankInstructionResponse);
		assertEquals(bankInstructionResponse.getProcessingStatus(), ProcessingStatus.FAIL);
		assertEquals(bankInstruction.getCorrelationID(), bankInstructionResponse.getCorrelationID() );
		assertEquals(bankInstructionResponse.getBankInstruction(), bankInstruction);
	}

}
