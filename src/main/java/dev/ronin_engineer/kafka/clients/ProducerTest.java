package dev.ronin_engineer.kafka.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProducerTest implements CommandLineRunner {

    @Value("${kafka.outbound-topic:first-topic}")
    private String outboundTopic;


    private final Producer producer;


    @Override
    public void run(String... args) throws Exception {
        producer.send(outboundTopic, "k1", "test");
    }
}
