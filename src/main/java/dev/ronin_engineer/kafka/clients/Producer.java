package dev.ronin_engineer.kafka.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String key, String value) {
        try {
            SendResult<String, String> result = kafkaTemplate.send(topic, key, value).get();
            log.info("Produced a message to topic: " + topic + " with key: " + key + ", value: " + value);
        } catch (Exception e) {
            log.error("Failed to produce the message to topic: " + topic);
        }
    }

}
