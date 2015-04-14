package lv.nixx.poc.si.ibank.application.domain;

import java.io.Serializable;

import org.springframework.messaging.MessagingException;

public class PaymentResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// By default - SUCCESS
	private ProcessingStatus processingStatus = ProcessingStatus.SUCCESS;
	
	private String responseDetails;
	private boolean processingState;
	private Payment originalPayment;
	private MessagingException messagingException;

	public String getResponseDetails() {
		return responseDetails;
	}

	public void setResponseDetails(String responseDetails) {
		this.responseDetails = responseDetails;
	}

	public boolean isProcessingState() {
		return processingState;
	}

	public void setProcessingState(boolean processingState) {
		this.processingState = processingState;
	}

	public Payment getOriginalPayment() {
		return originalPayment;
	}

	public void setOriginalPayment(Payment originalPayment) {
		this.originalPayment = originalPayment;
	}

	public MessagingException getMessageHandlingException() {
		return messagingException;
	}

	public void setMessageHandlingException(MessagingException messagingException) {
		this.messagingException = messagingException;
	}

	public ProcessingStatus getProcessingStatus() {
		if (messagingException!=null) {
			return ProcessingStatus.FAIL;
		}
		return processingStatus;
	}

	@Override
	public String toString() {
		return processingState + ":" + responseDetails;
	}

}
