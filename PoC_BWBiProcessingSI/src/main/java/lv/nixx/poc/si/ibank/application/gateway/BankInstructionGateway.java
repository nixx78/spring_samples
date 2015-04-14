package lv.nixx.poc.si.ibank.application.gateway;

import lv.nixx.poc.si.ibank.application.domain.BankInstruction;
import lv.nixx.poc.si.ibank.application.domain.BankInstructionResponse;

public interface BankInstructionGateway {
	public BankInstructionResponse sendBankInstruction(BankInstruction bankInstruction);
}
