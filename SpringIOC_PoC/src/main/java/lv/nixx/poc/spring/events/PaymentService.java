package lv.nixx.poc.spring.events;

import lv.nixx.poc.spring.events.model.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendPayment(Long id, Double amount) {
        PaymentEvent payment = new PaymentEvent(id, amount);
        System.out.println("Send payment [" + payment + "]");
        applicationEventPublisher.publishEvent(payment);
    }

}
