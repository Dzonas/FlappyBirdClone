package ui;

import game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Manages all UI elements in a state.
 * @author jancy
 * @version 0.1
 * @since 23.05.2017
 */
public class UIManager {
    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<>();
    }

    /**
     * Ticks every UI element.
     */
    public void tick(){
        for(UIObject o : objects)
            o.tick();
    }

    /**
     * Renders every UI element.
     * @param g Graphics object.
     */
    public void render(Graphics g){
        for(UIObject o : objects)
            o.render(g);
    }

    public void onMouseMove(MouseEvent e){
        for(UIObject o : objects)
            o.onMouseMove(e);
    }

    public void onMouseRelease(MouseEvent e){
        for(UIObject o : objects)
            o.onMouseRelease(e);
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void removeObject(UIObject o){
        objects.remove(o);
    }
}
