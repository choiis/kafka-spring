package org.example.router;

import lombok.AllArgsConstructor;
import org.example.data.Vo;
import org.example.sender.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class RouterHandler {

    private KafkaProducer kafkaProducer;

    private final static Logger log = LoggerFactory.getLogger(RouterHandler.class);

    public Mono<ServerResponse> post(ServerRequest serverRequest) {
        log.info("RouterHandler post");
        Flux<Vo> voFlux = serverRequest.bodyToFlux(Vo.class);
        return kafkaProducer.send(voFlux).flatMap(x -> ServerResponse.ok().build());
    }

}
