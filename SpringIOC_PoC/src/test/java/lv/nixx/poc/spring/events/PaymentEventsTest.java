package lv.nixx.poc.spring.events;

import lv.nixx.poc.spring.ioc.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

class PaymentEventsTest {

    @Test
    void sendAndCheckEvents() throws InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration.class);
        ctx.start();

        PaymentService paymentService = ctx.getBean(PaymentService.class);

        paymentService.sendPayment(System.currentTimeMillis(), 10.20);

        ctx.stop();
        TimeUnit.SECONDS.sleep(1);
    }


}
