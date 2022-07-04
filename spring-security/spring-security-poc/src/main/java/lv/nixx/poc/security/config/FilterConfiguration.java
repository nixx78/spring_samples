package lv.nixx.poc.security.config;

import lv.nixx.poc.security.filter.PaymentRequestFilter;
import lv.nixx.poc.security.filter.ViewNameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    @Autowired
    public FilterRegistrationBean<PaymentRequestFilter> accountTypeFilterFilterRegistration(PaymentRequestFilter paymentRequestFilter) {

        FilterRegistrationBean<PaymentRequestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(paymentRequestFilter);
        registration.addUrlPatterns("/payment");
        registration.setName("paymentFilter");
        registration.setOrder(1);

        return registration;
    }

    @Bean
    @Autowired
    public FilterRegistrationBean<ViewNameFilter> viewNameFilterFilterRegistration(ViewNameFilter viewNameFilter) {

        FilterRegistrationBean<ViewNameFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(viewNameFilter);
        registration.addUrlPatterns("/view/data/*");
        registration.setName("viewNameFilter");
        registration.setOrder(1);

        return registration;
    }

}
