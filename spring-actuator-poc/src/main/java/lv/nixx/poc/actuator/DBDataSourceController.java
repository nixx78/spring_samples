package lv.nixx.poc.actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

@RestController
public class DBDataSourceController {

    private final DataSource alphaDataSource;

    public DBDataSourceController(DataSource alphaDataSource) {
        this.alphaDataSource = alphaDataSource;
    }

    @GetMapping("/test/alpha")
    public void testAlpha() throws Exception {
        Connection connection = alphaDataSource.getConnection();
        TimeUnit.SECONDS.sleep(60L);
        connection.close();
    }
}
