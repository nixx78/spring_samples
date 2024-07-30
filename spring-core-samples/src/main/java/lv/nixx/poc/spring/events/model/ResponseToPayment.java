package lv.nixx.poc.spring.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ResponseToPayment {
    private final Long id;
    private final String status;
}
