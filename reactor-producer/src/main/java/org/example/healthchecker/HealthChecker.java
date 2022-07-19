package org.example.healthchecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class HealthChecker {

    private final static Logger log = LoggerFactory.getLogger(HealthChecker.class);

    public Mono<ServerResponse> up(ServerRequest req) {
        log.info("I'm alive");
        return ServerResponse.ok().build();
    }
}
