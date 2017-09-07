package entities;

import game.Handler;
import graphics.Animation;
import graphics.Assets;

import java.awt.*;
import java.util.ArrayList;

/**
 * Handles input from the player, plays animation, checks for collision etc.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class Player extends Entity implements ICollision, IMovable{
    /**
     * Default speed at which player moves.
     */
    private static final float DEFAULT_SPEED = 10; // 10
    /**
     * Default acceleration thats affects player.
     */
    private static final float DEFAULT_ACCELERATION = 0.4f; // 0.4f
    /**
     * Speed at which animation is played.
     */
    private static final int ANIMATION_SPEED = 100;
    /**
     * Player animation object.
     */
    private Animation animation;
    /**
     * Rectangle collision bounds.
     */
    private Rectangle bounds;
    /**
     * Speed of the object.
     */
    private float xSpeed, ySpeed;
    /**
     * Status of the player.
     */
    private boolean isAlive;
    /**
     * Enable or disable collision.
     */
    private boolean collisionEnabled;
    /**
     * Enable or disable animation.
     */
    private boolean animationEnabled;

    /**
     * Constructor.
     * @param handler Handler of the game.
     * @param x X position of the entity.
     * @param y Y position of the entity.
     * @param width Width of the entity.
     * @param height Height of the entity.
     */
    public Player(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        //Booleans
        isAlive = true;
        collisionEnabled = true;
        animationEnabled = false;

        //Movement
        xSpeed = 0;
        ySpeed = 0;

        animation = new Animation(ANIMATION_SPEED, Assets.player);
        bounds = new Rectangle((int)x + 2, (int)y + 2, (int)(width * 0.88), (int)(height * 0.83));
    }

    @Override
    public void tick() {
        if(isAlive){
            getInput(); //Get input from the player
            gravity(); //Add gravity
            moveX(); //Move player along X axis
            moveY(); //Move player along Y axis
            if(y < 0) //If player moves out of the screen
                isAlive = false; //Kill player
            updateBoundingBox(); //Update BB position
            play_animation(); //Plays animation
        }
    }


    public void getInput() {

        //If space was pressed
        if(handler.getKeyManager().space) {
            //Set player speed
            ySpeed = -DEFAULT_SPEED;
            handler.getKeyManager().space = false;

            //Play animation
            animationEnabled = true;

            handler.getKeyManager().latch_space();
        }
    }

    private void gravity() {
        ySpeed += DEFAULT_ACCELERATION;
        if(ySpeed > 200)
            ySpeed = 200;
    }

    @Override
    public void moveX() {
        x += xSpeed;
    }

    @Override
    public void moveY() {
        y += ySpeed;
    }

    @Override
    public void updateBoundingBox(){
        bounds.setBounds((int)(this.x + 2), (int)(this.y + 2), (int)(width * 0.88), (int)(height * 0.83));
    }

    /**
     * Plays short animation when gets input from the player.
     */
    private void play_animation() {
        //Play whole animation once
        if(animationEnabled) {
            animation.tick();
            if(animation.getCurrentFrame() == Assets.player[0])
                animationEnabled = false;
        }
    }

    @Override
    public boolean checkCollision(Rectangle bounds) {
        boolean collision = false;

        if(collisionEnabled) {
            if(this.bounds.intersects(bounds))
                collision = true;
        }

        return collision;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), (int) x, (int) y, width, height, null);
        //g.setColor(Color.red);
        //g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void setX(float x){
        this.x = x;
        updateBoundingBox();
    }

    @Override
    public void setY(float y){
        this.y = y;
        updateBoundingBox();
    }

    public void setWidth(int width) {
        this.width = width;
        updateBoundingBox();
    }
    public void setHeight(int height) {
        this.height = height;
        updateBoundingBox();
    }
}
