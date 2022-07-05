package lv.nixx.poc.security.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void unsecuredUrlTest() {
        final ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:" + localServerPort + "/home", String.class);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals("Success:home", res.getBody());
    }

}
