package entities;

import files.FileManager;
import game.Handler;
import graphics.Assets;
import graphics.Text;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Keeps track of the score and displays it.
 * If game is over displays scoreboard.
 *
 * @author jancy
 * @version 0.1
 * @since 22.05.2017
 */
public class Scoreboard extends Entity {
    /**
     * Current score of the game.
     */
    public int score;
    /**
     * Best score.
     */
    public int best_score;
    private final Font fontBig = new Font("Arial", Font.BOLD, 64);
    private final Font fontSmall = new Font("Arial", Font.BOLD, 32);
    private BufferedImage medal;
    private boolean gameOver;

    /**
     * Constructor
     * Creates file with scores if it doesn't exist.
     *
     * @param handler Game handler.
     * @param x X position of the scoreboard.
     * @param y Y position of the scoreboard.
     * @param width Width of the scoreboard.
     * @param height Height of the scoreboard.
     */
    public Scoreboard(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        score = 0;
        FileManager.create_file("scores.txt", "0");
        best_score = FileManager.get_integer("scores.txt");
        gameOver = false;
    }

    @Override
    public void tick() {
        if(score > best_score) {
            best_score = score;
            FileManager.set("scores.txt", Integer.toString(best_score));
        }
        if(score < 10)
            medal = Assets.medals[0];
        else if(score < 20)
            medal = Assets.medals[1];
        else if(score < 30)
            medal = Assets.medals[2];
        else if(score < 40)
            medal = Assets.medals[3];
    }

    @Override
    public void render(Graphics g) {
        if(!gameOver)
            Text.drawString(g, Integer.toString(score), handler.getWidth() / 2, handler.getHeight() / 8, true, Color.white, fontBig);
        else {
            g.drawImage(Assets.scoreboard, (int)x, (int)y, width, height, null);
            Text.drawString(g, Integer.toString(score), (int)x + 275, (int)y + 165, true, Color.white, fontSmall);
            Text.drawString(g, Integer.toString(best_score), (int)x + 275, (int)y + 225, true, Color.white, fontSmall);
            g.drawImage(medal, (int)x + 47, (int)y + 163, 60, 60, null);
        }
    }

    //Getters and setters

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
