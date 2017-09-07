package entities;

import game.Handler;

import java.awt.*;

/**
 * Abstract class for pipes.
 * @author jancy
 * @version 0.1
 * @since 22.05.2017
 */
public abstract class Pipe extends Entity implements ICollision, IMovable {
    protected static final int TOP_HEIGHT = 50;
    private float xSpeed, ySpeed;

    protected Rectangle bounds;

    /**
     * Constructor.
     *
     * @param handler Handler of the game.
     * @param x       X position of the pipe.
     * @param y       Y position of the pipe.
     * @param width   Width of the pipe.
     * @param height  Height of the pipe.
     */
    public Pipe(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        xSpeed = 0;
        ySpeed = 0;

        bounds = new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public void tick(){
        moveX();
        moveY();
        updateBoundingBox();
    }

    @Override
    public void moveX(){
        x += xSpeed;
    }

    @Override
    public void moveY(){
        y += ySpeed;
    }

    @Override
    public boolean checkCollision(Rectangle bounds) {
        return false;
    }

    @Override
    public void updateBoundingBox(){
        bounds.setBounds((int)x, (int)y, width, height);
    }

    //Getters and setters

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
