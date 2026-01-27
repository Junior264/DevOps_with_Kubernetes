package dev.ewald.todo_app.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Value("${IMAGE_SHARED_PATH:/sharedImages/randomImage.jpg}")
    private String imageSharedPath;

    @Value("${IMAGE_SOURCE_URL:https://picsum.photos/1200}")
    private String imageSourceUrl;

    @Value("${IMAGE_REFRESH_TIMER:600000}")
    private long imageRefreshTimer;

    public void setImage() {
        File sharedImage = new File(imageSharedPath);

        if (sharedImage.exists()) {
            long age = System.currentTimeMillis() - sharedImage.lastModified();
            if (age > imageRefreshTimer) {
                new Thread(() -> downloadImage(sharedImage)).start();
            }
        } else {
            downloadImage(sharedImage);
        }
    }

    private void downloadImage(File target) {
        try {
            URL imageUrl = URI.create(imageSourceUrl).toURL();
            BufferedImage image = ImageIO.read(imageUrl);
            ImageIO.write(image, "jpg", target);
            target.setLastModified(System.currentTimeMillis()); 
            target.setReadable(true, false);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
