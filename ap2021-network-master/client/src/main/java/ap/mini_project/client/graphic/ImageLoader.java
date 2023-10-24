package ap.mini_project.client.graphic;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null)
            instance = new ImageLoader();
        return instance;
    }

    private Map<String, BufferedImage> images;

    public ImageLoader() {
        images = new HashMap<>();
    }

    public BufferedImage getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        }

        // load from server
        return null;
    }
}
