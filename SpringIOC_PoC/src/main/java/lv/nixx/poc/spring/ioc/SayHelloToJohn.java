package lv.nixx.poc.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("beanWithJohn")
public class SayHelloToJohn extends GenericHelloBean {
	
	@Autowired
	public SayHelloToJohn(@Qualifier("john") Customer customer) {
		super(customer);
	}

}
