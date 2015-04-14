package lv.nixx.poc.si.ibank.application.util;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.DestinationResolutionException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class BiExceptionTransformer {
	
	private Logger log = LoggerFactory.getLogger(BiExceptionTransformer.class);

	public Message<?> createBiErrorResponse(Message<MessagingException> message){
		MessagingException messageHandlingException = message.getPayload();
		log.error("BiExceptionTransformer: " + messageHandlingException.getMessage());
		
		BankInstructionResponse bankInstructionResponse = null;

		Message<?> failedMessage = messageHandlingException.getFailedMessage();
		BankInstruction originalBankInstruction = (BankInstruction) failedMessage.getHeaders().get(BWMessageHeaders.ORIGINAL_BW_REQUEST);

		if (messageHandlingException.getCause() instanceof DestinationResolutionException){
			bankInstructionResponse = BankInstructionResponse.createBankInstructionResponse(originalBankInstruction, "NOT_SUPPORTED:FAIL");
		} else {
			bankInstructionResponse = BankInstructionResponse.createBankInstructionResponse(originalBankInstruction, "");
		}
		bankInstructionResponse.setMessageHandlingException(messageHandlingException);

		return MessageBuilder.withPayload(bankInstructionResponse).build();
	}

}
