package lv.nixx.poc.si.ibank.application.processor.bankinstruction;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BiType2Processor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
			
	public BankInstructionResponse processBankInstruction(BankInstruction bankInstruction){
		logger.debug("process BI, type [" + bankInstruction.getBiType() + "] correlation ID [" + bankInstruction.getCorrelationID() + "]");
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BankInstructionResponse response = BankInstructionResponse.createBankInstructionResponse(bankInstruction, "BI_TYPE2:Response" + bankInstruction.getBiData());
		return response;
	}
	
}
