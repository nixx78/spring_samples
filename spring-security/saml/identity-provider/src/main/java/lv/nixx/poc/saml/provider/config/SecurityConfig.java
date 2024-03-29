package lv.nixx.poc.saml.provider.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityConfiguration;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityDsl;

@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	@Order(1)
	public static class SamlSecurity extends SamlIdentityProviderSecurityConfiguration {

		private final AppConfig appConfig;
		private final BeanConfig beanConfig;

		public SamlSecurity(BeanConfig beanConfig, @Qualifier("appConfig") AppConfig appConfig) {
			super("/saml/idp/", beanConfig);
			this.appConfig = appConfig;
			this.beanConfig = beanConfig;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			super.configure(http);
			
			http.userDetailsService(beanConfig.userDetailsService())
				.formLogin();
			
			http.apply(SamlIdentityProviderSecurityDsl.identityProvider())
				.configure(appConfig);
		}
	}

	@Configuration
	public static class AppSecurity extends WebSecurityConfigurerAdapter {

		private final BeanConfig beanConfig;

		public AppSecurity(BeanConfig beanConfig) {
			this.beanConfig = beanConfig;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/**").authenticated()
				.and()
				.userDetailsService(beanConfig.userDetailsService()).formLogin();
		}
	}
}
