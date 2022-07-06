package lv.nixx.poc.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.nixx.poc.security.model.PaymentOperation;
import lv.nixx.poc.security.model.PaymentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static lv.nixx.poc.security.model.PaymentOperation.ADD;
import static lv.nixx.poc.security.model.PaymentOperation.UPDATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails("admin")
    void paymentFromAdminProcessSuccess() throws Exception {
        sendAndCheckRequest("id.1000", ADD, HttpStatus.OK, "Payment:id.1000 processed");
    }

    @Test
    @WithUserDetails("simple_user")
    void checkUserRightsProcessing() {
        assertAll(
                () -> sendAndCheckRequest("id.1000", ADD, HttpStatus.FORBIDDEN, "For user [simple_user] operation [ADD] not allowed"),
                () -> sendAndCheckRequest("id.7777", UPDATE, HttpStatus.OK, "Payment:id.7777 processed")
        );

    }

    private void sendAndCheckRequest(String id, PaymentOperation operation, HttpStatus expectedStatus, String expectedResponse) throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest(id, operation);

        mockMvc.perform(post("/payment")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(paymentRequest)))
                .andExpect(status().is(expectedStatus.value()))
                .andExpect(content().string(expectedResponse));
    }

}