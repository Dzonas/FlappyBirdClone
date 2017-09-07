package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages input from the keyboard.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class KeyManager implements KeyListener{
    /**
     * Array of keys
     */
    private boolean[] keys;
    public boolean space;
    public boolean escape;
    public boolean latch;

    /**
     * Constructor
     */
    public KeyManager(){
        keys = new boolean[256];
        latch = false;
    }

    /**
     * Every time it sets button to its logic value in array of keys.
     */
    public void tick() {
        space = keys[KeyEvent.VK_SPACE];
        escape = keys[KeyEvent.VK_ESCAPE];
    }

    /**
     * Unused
     * @param e KeyEvent that corresponds to key which was pressed.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Sets true in array of keys at index of key which was pressed.
     * @param e KeyEvent that corresponds to key which was pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(!latch) {
            keys[e.getKeyCode()] = true;
            latch = true;
        }
    }

    /**
     * Sets false in array of keys at index of key which was released.
     * @param e KeyEvent that corresponds to key which was pressed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        latch = false;
    }

    public void latch_space(){
        if(latch)
            keys[KeyEvent.VK_SPACE] = false;
    }
}
