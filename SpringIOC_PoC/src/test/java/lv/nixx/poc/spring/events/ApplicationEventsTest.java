package lv.nixx.poc.spring.events;

import lv.nixx.poc.spring.ioc.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

class ApplicationEventsTest {

    @Test
    void sendAndCheckEvents() throws InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Configuration.class);

        EventPublisher eventPublisher = ctx.getBean(EventPublisher.class);

        eventPublisher.publishCustomEvent("Unsuccessful event", false);
        eventPublisher.publishCustomEvent("Successful event", true);

        TimeUnit.SECONDS.sleep(1);
    }


}
