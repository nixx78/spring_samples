package lv.nixx.poc.spring.inheritance;

import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    public String externalCall(String p) {
        return p + ":external";
    }

}
