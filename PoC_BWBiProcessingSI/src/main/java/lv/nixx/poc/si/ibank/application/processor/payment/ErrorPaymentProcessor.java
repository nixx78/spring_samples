package lv.nixx.poc.si.ibank.application.processor.payment;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class ErrorPaymentProcessor {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public PaymentResponse processPayment(Payment payment) throws Exception{
		logger.debug("process payment from ErrorChannel, id [" + payment.getId() + "] channel [" + payment.getChannel() + "]");
		throw new Exception("Payment processing exception");
	}

}
