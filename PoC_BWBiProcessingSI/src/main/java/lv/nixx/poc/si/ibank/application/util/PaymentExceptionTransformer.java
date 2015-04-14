package lv.nixx.poc.si.ibank.application.util;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class PaymentExceptionTransformer {

	public Message<?> createPmtErrorResponse(Message<MessagingException> message){
		MessagingException messageHandlingException = message.getPayload();
		Message<?> failedMessage = messageHandlingException.getFailedMessage();
		
		Payment originalPayment = (Payment) failedMessage.getHeaders().get(BWMessageHeaders.ORIGINAL_BW_REQUEST);

		// FIXME create static method for Payment to create response in case of error ???
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setOriginalPayment(originalPayment);
		paymentResponse.setMessageHandlingException(messageHandlingException);
		
		
		PaymentResponseList list = new PaymentResponseList();
		list.addPaymentResponseToList(paymentResponse);

		return MessageBuilder.withPayload(list).build();
	}

}
