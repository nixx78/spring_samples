package lv.nixx.poc.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PaymentRequest {
    String id;
    PaymentOperation operation;

    public PaymentRequest(@JsonProperty("id") String id, @JsonProperty("operation") PaymentOperation operation) {
        this.id = id;
        this.operation = operation;
    }
}
