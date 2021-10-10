package lv.nixx.poc.security.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class IllegalViewAccessException extends RuntimeException {
    private final String userName;
    private final String view;

    public IllegalViewAccessException(String userName, String view) {
        super();
        this.userName = userName;
        this.view = view;
    }

    @Override
    public String getMessage() {
        return "For user [" + userName + "] view [" + view + "] not allowed";
    }
}
