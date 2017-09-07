package graphics;

import java.awt.image.BufferedImage;

/**
 * Loads assets into memory.
 *
 * @author Jan Cybulski
 * @version 0.1
 * @since 20.05.2017
 */
public class Assets {
    private static final int bird_images = 4;
    private static final int types_of_medals = 4;

    public static BufferedImage[] player;
    public static BufferedImage ceiling;
    public static BufferedImage background, dark_background;
    public static BufferedImage sky, dark_sky;
    public static BufferedImage pipe_up, pipe_down, pipe;
    public static BufferedImage[] medals;
    public static BufferedImage scoreboard;
    public static BufferedImage splash_screen;
    public static BufferedImage ground, dark_ground;
    public static BufferedImage cogwheeel;
    public static BufferedImage left_arrow;
    public static BufferedImage[] bird_normal, bird_red, bird_purple, bird_green;

    /**
     * Initializes assets and loads them into memory.
     */
    public static void init() {
        SpriteSheet sheet_bird_normal = new SpriteSheet(ImageLoader.loadImage("/textures/bird.png"));
        SpriteSheet sheet_bird_red = new SpriteSheet(ImageLoader.loadImage("/textures/bird_red.png"));
        SpriteSheet sheet_bird_purple = new SpriteSheet(ImageLoader.loadImage("/textures/bird_purple.png"));
        SpriteSheet sheet_bird_green = new SpriteSheet(ImageLoader.loadImage("/textures/bird_green.png"));
        player = new BufferedImage[bird_images];
        bird_normal = new BufferedImage[bird_images];
        bird_red = new BufferedImage[bird_images];
        bird_purple = new BufferedImage[bird_images];
        bird_green = new BufferedImage[bird_images];
        for(int i = 0; i < bird_images; i++) {
            bird_normal[i] = sheet_bird_normal.crop(0,i * 24, 34, 24);
        }
        player = bird_normal;
        for(int i = 0; i < bird_images; i++) {
            bird_red[i] = sheet_bird_red.crop(0,i * 24, 34, 24);
        }
        for(int i = 0; i < bird_images; i++) {
            bird_purple[i] = sheet_bird_purple.crop(0,i * 24, 34, 24);
        }
        for(int i = 0; i < bird_images; i++) {
            bird_green[i] = sheet_bird_green.crop(0,i * 24, 34, 24);
        }
        ceiling = ImageLoader.loadImage("/textures/ceiling.png");
        background = ImageLoader.loadImage("/textures/background.png");
        dark_background = ImageLoader.loadImage("/textures/dark_background.png");
        pipe_up = ImageLoader.loadImage("/textures/pipe-up.png");
        pipe_down = ImageLoader.loadImage("/textures/pipe-down.png");
        pipe = ImageLoader.loadImage("/textures/pipe.png");
        medals = new BufferedImage[types_of_medals];
        medals[0] = ImageLoader.loadImage("/textures/medal_bronze.png");
        medals[1] = ImageLoader.loadImage("/textures/medal_silver.png");
        medals[2] = ImageLoader.loadImage("/textures/medal_gold.png");
        medals[3] = ImageLoader.loadImage("/textures/medal_platinum.png");
        scoreboard = ImageLoader.loadImage("/textures/scoreboard.png");
        splash_screen = ImageLoader.loadImage("/textures/splash.png");
        ground = ImageLoader.loadImage("/textures/land.png");
        dark_ground = ImageLoader.loadImage("/textures/dark_land.png");
        sky = ImageLoader.loadImage("/textures/sky.png");
        dark_sky = ImageLoader.loadImage("/textures/dark_sky.png");
        cogwheeel = ImageLoader.loadImage("/textures/cogwheel.png");
        left_arrow = ImageLoader.loadImage("/textures/left_arrow.png");
    }
}
