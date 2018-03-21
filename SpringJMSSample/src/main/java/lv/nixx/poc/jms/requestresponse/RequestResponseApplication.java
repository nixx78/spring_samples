
package lv.nixx.poc.jms.requestresponse;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
@ComponentScan
public class RequestResponseApplication {
	
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}


	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(RequestResponseApplication.class, args);

		RequestResponseSynch synchRequest = context.getBean(RequestResponseSynch.class);

		// One call sample
		// String response = synchRequest.sendSynchRequest("Message1");
		// System.out.println("Synch response: " + response);

		ExecutorService pool = Executors.newFixedThreadPool(5);

		List<Future<String>> futures = Stream.iterate(1, i -> i + 1)
				.map(i -> new Request(synchRequest, "Message: " + i))
				.limit(10).map(pool::submit)
				.collect(Collectors.toList());

		for (Future<String> future : futures) {
			System.out.println("Synch response [" + future.get() + "]");
		}

		System.out.println("Existing...");
		pool.shutdown();
		context.close();
	}

	static class Request implements Callable<String> {

		private RequestResponseSynch requester;
		private String message;

		Request(RequestResponseSynch requester, String message) {
			this.requester = requester;
			this.message = message;
		}

		@Override
		public String call() throws Exception {
			return requester.sendSynchRequest(message);
		}

	}

}
