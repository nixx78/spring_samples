package lv.nixx.poc.security.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class SomeService {

    @Secured("ROLE_ADMIN")
    public String methodForAdmin() {
        return "Success:methodForAdmin";
    }

}
