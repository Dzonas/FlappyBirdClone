package game;

import display.Display;
import graphics.Assets;
import input.KeyManager;
import input.MouseManager;
import states.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

/**
 * Main class that initializes display, contains current state and runs tick and render methods at certain fps.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class Game implements Runnable {
    /**
     * Object that handles display.
     */
    private Display display;
    /**
     * Thread on which game is running.
     */
    private Thread thread;
    /**
     * Buffer strategy of the display.
     */
    private BufferStrategy bs;
    /**
     * Graphics object used to render.
     */
    private Graphics g;
    /**
     * Object that contains basic information
     */
    private Handler handler;
    /**
     * Object that manages keyboard input.
     */
    private KeyManager keyManager;
    /**
     * Object that manages mouse input.
     */
    private MouseManager mouseManager;
    /**
     * States game can be in.
     */
    private State currentState;
    /**
     * Window title.
     */
    private String title;
    /**
     * Window dimensions.
     */
    private int width, height;
    private int ground_height;
    private boolean running = false;

    /**
     * Constructor
     *
     * @param title Title of the window.
     * @param width Width of the window.
     * @param height Height of the window.
     */
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        ground_height = height / 8;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    /**
     * Initializes: display, handler, states objects.
     * Calls assets initialization.
     * Sets state to a default state.
     */
    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);

        currentState = new MenuState(handler);
    }

    /**
     * Ticks KeyManager and current State game is in.
     */
    private void tick(){
        keyManager.tick();

        if(currentState != null) {
            currentState.tick();
        }
    }

    /**
     * Creates buffer strategy. Draws current state onto canvas and clears it.
     * Shows canvas to the window.
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0, width, height); //Clear screen

        if(currentState != null) {
            currentState.render(g); //Renders current state
        }

        //End drawing

        bs.show(); //Shows game to the screen
        g.dispose(); //Removes object
    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        //Runs game at default fps
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
        }

        stop(); //In case it didn't stop
    }

    /**
     * Starts game thread.
     */
    public synchronized void start() {
        if(!running){
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Ends game thread.
     */
    public synchronized  void stop(){
        if(running){
            try{
                thread.join(); //Waits for thread to die
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        display.getFrame().dispatchEvent(new WindowEvent(display.getFrame(), WindowEvent.WINDOW_CLOSING)); //Closes window
    }

    //Getters and setters

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGround_height() {
        return ground_height;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Display getDisplay() {
        return display;
    }
}
