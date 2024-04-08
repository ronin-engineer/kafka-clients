package dev.ronin_engineer.kafka.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Consumer {

    @KafkaListener(
            topics = "${kafka.inbound-topic}"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(name = KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       @Payload String value) {
        log.info("Received a message from topic: " + topic + ", partition: " + partition + ", key: " + key + ", value: " + value);
        try {
            log.info("Processing the message: " + value);
        }
        catch (Exception e) {
            log.error("Failed to process message: " + value);
        }
    }
}
