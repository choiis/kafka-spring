package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.sender.KafkaProducer;
import org.example.utils.JacksonJsonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public JacksonJsonSerializer jacksonJsonSerializer(ObjectMapper objectMapper) {
        return new JacksonJsonSerializer(objectMapper);
    }

    @Value("${kafka.server}")
    private String BOOTSTRAP_SERVERS;

    @Bean
    public KafkaSender<String, Object> kafkaSender(JacksonJsonSerializer jacksonJsonSerializer) {
        Map<String, Object> producerProps = Map.of(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS
        );
        SenderOptions<String,Object> options = SenderOptions.<String, Object>create(producerProps)
                .maxInFlight(1024)
                .withValueSerializer(jacksonJsonSerializer)
                .stopOnError(false);

        return KafkaSender.create(options);
    }

    @Bean
    public KafkaProducer kafkaProducer(KafkaSender<String, Object> kafkaSender) {
        return new KafkaProducer(kafkaSender);
    }
}
