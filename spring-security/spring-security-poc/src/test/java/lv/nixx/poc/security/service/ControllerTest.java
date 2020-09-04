package lv.nixx.poc.security.service;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerTest {

    @LocalServerPort
    private int localServerPort;

    @Test
    public void unsecuredUrlTest() {
        final ResponseEntity<String> res = new TestRestTemplate().getForEntity("http://localhost:" + localServerPort + "/home", String.class);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals("Success:home", res.getBody());
    }

    //https://stackoverflow.com/questions/15203485/spring-test-security-how-to-mock-authentication

    @Test
    public void securedUrlTest() {

        final ResponseEntity<String> res = new TestRestTemplate()
                .withBasicAuth("admin", "1")
                .getForEntity("http://localhost:" + localServerPort + "/basicSecured", String.class);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals("Success:basicSecured", res.getBody());
    }


}
