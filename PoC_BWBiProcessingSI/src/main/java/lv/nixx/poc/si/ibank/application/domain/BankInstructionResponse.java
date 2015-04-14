package lv.nixx.poc.si.ibank.application.domain;

import org.springframework.messaging.MessagingException;

public class BankInstructionResponse {
	
	// By default SUCCESS
	private ProcessingStatus processingStatus = ProcessingStatus.SUCCESS;
	
	private String correlationID;
	private String responseData;
	
	private BankInstruction bankInstruction;
	private MessagingException messagingException;
	
	private BankInstructionResponse(BankInstruction bankInstruction){
		this.bankInstruction = bankInstruction;
		this.correlationID = bankInstruction.getCorrelationID();
	}
	
	public static BankInstructionResponse createBankInstructionResponse(BankInstruction originalBankInstruction, String responseData){
		BankInstructionResponse response = new BankInstructionResponse(originalBankInstruction);
		response.setResponseData(responseData);
		return response;
	}
	
	public String getResponseData() {
		return responseData;
	}
	
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public BankInstruction getBankInstruction() {
		return bankInstruction;
	}
	
	public String getCorrelationID() {
		return correlationID;
	}
	
	public String toString(){
		return correlationID + ":" + responseData;
	}

	public ProcessingStatus getProcessingStatus() {
		if (messagingException!=null) {
			return ProcessingStatus.FAIL;
		}
		return processingStatus;
	}

	public MessagingException getMessageHandlingException() {
		return messagingException;
	}

	public void setMessageHandlingException(MessagingException messageHandlingException) {
		this.messagingException = messageHandlingException;
	}
	
}
