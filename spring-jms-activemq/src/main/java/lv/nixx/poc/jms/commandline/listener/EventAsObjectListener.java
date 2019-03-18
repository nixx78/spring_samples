package lv.nixx.poc.jms.commandline.listener;

import lv.nixx.poc.jms.commandline.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EventAsObjectListener {

	private static final Logger LOG  = LoggerFactory.getLogger(EventAsObjectListener.class);

	@JmsListener(concurrency = "1", destination = "event.queue", containerFactory = "containerFactory")
	public void receiveMessage(Event message) {
		LOG.info("Event as object come {}", message);
	}

}
