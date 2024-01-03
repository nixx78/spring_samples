package lv.nixx.poc.spring.complex;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ComplexAppConfig.class)
class OrchestrationAnotherAutowireTest {

    @Autowired
    private OrchestrationAnotherAutowire service;

    @Test
    void processTest() {

        Map<Source, Dto> process = service.process("request.value");
        assertEquals(3, process.size());

        process.entrySet().forEach(
                System.out::println
        );


    }

}
