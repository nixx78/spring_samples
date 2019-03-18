package lv.nixx.poc.jms.commandline;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.nixx.poc.jms.commandline.domain.Event;
import lv.nixx.poc.jms.commandline.domain.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;


@Component
public class CommandLineApplication {

    private static final Logger LOG = LoggerFactory.getLogger(CommandLineApplication.class);

    private JmsTemplate jmsTemplate;
    private ObjectMapper om;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    public void sendMessageAndLogResponse(String message) throws JMSException, JsonProcessingException {

        Event event = new Event(System.currentTimeMillis(), message, EventType.INTERNAL);
        jmsTemplate.convertAndSend("event.queue", event);

        String jsonEvent = om.writeValueAsString(event);

        MessageCreator msgCreator = session -> session.createTextMessage(jsonEvent);

        jmsTemplate.send("event.text.queue", msgCreator);

        Message sendAndReceive = jmsTemplate.sendAndReceive("synch.event.queue", msgCreator);

        LOG.info("Synch response to request [{}}", ((TextMessage) sendAndReceive).getText());
    }


}
