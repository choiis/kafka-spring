package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.data.Vo;

public class VoDeserializer implements Deserializer<Vo> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Vo deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, Vo.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Vo");
        }
    }

    @Override
    public void close() {
    }
}
