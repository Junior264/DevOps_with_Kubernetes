package dev.ewald.ping_pong_app.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class PingPongService {
    public String pongCounter() throws IOException{
        File file = new File("./files/pongCounter.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

        Integer counter = getCounter();
        counter++;
        setCounter(file, counter);

        return "Ping / Pongs: " + String.valueOf(counter);
    }

    public Integer getCounter() throws IOException{
        String firstLine = Files.lines(Paths.get("./files/pongCounter.txt")).findFirst().orElse("0");

        Integer currentCounter = Integer.valueOf(firstLine);

        return currentCounter;
    }

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
