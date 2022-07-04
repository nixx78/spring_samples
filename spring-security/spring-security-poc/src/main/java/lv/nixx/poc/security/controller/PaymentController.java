package lv.nixx.poc.security.controller;

import lv.nixx.poc.security.model.PaymentRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @PostMapping("/payment")
    public String processPaymentRequest(@RequestBody PaymentRequest paymentRequest) {
        return "Payment:" + paymentRequest.getId() + " processed";
    }

}
