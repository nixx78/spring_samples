package lv.nixx.poc.saml.provider.config;

import lv.nixx.poc.saml.provider.UserDetailsWithAssertion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.saml.saml2.attribute.Attribute;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserDetailsProvider {

    private final Map<String, UserDetailsWithAssertion> users = Map.of(
            "manager", new UserDetailsWithAssertion("manager", "p",
                    Map.of(
                            "FNAME", "fManagerName",
                            "LNAME", "lManagerName",
                            "ROLES", "RES_MANAGER, RES_READ_ONLY"
                    )),
            "admin", new UserDetailsWithAssertion("admin", "p",
                    Map.of(
                            "FNAME", "fAdmin",
                            "LNAME", "lAdmin",
                            "ROLES", "RES_ADMIN"
                    ))

    );

    public List<Attribute> getAttributes(String user) {
        return users.get(user).getAttributes();
    }

    public Collection<UserDetails> getUsers() {
        return users.values()
                .stream()
                .map(t -> (UserDetails) t)
                .collect(Collectors.toList());
    }

}
