package game;

import input.KeyManager;
import input.MouseManager;

/**
 * Allows access for a game object.
 * @author Jan Cybulski
 * @version 0.1
 * @since 20.05.2017
 */
public class Handler {
    /**
     * Current game object.
     */
    private Game game;

    /**
     * Constructor
     *
     * @param game Current game.
     */
    public Handler(Game game) {
        this.game = game;
    }

    //Getters and setters

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }
}
