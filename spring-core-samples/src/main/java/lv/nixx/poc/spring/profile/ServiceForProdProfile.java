package lv.nixx.poc.spring.profile;

import lv.nixx.poc.spring.complex.Dto;
import lv.nixx.poc.spring.complex.Source;
import lv.nixx.poc.spring.complex.service.IService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("serviceForProfile")
@Profile("prod")
public class ServiceForProdProfile implements IService {
    @Override

    public Dto process(String request) {
        return new Dto("ServiceForProdProfile." + request, "ServiceForProdProfile." + request);
    }

    @Override
    public Source getSource() {
        return null;
    }

}
