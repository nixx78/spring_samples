package lv.nixx.poc.saml.app.config;

import lv.nixx.poc.saml.app.AppUser;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.schema.*;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeStatement;
import org.opensaml.saml.saml2.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.saml2.provider.service.authentication.OpenSaml4AuthenticationProvider;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomResponseAuthenticationConverter {

    private final RoleMapper roleMapper;

    @Autowired
    public CustomResponseAuthenticationConverter(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public Converter<OpenSaml4AuthenticationProvider.ResponseToken, Saml2Authentication> createResponseAuthenticationConverter() {

        return (responseToken) -> {
            Saml2AuthenticationToken token = responseToken.getToken();
            Response response = responseToken.getResponse();

            AttributesWrapper attributesWrapper = new AttributesWrapper(CollectionUtils.firstElement(response.getAssertions()));
            String username = attributesWrapper.getUsername();




            String registrationId = token.getRelyingPartyRegistration().getRegistrationId();

            List<GrantedAuthority> roles = attributesWrapper.getAttributes("ROLES")
                    .stream()
                    .map(String::valueOf)
                    .map(roleMapper::mapRole)
                    .collect(Collectors.toList());

            String displayName = attributesWrapper.getAttribute("FNAME") + "," + attributesWrapper.getAttribute("LNAME");

            CustomAuthenticationPrincipal principal = new CustomAuthenticationPrincipal(username, attributesWrapper.attribs, new AppUser(username, displayName, roles));
            principal.setRelyingPartyRegistrationId(registrationId);

            return new Saml2Authentication(principal, token.getSaml2Response(), roles);
        };
    }

    static class AttributesWrapper {

        private final Map<String, List<Object>> attribs;
        private final Assertion assertion;

        AttributesWrapper(Assertion assertion) {
            this.attribs = getAssertionAttributes(assertion);
            this.assertion = assertion;
        }

        String getUsername() {
            return assertion.getSubject().getNameID().getValue();
        }

        List<String> getAttributes(String key) {
            return attribs.getOrDefault(key, Collections.emptyList())
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        }

        String getAttribute(String key) {
            return attribs.getOrDefault(key, Collections.emptyList())
                    .stream()
                    .findFirst()
                    .map(String::valueOf)
                    .orElse(null);
        }

        private Map<String, List<Object>> getAssertionAttributes(Assertion assertion) {
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

        private Object getXmlObjectValue(XMLObject xmlObject) {
            if (xmlObject instanceof XSAny) {
                return ((XSAny) xmlObject).getTextContent();
            } else if (xmlObject instanceof XSString) {
                return ((XSString) xmlObject).getValue();
            } else if (xmlObject instanceof XSInteger) {
                return ((XSInteger) xmlObject).getValue();
            } else if (xmlObject instanceof XSURI) {
                return ((XSURI) xmlObject).getURI();
            } else if (xmlObject instanceof XSBoolean) {
                XSBooleanValue xsBooleanValue = ((XSBoolean) xmlObject).getValue();
                return xsBooleanValue != null ? xsBooleanValue.getValue() : null;
            } else {
                return xmlObject instanceof XSDateTime ? Instant.ofEpochMilli(Objects.requireNonNull(((XSDateTime) xmlObject).getValue()).getEpochSecond()) : null;
            }
        }


    }


}
