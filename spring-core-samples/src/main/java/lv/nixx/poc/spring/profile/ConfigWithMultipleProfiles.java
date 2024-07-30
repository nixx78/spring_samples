package lv.nixx.poc.spring.profile;

import lv.nixx.poc.spring.complex.Dto;
import lv.nixx.poc.spring.complex.Source;
import lv.nixx.poc.spring.complex.service.IService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "lv.nixx.poc.spring.profile")
public class ConfigWithMultipleProfiles {

    @Bean("pService")
    @Profile("dev")
    public IService serviceForDev() {
        return new IService() {
            @Override
            public Dto process(String request) {
                return new Dto("Dev.Value." + request, "Dev.Value." + request);
            }

            @Override
            public Source getSource() {
                return null;
            }
        };
    }

    @Bean("pService")
    @Profile("prod")
    public IService serviceForProd() {
        return new IService() {
            @Override
            public Dto process(String request) {
                return new Dto("Prod.Value." + request, "Prod.Value." + request);
            }

            @Override
            public Source getSource() {
                return null;
            }
        };
    }

}
