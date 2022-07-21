package lv.nixx.poc.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccessToActionChecker {

    final Map<String, String> actionToRoleMapping = Map.of(
            "action1", "ROLE_ADMIN",
            "action2", "ROLE_SIMPLE_USER"
    );

    public boolean checkUserHasAccessToAction(Authentication authentication, String action) {
        return authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet())
                .contains(actionToRoleMapping.get(action));
    }

}