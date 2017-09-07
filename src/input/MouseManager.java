package input;

import ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Manages input from the mouse.
 * @author jancy
 * @version 0.1
 * @since 23.05.2017
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    /**
     * If left mouse button is pressed.
     */
    private boolean leftPressed;
    /**
     * Holds position of mouse pointer.
     */
    private int mouseX, mouseY;
    private UIManager uiManager;

    public MouseManager(){

    }

    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;

        if(uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(uiManager != null)
            uiManager.onMouseMove(e);
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
