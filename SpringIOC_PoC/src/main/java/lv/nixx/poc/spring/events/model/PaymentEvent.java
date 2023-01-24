package lv.nixx.poc.spring.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PaymentEvent {
    private final Long id;
    private final Double amount;
}
