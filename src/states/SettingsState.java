package states;

import game.Handler;
import graphics.Assets;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

/**
 * Handles behavior of the settings menu.
 * @author jancy
 * @version 0.1
 * @since 23.05.2017
 */
public class SettingsState extends State {
    private UIManager uiManager;
    private int player_width;
    private int player_height;

    public SettingsState(Handler handler){
        super(handler);

        player_width = handler.getWidth() / 8;
        player_height = (int)(player_width * 0.7);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(10, 10, 50, 50, Assets.left_arrow, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().setCurrentState(new MenuState(handler));
            }
        }));

        uiManager.addObject(new UIImageButton(150, 300, player_width, player_height, Assets.bird_normal[0], new ClickListener() {
            @Override
            public void onClick() {
                Assets.player = Assets.bird_normal;
                handler.getGame().setCurrentState(new MenuState(handler));
            }
        }));

        uiManager.addObject(new UIImageButton(250, 300, player_width, player_height, Assets.bird_red[0], new ClickListener() {
            @Override
            public void onClick() {
                Assets.player = Assets.bird_red;
                handler.getGame().setCurrentState(new MenuState(handler));
            }
        }));

        uiManager.addObject(new UIImageButton(150, 400, player_width, player_height, Assets.bird_purple[0], new ClickListener() {
            @Override
            public void onClick() {
                Assets.player = Assets.bird_purple;
                handler.getGame().setCurrentState(new MenuState(handler));
            }
        }));

        uiManager.addObject(new UIImageButton(250, 400, player_width, player_height, Assets.bird_green[0], new ClickListener() {
            @Override
            public void onClick() {
                Assets.player = Assets.bird_green;
                handler.getGame().setCurrentState(new MenuState(handler));
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        int ground_height = handler.getGame().getGround_height();
        g.drawImage(Assets.dark_sky, 0, 0, handler.getWidth(), handler.getHeight() - 3 * ground_height, null);
        g.drawImage(Assets.dark_background, 0, handler.getHeight() - 3 * ground_height, handler.getWidth(), 2 * ground_height,null);
        g.drawImage(Assets.dark_ground, 0, handler.getHeight() - ground_height, handler.getWidth(), ground_height, null);
        uiManager.render(g);
    }
}
