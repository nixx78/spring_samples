package lv.nixx.poc.saml.app.config;

import lv.nixx.poc.saml.app.AppUser;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.schema.*;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeStatement;
import org.opensaml.saml.saml2.core.Response;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.saml2.provider.service.authentication.DefaultSaml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.OpenSamlAuthenticationProvider;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticationToken;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class CustomResponseAuthenticationConverter {

    private static final Map<String, SimpleGrantedAuthority> rolesMapping = Map.of(
            "RES_MANAGER", new SimpleGrantedAuthority("ROLE_MANAGER"),
            "RES_READ_ONLY", new SimpleGrantedAuthority("ROLE_RO"),
            "RES_ADMIN", new SimpleGrantedAuthority("ROLE_ADMIN")
    );

    public static Converter<OpenSamlAuthenticationProvider.ResponseToken, Saml2Authentication> createResponseAuthenticationConverter() {
        return (responseToken) -> {
            Saml2AuthenticationToken token = responseToken.getToken();
            Response response = responseToken.getResponse();

            Assertion assertion = CollectionUtils.firstElement(response.getAssertions());
            String username = assertion.getSubject().getNameID().getValue();
            Map<String, List<Object>> attributes = getAssertionAttributes(assertion);

            DefaultSaml2AuthenticatedPrincipal principal = new DefaultSaml2AuthenticatedPrincipal(username, attributes);

            String registrationId = token.getRelyingPartyRegistration().getRegistrationId();
            principal.setRelyingPartyRegistrationId(registrationId);

            List<GrantedAuthority> roles = attributes.get("ROLES")
                    .stream()
                    .map(String::valueOf)
                    .map(t -> rolesMapping.getOrDefault(t, new SimpleGrantedAuthority("ROLE_UNKNOWN")))
                    .collect(Collectors.toList());

            Saml2Authentication auth = new Saml2Authentication(principal, token.getSaml2Response(), roles);
            auth.setDetails(new AppUser(username, roles));

            return auth;
        };
    }

    private static Map<String, List<Object>> getAssertionAttributes(Assertion assertion) {
        Map<String, List<Object>> attributeMap = new LinkedHashMap<>();

        for (AttributeStatement attributeStatement : assertion.getAttributeStatements()) {

            for (Attribute attribute : attributeStatement.getAttributes()) {
                List<Object> attributeValues = new ArrayList<>();

                for (XMLObject xmlObject : attribute.getAttributeValues()) {
                    Object attributeValue = getXmlObjectValue(xmlObject);
                    if (attributeValue != null) {
                        attributeValues.add(attributeValue);
                    }
                }

                attributeMap.put(attribute.getName(), attributeValues);
            }
        }

        return attributeMap;
    }

    private static Object getXmlObjectValue(XMLObject xmlObject) {
        if (xmlObject instanceof XSAny) {
            return ((XSAny) xmlObject).getTextContent();
        } else if (xmlObject instanceof XSString) {
            return ((XSString) xmlObject).getValue();
        } else if (xmlObject instanceof XSInteger) {
            return ((XSInteger) xmlObject).getValue();
        } else if (xmlObject instanceof XSURI) {
            return ((XSURI) xmlObject).getValue();
        } else if (xmlObject instanceof XSBoolean) {
            XSBooleanValue xsBooleanValue = ((XSBoolean) xmlObject).getValue();
            return xsBooleanValue != null ? xsBooleanValue.getValue() : null;
        } else {
            return xmlObject instanceof XSDateTime ? Instant.ofEpochMilli(Objects.requireNonNull(((XSDateTime) xmlObject).getValue()).getMillis()) : null;
        }
    }

}
