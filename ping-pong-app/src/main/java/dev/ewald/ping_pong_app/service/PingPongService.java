package dev.ewald.ping_pong_app.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ewald.ping_pong_app.model.Counter;
import dev.ewald.ping_pong_app.repository.PingPongRepository;

@Service
public class PingPongService {
    @Autowired
    private PingPongRepository pingPongRepository;

    public String pongCounter() {
        Counter counter = pingPongRepository.findById(1).orElse(new Counter(1, 0));

        counter.setCount(counter.getCount() + 1);
        pingPongRepository.save(counter);

        return "Ping / Pongs: " + counter.getCount();
    }

    public Integer getCounter(){
        return pingPongRepository
                    .findById(1)
                    .orElse(new Counter(1, 0))
                    .getCount();
    }

    // public String pongCounter() throws IOException{
    //     File file = new File("./files/pongCounter.txt");
    //     file.getParentFile().mkdirs();
    //     file.createNewFile();

    //     Integer counter = getCounter();
    //     counter++;
    //     setCounter(file, counter);

    //     return "Ping / Pongs: " + String.valueOf(counter);
    // }

    // public Integer getCounter() throws IOException{
    //     Path path = Paths.get("./files/pongCounter.txt");

    //     try {
    //         String firstLine = Files.readString(path).trim();

    //         return firstLine.isEmpty() ? 0 : Integer.parseInt(firstLine);
    //     } catch (Exception e) {
    //         System.err.println("Unable to read counter: " + e.getMessage());

    //         return 0;
    //     }
    // }

    public List<String> getTimeStampedRandomStrings() throws IOException {
        Stream<String> stream = Files.lines(Paths.get("./files/timeStampedRandomString.txt"));
        List<String> list = stream.parallel().toList();
        stream.close();

        return list;
    }

    public void setCounter(File file, Integer counter) throws IOException{
        String newCounter = String.valueOf(counter);
        FileWriter fw = new FileWriter(file.getAbsolutePath(), false);

        fw.write(newCounter);
        fw.close();
    }

    public Integer getPings() throws IOException{
        return getCounter();
    }

}
