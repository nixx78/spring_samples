package lv.nixx.poc.spring.profile;

import lv.nixx.poc.spring.complex.Dto;
import lv.nixx.poc.spring.complex.service.IService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigWithMultipleProfiles.class)
@ActiveProfiles("prod")
class ProdProfileTest {

    @Autowired
    @Qualifier("pService")
    private IService service;

    @Autowired
    @Qualifier("serviceForProfile")
    private IService serviceForProfile;

    @Test
    void processTest() {
        Dto r1 = service.process("request");
        Dto r2 = serviceForProfile.process("request");

        assertAll(
                () -> assertEquals("Prod.Value.request", r1.getField1()),
                () -> assertEquals("ServiceForProdProfile.request", r2.getField1())
        );

    }

}
