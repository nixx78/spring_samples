package lv.nixx.poc.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

    //https://stackoverflow.com/questions/45867724/how-to-connect-with-an-external-online-ldap-server-using-spring-boot
    //TODO https://www.forumsys.com/tutorials/integration-how-to/ldap/online-ldap-test-server/

    //TODO Create own Auth Service, create group -> user role mapping to custom object.
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchFilter("(uid={0})")
                .userSearchBase("dc=example,dc=com")
                .groupSearchBase("ou=mathematicians,dc=example,dc=com")
                .groupSearchFilter("cn={0}")
                .contextSource()
                .url("ldap://ldap.forumsys.com")
                .port(389)
                .managerDn("cn=read-only-admin,dc=example,dc=com")
                .managerPassword("password");
    }

}
