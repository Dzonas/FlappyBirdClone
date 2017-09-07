package states;

import game.Handler;
import graphics.Assets;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

/**
 * Handles behavior of the menu.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class MenuState extends State {
    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(10, 10, 50, 50, Assets.cogwheeel, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().setCurrentState(new SettingsState(handler));
            }
        }));
    }

    @Override
    public void tick() {
        get_input();
        uiManager.tick();
    }

    private void get_input() {
        if(handler.getKeyManager().space && !handler.getKeyManager().escape) { //Start game
            handler.getGame().setCurrentState(new GameState(handler));
            handler.getKeyManager().space = false;
        }
    }

    @Override
    public void render(Graphics g) {
        int ground_height = handler.getGame().getGround_height();
        g.drawImage(Assets.dark_sky, 0, 0, handler.getWidth(), handler.getHeight() - 3 * ground_height, null);
        g.drawImage(Assets.dark_background, 0, handler.getHeight() - 3 * ground_height, handler.getWidth(), 2 * ground_height,null);
        g.drawImage(Assets.dark_ground, 0, handler.getHeight() - ground_height, handler.getWidth(), ground_height, null);
        g.drawImage(Assets.splash_screen, handler.getWidth() / 2 - 150, handler.getHeight() / 2 - 175, 300, 350, null);
        uiManager.render(g);
    }
}
