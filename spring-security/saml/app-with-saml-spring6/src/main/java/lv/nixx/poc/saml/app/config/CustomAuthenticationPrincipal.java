package lv.nixx.poc.saml.app.config;

import lombok.Getter;
import lv.nixx.poc.saml.app.AppUser;
import org.springframework.security.saml2.provider.service.authentication.DefaultSaml2AuthenticatedPrincipal;

import java.util.List;
import java.util.Map;

public class CustomAuthenticationPrincipal extends DefaultSaml2AuthenticatedPrincipal {

    @Getter
    private final AppUser appUser;

    public CustomAuthenticationPrincipal(String name, Map<String, List<Object>> attributes, AppUser appUser) {
        super(name, attributes);
        this.appUser = appUser;
    }
}
