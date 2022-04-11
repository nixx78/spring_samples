package lv.nixx.poc.spring.ioc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = {"name", "surname"})
public class Customer {
    private final String name;
    private final String surname;
}
