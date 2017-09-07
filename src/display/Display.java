package display;

import javax.swing.*;
import java.awt.*;

/**
 * Sets up window and canvas.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class Display {
    /**
     * Main frame of the game.
     */
    private JFrame frame;
    /**
     * Canvas on which everything is drawn.
     */
    private Canvas canvas;
    /**
     * Title of the window.
     */
    private String title;
    /**
     * Dimensions of the window.
     */
    private int width, height;

    /**
     * Constructor
     *
     * @param title Title of the window.
     * @param width Width of the window.
     * @param height Height of the window.
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    /**
     * Creates window and canvas.
     * Performs basic setup of the display.
     * Fits canvas to window.
     */
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //Center window
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas); //Add canvas to frame
        frame.pack(); //Resizes frame to fit canvas
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
