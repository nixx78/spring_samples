package lv.nixx.poc.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@Autowired
	private JmsTemplate jmsTemplate;

	@JmsListener(destination = "synch.queue.request", containerFactory = "myFactory")
	public void receiveMessage(TextMessage message) throws JMSException {
		Destination jmsReplyTo = message.getJMSReplyTo();

		String request = message.getText();
		String jmsCorrelationID = message.getJMSCorrelationID();
		
		System.out.println("T:" + Thread.currentThread().getName() +  " message [" + message.getText() + "] reply queue [" + jmsReplyTo + "]");
		
		MessageCreator messageCreator = new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage(request + ".response");
				msg.setJMSCorrelationID(jmsCorrelationID);
				return msg;
			}};
		
		jmsTemplate.send(jmsReplyTo, messageCreator);
	}

}
