package lv.nixx.poc.si.ibank.application.processor.bankinstruction;

import org.springframework.stereotype.Component;

import lv.nixx.poc.si.ibank.application.domain.*;

@Component
public class ErrorBiProcessor {

	public BankInstructionResponse processBankInstruction(BankInstruction bankInstruction) throws Exception {
		callMethodWithNPE();
		return null;
	}
	
	private void callMethodWithNPE(){
		((String)null).substring(0,12);
	}

}
