package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.service.ServiceWithMethodLevelPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
public class MethodLevelPermissionsController {

    private final ServiceWithMethodLevelPermissions service;

    @Autowired
    public MethodLevelPermissionsController(ServiceWithMethodLevelPermissions service) {
        this.service = service;
    }

    @GetMapping("/adminEndpoint")
    public String endpointForAdmin() {
        return service.methodForAdmin();
    }

    @GetMapping("/userEndpoint")
    public String endpointForUser() {
        return service.methodForUser();
    }

    @GetMapping("/endpointForAll")
    public String allEndpoint() {
        return service.methodForAll();
    }
}
