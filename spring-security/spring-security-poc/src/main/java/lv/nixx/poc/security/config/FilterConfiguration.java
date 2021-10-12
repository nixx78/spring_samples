package lv.nixx.poc.security.config;

import lv.nixx.poc.security.filter.AccountTypeFilter;
import lv.nixx.poc.security.filter.ViewNameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Autowired
    private ViewNameFilter viewNameFilter;

    @Bean
    public FilterRegistrationBean<AccountTypeFilter> accountTypeFilterFilterRegistration() {

        FilterRegistrationBean<AccountTypeFilter> registration = new FilterRegistrationBean<AccountTypeFilter>();
        registration.setFilter(new AccountTypeFilter());
        registration.addUrlPatterns("/account/*");
        registration.setName("accountFilter");
        registration.setOrder(1);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<ViewNameFilter> viewNameFilterFilterRegistration() {

        FilterRegistrationBean<ViewNameFilter> registration = new FilterRegistrationBean<ViewNameFilter>();
        registration.setFilter(viewNameFilter);
        registration.addUrlPatterns("/view/*");
        registration.setName("viewNameFilter");
        registration.setOrder(1);

        return registration;
    }

}
