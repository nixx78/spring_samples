package lv.nixx.poc.spring.rest;

import lv.nixx.poc.spring.MyProcessorBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrototypeSampleController {

    private final ObjectProvider<MyProcessorBean> objectProvider;

    public PrototypeSampleController(ObjectProvider<MyProcessorBean> objectProvider) {
        this.objectProvider = objectProvider;
    }

    @GetMapping("/process/prototype")
    public String processUsingPrototype(@RequestParam String request) {
        return objectProvider.getObject().process(request);
    }

}
