package lv.nixx.poc.actuator;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ServiceForMonitoring {

    private String status = "Unknown";
    private String message = "Unknown message";
    private String details = "Unknown details";

}
