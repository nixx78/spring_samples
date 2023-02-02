package lv.nixx.poc.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO add docker container with DB and setup connection
//TODO add swagger-config
//TODO add controller to set different states for application component

@SpringBootApplication
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

}