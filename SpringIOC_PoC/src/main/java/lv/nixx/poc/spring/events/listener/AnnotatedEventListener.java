package lv.nixx.poc.spring.events.listener;

import lv.nixx.poc.spring.events.model.CustomApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// При использование @EventListener возможно указывать условие (SpEL), при котором данный листенер вызовется
@Component
public class AnnotatedEventListener {

    @EventListener
    public void allEventHandler(CustomApplicationEvent event) {
        System.out.println("\t\tAll event handler (AnnotatedListener) - " + event.getMessageToSender() + " result: " + event.isSuccess());
    }

    @EventListener(condition = "#event.success")
    public void successEventHandler(CustomApplicationEvent event) {
        System.out.println("\t\tSuccessEventHandler (AnnotatedListener) - " + event.getMessageToSender() + " result: " + event.isSuccess());
    }

    @EventListener(condition = "!#event.success")
    public void unsuccessfulHandler(CustomApplicationEvent event) {
        System.out.println("\t\tUnsuccessfulEventHandler (AnnotatedListener) - " + event.getMessageToSender() + " result: " + event.isSuccess());
    }

}
