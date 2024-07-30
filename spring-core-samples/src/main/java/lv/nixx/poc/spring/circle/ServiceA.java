package lv.nixx.poc.spring.circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    private ServiceB serviceB;

    // Если использовать конструктор injection то получим: Error creating bean with name 'serviceA': Requested bean is currently in creation: Is there an unresolvable circular reference?
    // Для решения этой проблемы нужно использовать setter injection

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public String process() {
        return "Processed by ServiceA:" + serviceB.valueFrom();
    }

    public String valueFrom() {
        return "valueFromA";
    }

}
