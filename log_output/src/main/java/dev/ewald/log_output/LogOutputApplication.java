package dev.ewald.log_output;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LogOutputApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LogOutputApplication.class, args);

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
