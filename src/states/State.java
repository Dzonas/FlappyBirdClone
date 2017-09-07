package states;

import game.Handler;

import java.awt.Graphics;

/**
 * Base for every state possible in game eg. "Game State", "Menu State" etc.
 *
 * @author Jan Cybulski
 * @version 0.1
 * @since 20.05.2017
 */
public abstract class State {
    /**
     * Game handler.
     */
    protected Handler handler;

    /**
     * Constructor
     *
     * @param handler Game handler
     */
    public State(Handler handler) {
        this.handler = handler;
    }

    /**
     * Ticks state.
     */
    public abstract void tick();

    /**
     * Renders state to display.
     * @param g Graphics object.
     */
    public abstract void render(Graphics g);
}
