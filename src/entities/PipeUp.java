package entities;

import game.Handler;
import graphics.Assets;

import java.awt.*;

/**
 * Normal pipe.
 * @author jancy
 * @version 0.1
 * @since 22.05.2017
 */
public class PipeUp extends Pipe{
    public PipeUp(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.pipe, (int)x, (int)y + TOP_HEIGHT, width, height - TOP_HEIGHT, null);
        g.drawImage(Assets.pipe_up, (int)x, (int)y, width, TOP_HEIGHT, null);
        //g.setColor(Color.red);
        //g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
