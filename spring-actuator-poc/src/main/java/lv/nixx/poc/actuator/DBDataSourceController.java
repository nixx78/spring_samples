package lv.nixx.poc.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

@RestController
public class DBDataSourceController {

    private static final Logger log = LoggerFactory.getLogger(DBDataSourceController.class);

    private final DataSource alphaDataSource;

    public DBDataSourceController(DataSource alphaDataSource) {
        this.alphaDataSource = alphaDataSource;
    }

    @GetMapping("/test/alpha")
    public void testAlpha() {

        try (Connection connection = alphaDataSource.getConnection()) {
            log.info("Start connection check, connection: {}", connection.toString());
            TimeUnit.SECONDS.sleep(60L);
            log.info("Finish connection check");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
