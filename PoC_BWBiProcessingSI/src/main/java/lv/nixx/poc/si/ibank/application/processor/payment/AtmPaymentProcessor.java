package lv.nixx.poc.si.ibank.application.processor.payment;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class AtmPaymentProcessor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public PaymentResponse processPayment(Payment payment){

		logger.debug("process payment from ATMChannel, id [" + payment.getId() + "] channel [" + payment.getChannel() + "]");

		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setProcessingState(false);
		paymentResponse.setResponseDetails(payment.getId() + ":ATM_CHANNEL_NOT_SUPPORTED");

		return paymentResponse;
	}

}
