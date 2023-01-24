package lv.nixx.poc.spring.events;

import lv.nixx.poc.spring.events.model.CustomApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


/*
По умолчанию, Spring events выполняются синхронно в одном потоке.
Пример кода для асинхронного выполнения показан ниже.
@Configuration
public class AsynchronousSpringEventsConfig {
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster =
                new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}
*/

@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final String message, boolean success) {
        System.out.println("Publishing app event [" + message + "] [" + System.currentTimeMillis() + "] is success [" + success + "]");
        CustomApplicationEvent customSpringEvent = new CustomApplicationEvent(this, message, success);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

}
