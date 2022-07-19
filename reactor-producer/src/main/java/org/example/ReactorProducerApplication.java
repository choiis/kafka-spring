package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReactorProducerApplication {

    private final static Logger log = LoggerFactory.getLogger(ReactorProducerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReactorProducerApplication.class, args);
    }
}
