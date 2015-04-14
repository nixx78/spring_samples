package lv.nixx.poc.si.ibank.application.interceptor;

import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.integration.history.MessageHistory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class MessageHistoryLogger extends ChannelInterceptorAdapter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
		
		// FIXME think how to log history in case of error
		if ( logger.isDebugEnabled() ) {
			Iterator<Properties> historyIterator = message.getHeaders().get(MessageHistory.HEADER_NAME, MessageHistory.class).iterator();
			logger.debug("message history:");
			while (historyIterator.hasNext()) {
				Properties properties = historyIterator.next();
				logger.debug(properties.toString());
			}
		}
		return message;
	}
	
}