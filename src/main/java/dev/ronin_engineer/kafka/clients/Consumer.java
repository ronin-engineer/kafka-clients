package dev.ronin_engineer.kafka.clients;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(
            topics = "${kafka.inbound-topic}"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key,
                       @Payload String value) {
        log.info("Received a message: topic: " + topic + ", partition: " + partition + ", key: " + key + ", value: " + value);
        try {
            log.info("Processing the message: " + value);
        } catch (Exception e) {
            log.error("Failed to process the message: " + value);
        }
    }

}












