package lv.nixx.poc.ps.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyDecrypt {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {

        return new StringEncryptor() {
            @Override
            public String encrypt(String s) {
                return s;
            }

            @Override
            public String decrypt(String s) {
                return s + ".decrypted";
            }
        };

    }

}
