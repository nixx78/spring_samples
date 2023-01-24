package lv.nixx.poc.spring.events.listener;

import lv.nixx.poc.spring.events.model.PaymentEvent;
import lv.nixx.poc.spring.events.model.ResponseToPayment;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @EventListener
    public ResponseToPayment paymentProcessor(PaymentEvent event) {
        System.out.println("Payment:" + event + " processed");
        // В данном случае, порождается новое событие, которое будет перехваченно в этомже классе
        return new ResponseToPayment(event.getId(), "success");
    }

    @EventListener
    public void paymentResponseProcessor(ResponseToPayment event) {
        System.out.println("Payment response:" + event + " processed");
    }

}
