package lv.nixx.poc.ps.service;

import lv.nixx.poc.ps.configuration.YmlSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithYmlSettings {

    private final YmlSettings ymlSettings;

    @Autowired
    public ServiceWithYmlSettings(YmlSettings ymlSettings) {
        this.ymlSettings = ymlSettings;
    }


    public YmlSettings getSettingsFromYaml(){
        return ymlSettings;
    }

}
