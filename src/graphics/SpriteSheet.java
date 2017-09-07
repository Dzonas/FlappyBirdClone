package graphics;

import java.awt.image.BufferedImage;

/**
 * Handles cutting images into smaller pieces.
 *
 * @author Jan Cybulski
 * @version 0.1
 * @since 20.05.2017
 */
public class SpriteSheet {
    /**
     * Image to be cropped out.
     */
    private BufferedImage sheet;

    /**
     * Constructor
     *
     * @param sheet BufferedImage object to be cut.
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Gets BufferedImage object and cuts it to desired sub-image.
     *
     * @param x X position of new sub-image.
     * @param y Y position of new sub-image.
     * @param width Width of new sub-image.
     * @param height Height of new sub-image.
     * @return Cut image.
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
