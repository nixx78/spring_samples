package lv.nixx.poc.jms.requestresponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class RequestResponseSynch {
	
	private static final String SYNCH_QUEUE_RESPONSE = "synch.queue.response";
	private Map<String, BlockingQueue<String>> map = new ConcurrentHashMap<>();
	private JmsTemplate jmsTemplate;
	
	@Autowired	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}


	@JmsListener(destination = SYNCH_QUEUE_RESPONSE, containerFactory = "myFactory")
	public void responseCome(TextMessage message) throws Exception {
		
		String correlationID = message.getJMSCorrelationID();
		System.out.println("Response for Id [" + correlationID + "] come, message [" + message.getText() + "]");
		
		if (map.containsKey(correlationID)) {
			BlockingQueue<String> blockingQueue = map.get(correlationID);
			blockingQueue.put(message.getText());
		}
	}

	
	public String sendSynchRequest(String message) throws Exception {
		String id = UUID.randomUUID().toString();
		
		map.put(id, new ArrayBlockingQueue<>(1));
		
		jmsTemplate.send("synch.queue.request", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage(message);
				msg.setJMSCorrelationID(id);
				return msg;
			}
		});
		
		return waitForResponse(id);
	}
	
	public String waitForResponse(String id) throws Exception {
		if (map.containsKey(id)) {
			BlockingQueue<String> q = map.get(id);
			return q.poll(5, TimeUnit.SECONDS);
		}
		return null;
	}

}
