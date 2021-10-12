package lv.nixx.poc.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountTypeFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AccountTypeFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        LOG.info("Account type filter fired, URL [{}]", httpServletRequest.getRequestURL());

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
