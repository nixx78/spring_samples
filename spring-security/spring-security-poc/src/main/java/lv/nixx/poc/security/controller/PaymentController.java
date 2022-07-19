package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.model.PaymentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/payment")
    public String processPaymentRequest(@RequestBody PaymentRequest paymentRequest) {
        return "Payment:" + paymentRequest.getId() + " processed";
    }

}
