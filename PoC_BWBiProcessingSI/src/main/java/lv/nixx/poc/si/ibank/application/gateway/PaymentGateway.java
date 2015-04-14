package lv.nixx.poc.si.ibank.application.gateway;

import lv.nixx.poc.si.ibank.application.domain.*;

public interface PaymentGateway {
	public PaymentResponseList sendTransaction(PaymentList paymentList);
}
