package entities;

import game.Handler;
import graphics.Assets;

import java.awt.*;

/**
 * Moving ground.
 * @author jancy
 * @version 0.1
 * @since 22.05.2017
 */
public class Ground extends Entity implements ICollision, IMovable{
    private float xSpeed;
    private float ySpeed;

    private Rectangle bounds;

    public Ground(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);


        //Movement
        xSpeed = 0;
        ySpeed = 0;

        bounds = new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public void tick() {
        if(x + width < 0)
            x += 2 * width;
        moveX(); //Move along X axis
        moveY(); //Move along Y axis
        updateBoundingBox(); //Update BB position
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ground, (int)x, (int)y, width, height, null);
        //g.setColor(Color.red);
        //g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
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
    public boolean checkCollision(Rectangle bounds) {
        return false;
    }

    @Override
    public void updateBoundingBox() {
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

    public Rectangle getBounds() {
        return bounds;
    }
}
