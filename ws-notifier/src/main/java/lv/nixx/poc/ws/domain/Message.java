package lv.nixx.poc.ws.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private Action action;
    private String content;
}
