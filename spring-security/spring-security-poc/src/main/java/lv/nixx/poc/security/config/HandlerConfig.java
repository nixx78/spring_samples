package lv.nixx.poc.security.config;

import lv.nixx.poc.security.filter.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HandlerConfig implements WebMvcConfigurer {

    private final Handler handler;

    @Autowired
    public HandlerConfig(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handler)
                .addPathPatterns("/endpointWithHandler");
    }

}
