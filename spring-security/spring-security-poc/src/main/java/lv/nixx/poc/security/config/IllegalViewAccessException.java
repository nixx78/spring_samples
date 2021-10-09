package lv.nixx.poc.security.config;

import lombok.Getter;

@Getter
public class IllegalViewAccessException extends RuntimeException {
    private final String userName;
    private final String view;

    public IllegalViewAccessException(String userName, String view) {
        super();
        this.userName = userName;
        this.view = view;
    }

}
