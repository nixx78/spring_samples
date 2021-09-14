package lv.nixx.poc.ps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;

@Service
public class ApplicationRestartService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationRestartService.class);

    private final RestartEndpoint restartEndpoint;

    @Autowired
    public ApplicationRestartService(RestartEndpoint restartEndpoint) {
        this.restartEndpoint = restartEndpoint;
    }

    public void restart() {
        LOG.info("Try to restart application");
        restartEndpoint.restart();
    }

}
