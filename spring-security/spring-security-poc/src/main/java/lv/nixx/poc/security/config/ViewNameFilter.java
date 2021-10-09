package lv.nixx.poc.security.config;

import lv.nixx.poc.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ViewNameFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(ViewNameFilter.class);

    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            final String url = httpServletRequest.getRequestURL().toString();
            final String queryString = httpServletRequest.getQueryString();

            String viewName = httpServletRequest.getParameter("viewName");
            if (viewName != null) {
                LOG.info("User try to get view [{}] data", viewName);

                String userName = loginService.getUserName();

                if (!loginService.isViewIsAllowed(viewName)) {
                    throw new IllegalViewAccessException(userName, viewName);
                }

            }

        }

        chain.doFilter(request, response);
    }

}
