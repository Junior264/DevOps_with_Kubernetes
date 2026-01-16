package dev.ewald;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        while(true) {
            String randomString = generateRandomString();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);

            System.out.println(randomString + " " + formattedNow);

            TimeUnit.SECONDS.sleep(5);
        }
    }

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}