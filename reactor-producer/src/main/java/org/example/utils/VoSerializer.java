package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serializer;
@AllArgsConstructor

public class VoSerializer implements Serializer<Object> {

    private final ObjectMapper objectMapper;

    @Override
    public byte[] serialize(String s, Object message) {
        try {
            return objectMapper.writeValueAsBytes(message);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
