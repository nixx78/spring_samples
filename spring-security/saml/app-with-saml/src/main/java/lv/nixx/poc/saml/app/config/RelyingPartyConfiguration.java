package lv.nixx.poc.saml.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.saml2.core.Saml2X509Credential;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;

import static org.springframework.security.saml2.core.Saml2X509Credential.Saml2X509CredentialType.ENCRYPTION;
import static org.springframework.security.saml2.core.Saml2X509Credential.Saml2X509CredentialType.VERIFICATION;

@Configuration
@Profile("saml_manual_config")
public class RelyingPartyConfiguration {

    @Bean
    @Primary
    public RelyingPartyRegistrationRepository getRepository(@Autowired ServerProperties serverProperties) {

        Integer port = serverProperties.getPort();
        String contextPath = serverProperties.getServlet().getContextPath();

        String baseUrl = "http://127.0.0.1:" + port + contextPath;

        String certificateLocation = "classpath:credentials/public.cer";

        Saml2X509Credential saml2SigningCredential = ConfigurationHelper.getSaml2SigningCredential("classpath:credentials/private.key", certificateLocation);

        Saml2X509Credential verification = ConfigurationHelper.getSaml2CredentialWithType(certificateLocation, VERIFICATION);
        Saml2X509Credential encryption = ConfigurationHelper.getSaml2CredentialWithType(certificateLocation, ENCRYPTION);

        String entityId = baseUrl + "/saml2/service-provider-metadata/{registrationId}";
        String assertionConsumerServiceLocation = baseUrl + "/login/saml2/sso/{registrationId}";

        RelyingPartyRegistration relyingPartyConfiguration = RelyingPartyRegistration.withRegistrationId("saml_poc")
                .entityId(entityId)
                .assertionConsumerServiceLocation(assertionConsumerServiceLocation)
                .signingX509Credentials(consumer -> consumer.add(saml2SigningCredential))
                .assertingPartyDetails(details ->
                        details
                                .wantAuthnRequestsSigned(false)
                                .entityId("appwithsaml-entity-id")
                                .singleSignOnServiceLocation("http://localhost:8081/idp-provider/saml/idp/select")
                                .verificationX509Credentials(c -> c.add(verification))
                                .encryptionX509Credentials(c -> c.add(encryption))
                ).build();

        return new InMemoryRelyingPartyRegistrationRepository(relyingPartyConfiguration);
    }

}
