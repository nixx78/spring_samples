package lv.nixx.poc.security.config;

import lv.nixx.poc.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/OncePerRequestFilter.html

@Component
public class ViewNameFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ViewNameFilter.class);

    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws ServletException, IOException {
        String viewName = httpServletRequest.getParameter("viewName");
        if (viewName != null) {
            LOG.info("User try to get view [{}] data", viewName);
            String userName = loginService.getUserName();

            if (!loginService.isViewIsAllowed(viewName)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                String responseToClient = "For user [" + userName + "] view [" + viewName + "] not allowed";
                httpServletResponse.getWriter().write(responseToClient);
                httpServletResponse.getWriter().flush();
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

}
