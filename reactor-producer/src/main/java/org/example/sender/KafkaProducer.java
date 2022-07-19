package org.example.sender;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.data.Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

public class KafkaProducer {

    private final KafkaSender<String, Object> sender;

    public KafkaProducer(KafkaSender<String, Object> sender) {
        this.sender = sender;
    }

    @Value("${kafka.topic}")
    private String TOPIC;

    private final static Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    public Mono<Void> send(Flux<? extends Vo> messages) {
        log.info("KafkaProducer send " + messages);
        return Mono.deferWithContext(x -> {

            var records = messages.map(message -> new ProducerRecord<String, Object>(TOPIC, message.getName(), message))
                    .map(pRecord -> SenderRecord.create(pRecord, null));
            return sender.send(records)
                    .doOnError(e -> {
                        log.error("Failed to producer kafka", e);
                    })
                    .then();
        });
    }
}
