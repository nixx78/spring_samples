package lv.nixx.poc.ps.controller;

import lv.nixx.poc.ps.Response;
import lv.nixx.poc.ps.service.ServiceWithJavaConfiguration;
import lv.nixx.poc.ps.service.ServiceWithEnvironmentValue;
import lv.nixx.poc.ps.service.ServiceWithValueProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ServiceController {

    private final ServiceWithValueProperties serviceWithValueProperties;
    private final ServiceWithJavaConfiguration serviceWithJavaConfiguration;
    private final ServiceWithEnvironmentValue serviceWithEnvironmentValue;

    @Autowired
    public ServiceController(ServiceWithValueProperties serviceWithValueProperties,
                             ServiceWithJavaConfiguration serviceWithJavaConfiguration,
                             ServiceWithEnvironmentValue serviceWithEnvironmentValue) {
        this.serviceWithValueProperties = serviceWithValueProperties;
        this.serviceWithJavaConfiguration = serviceWithJavaConfiguration;
        this.serviceWithEnvironmentValue = serviceWithEnvironmentValue;
    }


    @GetMapping("/process")
    public Response callServices() {
        return new Response()
                .setTimestamp(new Date())
                .setPropertyFromValue(serviceWithValueProperties.process())
                .setPropertyFromApplicationConfig(serviceWithJavaConfiguration.process())
                .setPropertyFromEnvironment(serviceWithEnvironmentValue.process())
                .setPageSize(serviceWithJavaConfiguration.getPageSize())
                .setTotalRecordCount(serviceWithJavaConfiguration.getTotalRecordCount())
                .setPermission(serviceWithJavaConfiguration.getPermission());
    }

}
