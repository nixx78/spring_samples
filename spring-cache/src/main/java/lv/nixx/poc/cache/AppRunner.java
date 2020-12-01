package lv.nixx.poc.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final CustomerRepository customerRepository;

    public AppRunner(CustomerRepository bookRepository) {
        this.customerRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        logger.info(".... Fetching customers");
        logger.info("1L -->" + customerRepository.getById(1L));
        logger.info("100L -->" + customerRepository.getById(100L));
        logger.info("200L -->" + customerRepository.getById(200L));
    }

}