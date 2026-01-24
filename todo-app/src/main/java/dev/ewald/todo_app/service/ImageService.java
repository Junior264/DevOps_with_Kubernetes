package dev.ewald.todo_app.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
    public void setImage() {
        File sharedImage = new File("/sharedImages/randomImage.jpg");
        long tenMinutes = 10 * 60 * 1000;

        if (sharedImage.exists()) {
            long age = System.currentTimeMillis() - sharedImage.lastModified();
            if (age > tenMinutes) {
                new Thread(() -> downloadImage(sharedImage)).start();
            }
        } else {
            downloadImage(sharedImage);
        }
    }

    private void downloadImage(File target) {
        try {
            URL imageUrl = URI.create("https://picsum.photos/1200").toURL();
            BufferedImage image = ImageIO.read(imageUrl);
            ImageIO.write(image, "jpg", target);
            target.setLastModified(System.currentTimeMillis()); 
            target.setReadable(true, false);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
