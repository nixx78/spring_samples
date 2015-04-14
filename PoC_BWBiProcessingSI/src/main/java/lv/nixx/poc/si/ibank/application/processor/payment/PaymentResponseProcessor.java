package lv.nixx.poc.si.ibank.application.processor.payment;

import java.util.List;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.*;
import org.springframework.stereotype.Component;

@Component
public class PaymentResponseProcessor {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public PaymentResponseList processPayment(List<PaymentResponse> paymentResponseList){
		logger.debug("create PaymentResponseList object");
		
		PaymentResponseList paymentResponseObj = new PaymentResponseList();
		for (PaymentResponse paymentResponse : paymentResponseList) {
			paymentResponseObj.addPaymentResponseToList(paymentResponse);
		}
		return paymentResponseObj;
	}

}
