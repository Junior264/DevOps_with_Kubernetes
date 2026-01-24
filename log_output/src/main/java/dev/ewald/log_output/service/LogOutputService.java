package dev.ewald.log_output.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.File;
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
    private final RestClient restClient = RestClient.create();

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

    public String getTimeStampedRandomStringWithPongCount() throws IOException{
        String randomString = generateRandomString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        File file = new File("./files/pongCounter.txt");
        file.createNewFile();

        return randomString + " " + formattedNow + " \n Ping / Pongs: " + getCounterFromRequest();
    }

    public Integer getCounterFromRequest() {
        return restClient.get()
                .uri("http://ping-pong-svc:2348/pings")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Integer.class);
    }

    // public Integer getCounter(File file) throws IOException{
    //     String firstLine = Files.lines(Paths.get("./files/pongCounter.txt")).findFirst().orElse("0");

    //     Integer currentCounter = Integer.valueOf(firstLine);

    //     return currentCounter;
    // }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
