package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Custom created loader of images.
 *
 * @author Jan Cybulski
 * @version 0.1
 * @since 20.05.2017
 */
public class ImageLoader {
    /**
     * Gets path to the image and returns it as BufferedImage object.
     *
     * @param path Relative path to the image.
     * @return Loaded image.
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
