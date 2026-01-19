package dev.ewald.log_output.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class LogOutputService {
    public String getTimeStampedRandomString() {
        String randomString = generateRandomString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        return randomString + " " + formattedNow;
    }

    public List<String> getTimeStampedRandomStrings() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("./files/timeStampedRandomString.txt"));
        List<String> list = stream.parallel().toList();
        stream.close();

        return list;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
