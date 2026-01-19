package dev.ewald.log_output;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LogOutputApplication {

    private static File file;

    LogOutputApplication() throws IOException {
        file = new File("./files/timeStampedRandomString.txt");
        file.createNewFile();
    }

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LogOutputApplication.class, args);

		while(true) {
            String randomString = generateRandomString();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedNow = now.format(formatter);

            writeToFile(randomString + " " + formattedNow);

            TimeUnit.SECONDS.sleep(5);
        }
    }

    public static void writeToFile(String line) {
        try (FileWriter fw = new FileWriter(file.getAbsolutePath(), true)) {
            fw.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
