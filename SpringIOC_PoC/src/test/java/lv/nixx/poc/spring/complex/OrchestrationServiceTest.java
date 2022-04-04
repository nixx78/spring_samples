package lv.nixx.poc.spring.complex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class OrchestrationServiceTest {

    @Autowired
    private OrchestrationService service;

    @Test
    public void processTest() {

        Map<Source, Dto> process = service.process("request.value");
        assertEquals(3, process.size());

        process.entrySet().forEach(
                System.out::println
        );


    }

}
