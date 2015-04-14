package lv.nixx.poc.si.ibank.application.processor.payment;

import java.util.ArrayList;
import java.util.List;

import lv.nixx.poc.si.ibank.application.domain.*;

import org.slf4j.*;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

// This class split's PaymentList object to separate messages with object Payment
// and set values from Payment to Message Headers

@Component
public class PaymentListSplitter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Message<Payment>> splitPaymentList(Message<PaymentList> incomingMessage) {
		List<Payment> imcomingPayments = incomingMessage.getPayload().getPaymentsList();
	    logger.debug("payment splitter call for [{}] messages", imcomingPayments.size());
	    
		List<Message<Payment>> outPaymentList = new ArrayList<Message<Payment>>();
		
		for (Payment payment : imcomingPayments) {
			MessageBuilder<Payment> messageBuilder = MessageBuilder.withPayload(payment);
			messageBuilder.copyHeaders(incomingMessage.getHeaders());
			// set original payment to use it in case of error
			messageBuilder.setHeader(BWMessageHeaders.ORIGINAL_BW_REQUEST , payment);
			outPaymentList.add(messageBuilder.build());
		}
		
		return outPaymentList;
	}
	
}
