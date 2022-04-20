package lv.nixx.poc.spring.complex;


import lv.nixx.poc.spring.complex.service.IService;
import lv.nixx.poc.spring.profile.ConfigWithMultipleProfiles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigWithMultipleProfiles.class)
@ActiveProfiles("DEV")
class DevProfileTest {

    @Autowired
    @Qualifier("pService")
    private IService service;

    @Test
    void processTest() {
        Dto response = service.process("request");

        assertEquals("Dev.Value.request", response.getField1());
    }

}
