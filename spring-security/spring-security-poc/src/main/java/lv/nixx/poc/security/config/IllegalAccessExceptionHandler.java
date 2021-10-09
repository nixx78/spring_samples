package lv.nixx.poc.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalAccessExceptionHandler {

    @ExceptionHandler(IllegalViewAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public String process(IllegalViewAccessException ex) {
        return "For user [" + ex.getUserName() + "] view [" + ex.getView() + "] not allowed";
    }

}
