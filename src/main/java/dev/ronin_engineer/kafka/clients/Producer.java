package dev.ronin_engineer.kafka.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String key, String value) {
        try {
            kafkaTemplate.send(topic, key, value);  // async
//            SendResult<String, String> result = kafkaTemplate.send(topic, key, value).get(); // sync
        }
        catch (Exception e) {
            log.error("Failed to send the message topic: " + topic);
        }
    }

}
