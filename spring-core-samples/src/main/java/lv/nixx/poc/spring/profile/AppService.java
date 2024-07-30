package lv.nixx.poc.spring.profile;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AppService {

    @Value("${connection}")
    private String connection;

}
