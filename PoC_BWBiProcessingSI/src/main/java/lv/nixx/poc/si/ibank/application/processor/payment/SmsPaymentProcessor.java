package lv.nixx.poc.si.ibank.application.processor.payment;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class SmsPaymentProcessor {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public PaymentResponse processPayment(Payment payment){
		logger.debug("process payment from SMSChannel, id [" + payment.getId() + "] channel [" + payment.getChannel() + "]");

		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setProcessingState(true);
		paymentResponse.setResponseDetails(payment.getId() + ":RESPONSE_SMS");
	
		return paymentResponse;
	}

}
