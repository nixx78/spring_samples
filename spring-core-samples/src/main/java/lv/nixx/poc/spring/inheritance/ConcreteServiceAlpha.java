package lv.nixx.poc.spring.inheritance;

import org.springframework.stereotype.Service;

@Service
public class ConcreteServiceAlpha extends GenericService {
    public ConcreteServiceAlpha() {
        super("ConcreteServiceAlpha.param");
    }
}
