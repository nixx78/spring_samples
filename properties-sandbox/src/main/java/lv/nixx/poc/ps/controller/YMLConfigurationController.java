package lv.nixx.poc.ps.controller;

import lv.nixx.poc.ps.configuration.YmlSettings;
import lv.nixx.poc.ps.service.ServiceWithYmlSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class YMLConfigurationController {

    private final ServiceWithYmlSettings service;

    @Autowired
    public YMLConfigurationController(ServiceWithYmlSettings service) {
        this.service = service;
    }

    @GetMapping(value = "/ymlConfiguration")
    public Map<String, Object> getYamlConfiguration() {
        YmlSettings s = service.getSettingsFromYaml();

        return Map.of(
                "name", s.getName(),
                "aliases", s.getAliases(),
                "accountCache", s.getAccountCache(),
                "caches", s.getCaches(),
                "mapWithRoles", s.getRoles(),
                "sequence", s.getSequence(),
                "map", s.getMap()
        );
    }

}
