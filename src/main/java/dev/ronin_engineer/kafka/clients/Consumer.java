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

    @Value("${kafka.inbound-topic}")
    private String inboundTopic;


    @KafkaListener(
            topics = "${kafka.inbound-topic}"
    )
    public void listen(@Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                       @Header(name = KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Payload String value) {
        log.info("Received a message from topic: " + inboundTopic + ", partition: " + partition + ", key: " + key + ", value: " + value);
        try {
            log.info("Processed the message: " + value);
        }
        catch (Exception e) {
            log.error("Failed to process message: " + value);
        }
    }
}
