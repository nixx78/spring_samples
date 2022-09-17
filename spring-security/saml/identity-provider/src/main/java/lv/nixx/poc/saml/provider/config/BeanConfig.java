package lv.nixx.poc.saml.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.saml.provider.SamlServerConfiguration;
import org.springframework.security.saml.provider.identity.AssertionEnhancer;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderServerBeanConfiguration;

@Configuration
public class BeanConfig extends SamlIdentityProviderServerBeanConfiguration {

    private final AppConfig config;
    private final UserDetailsProvider userDetailsProvider;

    public BeanConfig(AppConfig config, UserDetailsProvider userDetailsProvider) {
        this.config = config;
        this.userDetailsProvider = userDetailsProvider;
    }

    @Override
    protected SamlServerConfiguration getDefaultHostSamlServerConfiguration() {
        return config;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(userDetailsProvider.getUsers());
    }

    @Bean(name = "samlAssertionEnhancer")
    public AssertionEnhancer samlAssertionEnhancer() {
        return assertion -> assertion.setAttributes(userDetailsProvider.getAttributes(assertion.getSubject().getPrincipal().getValue()));

    }
}
