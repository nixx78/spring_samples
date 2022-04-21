package lv.nixx.poc.ps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithDecryptedProperties {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceWithDecryptedProperties.class);

    public ServiceWithDecryptedProperties(@Value("${decrypted.property.1}") String p1, @Value("${decrypted.property.2}") String p2) {
        LOG.info("Decrypted properties, p1 [{}] p2 [{}]", p1, p2);
    }
}
