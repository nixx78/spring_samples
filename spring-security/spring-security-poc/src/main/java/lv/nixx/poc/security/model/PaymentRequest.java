package lv.nixx.poc.security.model;

import lombok.Data;

@Data
public class PaymentRequest {
    String id;
    PaymentOperation operation;
}
