package lv.nixx.poc.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("beanWithMerry")
public class SayHelloToMarry extends GenericHelloBean {

	@Autowired
	public SayHelloToMarry(@Qualifier("merry") Customer customer) {
		super(customer);
	}

}
