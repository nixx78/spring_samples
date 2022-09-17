package lv.nixx.poc.saml.provider;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.saml2.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDetailsWithAssertion extends User {

    private final Map<String, String> attributes;

    public UserDetailsWithAssertion(String username, String password, Map<String, String> attributes) {
        super(username, "{noop}" + password, List.of(new SimpleGrantedAuthority("ROLE")));
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {
        return attributes.entrySet().stream().map(e -> {
            String value = e.getValue();
            List<Object> lst;
            if (value.contains(",")) {
                lst = Stream.of(value.split(",")).collect(Collectors.toList());
            } else {
                lst = List.of(value);
            }
            return new Attribute()
                    .setName(e.getKey())
                    .setValues(lst);

        }).collect(Collectors.toList());
    }

}
