package org.example.router;

import org.example.healthchecker.HealthChecker;
import org.example.sender.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class RouterConfiguration {

    private final static Logger log = LoggerFactory.getLogger(RouterConfiguration.class);

    private String INSERT_PATH = "/api/producer";

    private String HEALTH_PATH = "/health/check";

    @Bean
    public RouterHandler routerHandler(KafkaProducer kafkaProducer) {
        return new RouterHandler(kafkaProducer);
    }

    @Bean
    public HealthChecker healthChecker(){
        return new HealthChecker();
    }

    @Bean
    public RouterFunction<ServerResponse> router(RouterHandler routerHandler, HealthChecker healthChecker) {
        log.info("RouterConfiguration router");
        return RouterFunctions.route().POST(INSERT_PATH, routerHandler::post)
                .GET(HEALTH_PATH, healthChecker::up)
                .build();
    }
}
