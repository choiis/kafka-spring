package org.example.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements AcknowledgingMessageListener<String,String> {

    private final static Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Override
    @KafkaListener(topics = "message.queue", groupId = "event")
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {

        try {
            String key = consumerRecord.key();
            String value = consumerRecord.value();
            JSONObject jsonObject = new JSONObject(value);
            String name = (String) jsonObject.get("name");
            int number = (Integer) jsonObject.get("number");
            log.info("consumerRecord key " + key + " value " + name + " " + number);
        } catch (JSONException e) {
            log.error("consume data key json parse error");
        } catch (Exception e) {
            log.error("consume data unexpected error ");
        }

    }
}
