package org.example.starter;

import org.example.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class ConsumerStarter {


    private final static Logger log = LoggerFactory.getLogger(ConsumerStarter.class);

    @Value("${kafka.topic}")
    private String topic;

    private MessageListener messageListener;

    private Map<String, Object> consumerProperties;

    public ConsumerStarter(MessageListener messageListener, Map<String, Object> consumerProperties) {
        this.messageListener = messageListener;
        this.consumerProperties = consumerProperties;
    }

    private Map<String, ConcurrentMessageListenerContainer<String, String>> consumersMap =
            new HashMap<>();

    public void startConsumer() {

        log.info("starting consumer....");
        ConcurrentMessageListenerContainer<String, String> container = consumersMap.get(topic);
        if (!ObjectUtils.isEmpty(container) && container.isRunning()) {
            log.info("consumer is running now");
            return;
        } else if (!ObjectUtils.isEmpty(container) && !container.isRunning()) {
            log.info("consumer created but It is not running now");
            container.start();
            log.info("consumer restarted");
            return;
        }
        ContainerProperties containerProps = new ContainerProperties(topic);
        containerProps.setPollTimeout(100);
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        ConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(consumerProperties);
        container = new ConcurrentMessageListenerContainer<>(factory, containerProps);

        container.setupMessageListener(messageListener);
        container.setConcurrency(3);
        container.start();
        consumersMap.put(topic, container);
        log.info("kafka consumer started for ", topic);
    }

    public void stopConsumer() {
        log.info("stopping consumer....");
        ConcurrentMessageListenerContainer<String, String> container = consumersMap.get(topic);
        if (ObjectUtils.isEmpty(container)) {
            log.info("consumer empty");
            return;
        }
        container.stop();
        consumersMap.remove(topic);
        log.info("kafka consumer stopped for ", topic);
    }
}
