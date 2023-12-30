package lv.nixx.poc.spring.inheritance;

import org.springframework.stereotype.Service;

@Service
public class ConcreteServiceBeta extends GenericService {
    public ConcreteServiceBeta() {
        super("ConcreteServiceBeta.param");
    }
}
