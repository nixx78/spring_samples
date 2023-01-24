package lv.nixx.poc.spring.events.listener;

import lv.nixx.poc.spring.events.model.CustomApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FirstEventListener implements ApplicationListener<CustomApplicationEvent> {
    @Override
    public void onApplicationEvent(CustomApplicationEvent event) {
        System.out.println(Thread.currentThread().getName() + ": Received spring custom event (FirstListener) - " + event.getMessageToSender());
    }

}
