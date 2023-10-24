package lv.nixx.poc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring().antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .userDetailsService(userDetailsService)
                .authorizeRequests()
                .antMatchers("/method/**").permitAll()

                .antMatchers("/urlBased/basicSecured").hasAnyRole("ADMIN", "SIMPLE_USER")
                .antMatchers("/urlBased/secured").hasRole("ADMIN")
                .antMatchers("/urlBased/home").permitAll()
                .antMatchers("/urlBased/twoRolesRequired").access("hasRole('ADMIN') and hasRole('POWER')")

                .antMatchers(GET, "/urlBased/action").hasRole("SIMPLE_USER")
                .antMatchers(POST, "/urlBased/action").hasRole("ADMIN")

                .antMatchers("/urlBased/process/{name}")
                .access("@accessToActionChecker.checkUserHasAccessToAction(authentication,#name)")

//                .regexMatchers("\\/urlBased\\/process\\?action=power(&.*|$)").hasRole("POWER")
//                .regexMatchers("\\/urlBased\\/process\\?action=power(&.*|$)").hasRole("POWER")

                .antMatchers("/userInfo").authenticated()
                .antMatchers("/userDetails").authenticated()

                .antMatchers("/view/*").authenticated()
                .antMatchers("/payment/*").authenticated()

                .antMatchers("/endpointWithHandler").hasAnyRole("ADMIN")

                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/swagger-ui.html", true)
                //TODO Create handler sample there
                //.successHandler()
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                        .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    static class CustomAccessDeniedHandler implements AccessDeniedHandler {

        // This class handle event, when Authenticated user try to access to not allowed resource
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
            response.setHeader("CustomAccessDeniedHandler_Error", accessDeniedException.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

    static class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        // This class handle event,when user login fail: wrong user/password
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
            int returnStatus = HttpStatus.UNAUTHORIZED.value();

            if (exception instanceof InternalAuthenticationServiceException) {
                returnStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
            }

            response.setHeader("CustomAuthenticationFailureHandler_Error", exception.getMessage());
            response.setStatus(returnStatus);
        }
    }

}
