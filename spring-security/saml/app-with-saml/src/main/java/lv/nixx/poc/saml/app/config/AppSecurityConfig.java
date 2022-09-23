package lv.nixx.poc.saml.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class AppSecurityConfig {

    //TODO https://backendstory.com/spring-security-authentication-architecture-explained-in-depth/

    @Bean
    @Autowired
    public SecurityFilterChain config(HttpSecurity http,
                                      RelyingPartyRegistrationRepository relyingPartyRegistrationRepository,
                                      AuthenticationSuccessHandler successHandler,
                                      CustomResponseAuthenticationConverter customResponseAuthenticationConverter

    ) throws Exception {
        Converter<HttpServletRequest, RelyingPartyRegistration> relyingPartyRegistrationResolver =
                new DefaultRelyingPartyRegistrationResolver(relyingPartyRegistrationRepository);

        Saml2MetadataFilter filter = new Saml2MetadataFilter(
                relyingPartyRegistrationResolver,
                new OpenSamlMetadataResolver());

        // This class is deprecated, but you have to use it if you're using OpenSAML < 4.0
        OpenSamlAuthenticationProvider authenticationProvider = new OpenSamlAuthenticationProvider();
        authenticationProvider.setAssertionValidator(OpenSamlAuthenticationProvider.createDefaultAssertionValidator());
        http
                .saml2Login(saml2 ->
                        saml2.authenticationManager(new ProviderManager(authenticationProvider))
                                .successHandler(successHandler)

                )
                .addFilterBefore(filter, Saml2WebSsoAuthenticationFilter.class)
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/endpointForAdmin").hasRole("ADMIN")
                .antMatchers("/endpointForAll").hasAnyRole("RO", "MANAGER")
                .antMatchers("/openEndpoint").permitAll()
                .anyRequest().authenticated();

        authenticationProvider.setResponseAuthenticationConverter(customResponseAuthenticationConverter.createResponseAuthenticationConverter());


        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(@Value("${app.authentication.success-path}") String successPath) {
        return new SimpleUrlAuthenticationSuccessHandler(successPath);
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(@Value("${app.authentication.failure-path}") String failurePath) {
        return new SimpleUrlAuthenticationFailureHandler(failurePath);
    }

}