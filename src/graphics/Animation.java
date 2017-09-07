package graphics;

import java.awt.image.BufferedImage;

/**
 * Handles rendering animation.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class Animation {
    /**
     * Speed of the animation.
     */
    private int speed;
    /**
     * Points at image in frames to display.
     */
    private int index;
    /**
     * Handles animation timing.
     */
    private long lastTime, timer;
    /**
     * Animation frames.
     */
    private BufferedImage[] frames;

    /**
     * Constructor
     *
     * @param speed Speed at which animation is displayed.
     * @param frames How many frames animation holds.
     */
    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * Switches anmation frames at desired speed.
     */
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed) {
            index++;
            timer = 0;
            if(index >= frames.length)
                index = 0;
        }
    }

    //Getters and setters

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}