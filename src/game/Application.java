package game;

/**
 * Entry point for the game.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class Application {
    public static void main(String[] args){
        Game game = new Game("Flappy Bird", 450, 800);
        game.start();
    }
}
