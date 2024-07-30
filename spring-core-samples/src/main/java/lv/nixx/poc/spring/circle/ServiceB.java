package lv.nixx.poc.spring.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {

    private ServiceA serviceA;

    @Autowired
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public String process() {
        return "Processed by ServiceB:" + serviceA.valueFrom();
    }

    public String valueFrom() {
        return "valueFromB";
    }
}
