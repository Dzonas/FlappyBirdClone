package entities;

import game.Handler;

import java.awt.*;

/**
 * Base class for every object in game that has position and size.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public abstract class Entity {
    /**
     * Handler of the game.
     */
    protected Handler handler;
    /**
     * Position of the entity.
     */
    protected float x, y;
    /**
     * Size of the entity.
     */
    protected int width, height;

    /**
     * Constructor.
     * @param handler Handler of the game.
     * @param x X position of the entity.
     * @param y Y position of the entity.
     * @param width Width of the entity.
     * @param height Height of the entity.
     */
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Ticks entity.
     */
    public abstract void tick();

    /**
     * Renders entity to display.
     * @param g Graphics object.
     */
    public abstract void render(Graphics g);

    //Getters and setters

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
