package org.example.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.listener.MessageListener;
import org.example.starter.ConsumerStarter;
import org.example.utils.VoDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {

    @Value("${kafka.server}")
    private String kakfaServer;

    @Bean
    public ConsumerStarter consumerStarter(MessageListener messageListener) {
        Map<String, Object> consumerProperties = new HashMap<>();
        consumerProperties.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kakfaServer);
        consumerProperties.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        consumerProperties.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        consumerProperties.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        consumerProperties.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                VoDeserializer.class);
        consumerProperties.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "event");
        return new ConsumerStarter(messageListener, consumerProperties);
    }
}
