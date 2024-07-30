package lv.nixx.poc.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SayHelloToPrimary extends GenericHelloBean {

    @Autowired
    public SayHelloToPrimary(Customer customer) {
        super(customer);
    }

}
