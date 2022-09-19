package lv.nixx.poc.saml.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.saml2.provider.service.authentication.OpenSamlAuthenticationProvider;
import org.springframework.security.saml2.provider.service.metadata.OpenSamlMetadataResolver;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.servlet.filter.Saml2WebSsoAuthenticationFilter;
import org.springframework.security.saml2.provider.service.web.DefaultRelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.Saml2MetadataFilter;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http, @Autowired RelyingPartyRegistrationRepository relyingPartyRegistrationRepository) throws Exception {
        Converter<HttpServletRequest, RelyingPartyRegistration> relyingPartyRegistrationResolver =
                new DefaultRelyingPartyRegistrationResolver(relyingPartyRegistrationRepository);

        Saml2MetadataFilter filter = new Saml2MetadataFilter(
                relyingPartyRegistrationResolver,
                new OpenSamlMetadataResolver());

        // This class is deprecated, but you have to use it if you're using OpenSAML < 4.0
        OpenSamlAuthenticationProvider authenticationProvider = new OpenSamlAuthenticationProvider();
        authenticationProvider.setAssertionValidator(OpenSamlAuthenticationProvider.createDefaultAssertionValidator());
        authenticationProvider.setResponseAuthenticationConverter(CustomResponseAuthenticationConverter.createResponseAuthenticationConverter());
        http
                .saml2Login(saml2 -> saml2.authenticationManager(new ProviderManager(authenticationProvider)))
                .addFilterBefore(filter, Saml2WebSsoAuthenticationFilter.class)
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/**").authenticated();

        return http.build();
    }

}