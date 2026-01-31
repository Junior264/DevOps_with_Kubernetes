package dev.ewald.broadcaster.service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@Slf4j
public class BroadcasterService {

    @Value("${NATS_URL:nats://my-nats:4222}")
    private String natsUrl;

    @Value("${GENERIC_URL}")
    private String genericUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void startListening() {
        try {
            Connection nc = Nats.connect(natsUrl);
            log.info("Connected to NATS at {}", natsUrl);

            Dispatcher d = nc.createDispatcher((msg) -> {
                String content = new String(msg.getData(), StandardCharsets.UTF_8);
                sendToGeneric(content);
            });

            d.subscribe("todo_events", "broadcaster-group");

        } catch (Exception e) {
            log.error("Error connecting to NATS", e);
        }
    }

    private void sendToGeneric(String message) {
        try {
            log.info("Forwarding message to Generic URL: {}", message);
            
            Map<String, String> payload = Map.of(
                "user", "bot",
                "message", message
            );
            
            restTemplate.postForEntity(genericUrl, payload, String.class);
        } catch (Exception e) {
            log.error("Failed to send to external service: {}", e.getMessage());
        }
    }
}