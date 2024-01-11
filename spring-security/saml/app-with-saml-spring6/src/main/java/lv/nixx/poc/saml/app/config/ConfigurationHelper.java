package lv.nixx.poc.saml.app.config;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.saml2.core.Saml2X509Credential;

import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;

public class ConfigurationHelper {

    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();

    public static Saml2X509Credential getSaml2SigningCredential(String privateKeyLocation, String certificateLocation) {
        return getSaml2Credential(privateKeyLocation, certificateLocation, Saml2X509Credential.Saml2X509CredentialType.SIGNING);
    }

    private static Saml2X509Credential getSaml2Credential(String privateKeyLocation, String certificateLocation, Saml2X509Credential.Saml2X509CredentialType credentialType) {
        RSAPrivateKey privateKey = readPrivateKey(privateKeyLocation);
        X509Certificate certificate = readCertificate(certificateLocation);
        return new Saml2X509Credential(privateKey, certificate, credentialType);
    }

    public static Saml2X509Credential getSaml2CredentialWithType(String certificateLocation, Saml2X509Credential.Saml2X509CredentialType credentialType) {
        X509Certificate certificate = readCertificate(certificateLocation);
        return new Saml2X509Credential(certificate, credentialType);
    }


    private static RSAPrivateKey readPrivateKey(String privateKeyLocation) {
        Resource privateKey = resourceLoader.getResource(privateKeyLocation);

        try {
            InputStream inputStream = privateKey.getInputStream();

            RSAPrivateKey rsaPrivateKey;
            try {
                rsaPrivateKey = RsaKeyConverters.pkcs8().convert(inputStream);
            } catch (Throwable t1) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable t2) {
                        t1.addSuppressed(t2);
                    }
                }

                throw t1;
            }

            if (inputStream != null) {
                inputStream.close();
            }

            return rsaPrivateKey;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private static X509Certificate readCertificate(String certificateLocation) {
        Resource certificate = resourceLoader.getResource(certificateLocation);

        try {
            InputStream inputStream = certificate.getInputStream();

            X509Certificate x509Certificate;
            try {
                x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(inputStream);
            } catch (Throwable t1) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var5) {
                        t1.addSuppressed(var5);
                    }
                }

                throw t1;
            }

            if (inputStream != null) {
                inputStream.close();
            }

            return x509Certificate;
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }


}
