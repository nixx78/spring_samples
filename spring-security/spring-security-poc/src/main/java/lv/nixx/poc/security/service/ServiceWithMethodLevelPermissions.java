package lv.nixx.poc.security.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;


//https://en.wikipedia.org/wiki/JSR_250
@Service
public class ServiceWithMethodLevelPermissions {

    @RolesAllowed("ROLE_ADMIN")
    public String methodForAdmin() {
        return "Success:methodForAdmin";
    }

    @PermitAll
    public String methodForAll() {
        return "Success:methodForAll";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public String methodForUser() {
        return "Success:methodForUser";
    }

}
