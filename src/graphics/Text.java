package graphics;

import java.awt.*;

/**
 * Handles drawing string onto screen.
 * @author jancy
 * @version 0.1
 * @since 22.05.2017
 */
public class Text {
    /**
     * Displays string with desired text, font, position and color.
     * @param g Graphics object.
     * @param text Desired string to be displayed.
     * @param xPos X position of the string
     * @param yPos Y position of the string
     * @param center If true x,y positions are in the center of the string.
     *               If false x,y position are in top-left corner of the string.
     * @param c Color of the string.
     * @param f Font of the string.
     */
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font f) {
        g.setColor(c);
        g.setFont(f);
        int x = xPos;
        int y = yPos;
        if(center) {
            FontMetrics fm = g.getFontMetrics(f);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
}
