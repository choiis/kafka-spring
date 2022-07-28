package org.example.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.data.Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements AcknowledgingMessageListener<String, Vo> {

    private final static Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onMessage(ConsumerRecord<String, Vo> consumerRecord, Acknowledgment acknowledgment) {

        try {
            String key = consumerRecord.key();
            Vo vo = consumerRecord.value();
            String name = vo.getName();
            int number = vo.getNumber();
            log.info("consumerRecord key " + key + " value " + name + " " + number);
        } catch (Exception e) {
            log.error("consume data unexpected error ");
        } finally {
            acknowledgment.acknowledge(); // offset commit한다
        }

    }
}
