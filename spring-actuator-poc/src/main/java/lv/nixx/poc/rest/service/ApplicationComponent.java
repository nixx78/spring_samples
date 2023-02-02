package lv.nixx.poc.rest.service;

import org.springframework.stereotype.Service;

@Service
public class ApplicationComponent {

    public String getStatusMessage() {
        return "Component1: status message: " + System.currentTimeMillis();
    }

}
