package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.starter.ConsumerStarter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SwitchController {

    private ConsumerStarter consumerStarter;

    @GetMapping(value = "on")
    public ResponseEntity<String> on() {
        consumerStarter.startConsumer();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "off")
    public ResponseEntity<String> off() {
        consumerStarter.stopConsumer();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
