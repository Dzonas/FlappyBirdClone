package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Basic button made from image.
 * @author jancy
 * @version 0.1
 * @since 23.05.2017
 */
public class UIImageButton extends UIObject{
    private BufferedImage image;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height,
                         BufferedImage image, ClickListener clicker){
        super(x, y, width, height);
        this.image = image;
        this.clicker = clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering)
            g.drawImage(image, (int)x - 5, (int)y - 5, width + 10, height + 10, null);
        else
            g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
