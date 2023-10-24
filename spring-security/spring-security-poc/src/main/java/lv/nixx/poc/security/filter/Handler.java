package lv.nixx.poc.security.filter;

import lv.nixx.poc.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class Handler implements HandlerInterceptor {

    private final LoginService loginService;

    @Autowired
    public Handler(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Username_from_handler", loginService.getUserName());
        return true;
    }

}
