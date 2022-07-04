package lv.nixx.poc.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.nixx.poc.security.model.CustomUser;
import lv.nixx.poc.security.model.PaymentOperation;
import lv.nixx.poc.security.model.PaymentRequest;
import lv.nixx.poc.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class PaymentRequestFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestFilter.class);

    private LoginService loginService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws ServletException, IOException {

        HttpRequestBodyWrapper cachedBodyHttpServletRequest = new HttpRequestBodyWrapper(httpServletRequest);

        CustomUser user = loginService.getLoggedInUser();
        String body = new BufferedReader(
                new InputStreamReader(cachedBodyHttpServletRequest.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        LOG.info("PaymentRequestFilter fired, user [{}] body [{}]", user.getUsername(), body);

        PaymentRequest paymentRequest = objectMapper.readValue(body, PaymentRequest.class);

        if (!user.isAdmin()) {
            PaymentOperation operation = paymentRequest.getOperation();
            if (operation.equals(PaymentOperation.ADD)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                String responseToClient = "For user [" + user.getUsername() + "] operation [" + operation + "] not allowed";
                httpServletResponse.getWriter().write(responseToClient);
                httpServletResponse.getWriter().flush();
                LOG.warn(responseToClient);
            } else {
                chain.doFilter(cachedBodyHttpServletRequest, httpServletResponse);
            }
        } else {
            chain.doFilter(cachedBodyHttpServletRequest, httpServletResponse);
        }

    }

}
