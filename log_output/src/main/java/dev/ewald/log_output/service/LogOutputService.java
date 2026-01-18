package dev.ewald.log_output.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class LogOutputService {
    public String getTimeStampedRandomString() {
        String randomString = generateRandomString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        return randomString + " " + formattedNow;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
