package states;

import entities.*;
import game.Handler;
import graphics.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handles behavior of the game.
 * @author jancy
 * @version 0.1
 * @since 20.05.2017
 */
public class GameState extends State {
    private final static float STARTING_SPEED = 3.0f;
    private final static int DISTANCE_BETWEEN_PIPES = 300;
    private final static int N_PIPES = 6;
    private final static int MAX_WINDOW_SIZE = 200;
    private final static int MIN_WINDOW_SIZE = 150;
    private final static int MAX_WINDOW_HEIGHT = 100;
    private final static int MIN_WINDOW_HEIGHT = 100;
    private final static int PIPE_WIDTH = 90;
    private final static float ADD_SPEED = -0.25f;
    private final static long SPEED_INCREASE_TIME = 5 * 1000000000L;

    private int ground_height;
    private int index;
    private float speed;
    private long timer;
    private long last_time;

    private boolean gameOver;
    private boolean reset;

    private Random r_generator;

    private Player player;
    private Ground[] grounds; //Array of ground entities
    private ArrayList<PipeUp> upPipes; //Array of pipe entities facing up
    private ArrayList<PipeDown> downPipes; //Array of pipe entities facing down
    private ArrayList<Entity> entities; //Array of all entities in game
    private Scoreboard scoreboard; //Scoreboard for handling score

    public GameState(Handler handler){
        super(handler);

        init();
    }

    private void init(){
        speed = STARTING_SPEED;

        //All entities
        entities = new ArrayList<>();

        //Height of the ground element
        ground_height = handler.getGame().getGround_height();

        //Initialize player
        int player_width = handler.getWidth() / 8;
        int player_height = (int)(player_width * 0.7);
        float player_x = handler.getWidth() / 2 - player_width / 2;
        float player_y = (handler.getHeight() - player_height) / 2;
        player = new Player(handler, player_x, player_y, player_width, player_height);

        //Initialize ground
        grounds = new Ground[2];
        grounds[0] = new Ground(handler, 0, handler.getHeight() - ground_height, handler.getWidth(), ground_height);
        grounds[0].setxSpeed(-speed);
        entities.add(grounds[0]);
        grounds[1] = new Ground(handler, handler.getWidth(), handler.getHeight() - ground_height, handler.getWidth(), ground_height);
        grounds[1].setxSpeed(-speed);
        entities.add(grounds[1]);

        //Random generator
        r_generator = new Random();

        downPipes = new ArrayList<>(N_PIPES / 2);
        upPipes = new ArrayList<>(N_PIPES / 2);

        //Initialize pipes
        for(int i = 0; i < N_PIPES / 2; i++) {
            int r_size = generateWindowSize();
            int r_height = generateWindowHeight(r_size, handler.getHeight() - ground_height);
            PipeDown pipeDown = new PipeDown(handler, DISTANCE_BETWEEN_PIPES + handler.getWidth() + i * DISTANCE_BETWEEN_PIPES, 0, PIPE_WIDTH, r_height);
            PipeUp pipeUp = new PipeUp(handler, DISTANCE_BETWEEN_PIPES + handler.getWidth() + i * DISTANCE_BETWEEN_PIPES, r_height + r_size, PIPE_WIDTH, handler.getHeight() - ground_height - r_height - r_size);
            pipeDown.setxSpeed(-STARTING_SPEED);
            pipeUp.setxSpeed(-STARTING_SPEED);
            downPipes.add(pipeDown);
            entities.add(pipeDown);
            upPipes.add(pipeUp);
            entities.add(pipeUp);
        }

        //Scoreboard
        scoreboard = new Scoreboard(handler, 56, 200, 337, 400);

        //Other variables
        index = 0;
        timer = 0;
        last_time = System.nanoTime();
    }

    @Override
    public void tick() {
        if(!player.isAlive()) //If player is dead
            endGame();
        //Gets input from the player
        get_input();

        //Ticks every entity
        if(!gameOver){
            player.tick();
            check_score();
            checkCollision();
            if(!gameOver){
                for(Entity entity : entities)
                    entity.tick();

                //Handles pipes
                randomize_pipes();

                //Increases speed of the game
                increase_speed();
            }
        }
        scoreboard.tick();
    }

    /**
     * Checks if player collides with pipes or ground.
     */
    private void checkCollision(){
        //Check collision with ground
        for(Ground ground : grounds) {
            if(player.checkCollision(ground.getBounds())){
                player.setY(ground.getY() - player.getHeight());
                endGame();
                return;
            }
        }

        //Check collision with pipe
        for(Pipe pipe : upPipes) {
            if(player.checkCollision(pipe.getBounds())){
                endGame();
                return;
            }
        }

        //Check collision with pipe
        for(Pipe pipe : downPipes) {
            if(player.checkCollision(pipe.getBounds())){
                endGame();
                return;
            }
        }
    }

    /**
     * Method that is executed when player dies
     */
    private void endGame(){
        gameOver = true;
        scoreboard.setGameOver(true);
    }

    /**
     * Gets input to switch states
     */
    private void get_input() {
        //If esc was pressed but space wasn't
        if(handler.getKeyManager().escape && !handler.getKeyManager().space) {
            handler.getGame().setCurrentState(new MenuState(handler)); //Go to the menu
        }
        //If space was pressed and game is over but esc wasn't
        else if(!handler.getKeyManager().escape && handler.getKeyManager().space && gameOver) {
            handler.getGame().setCurrentState(new GameState(handler)); // Restart game
        }
    }

    @Override
    public void render(Graphics g) {
        render_background(g);

        player.render(g);

        //Renders every entity
        for(Entity entity : entities)
            entity.render(g);

        scoreboard.render(g);
    }

    /**
     * Generates height at which pipe window will appear.
     * @param window_size Size of the window.
     * @param pipe_up_end Position of the end of the pipe.
     * @return Window height.
     */
    private int generateWindowHeight(int window_size, int pipe_up_end) {
        return r_generator.nextInt(pipe_up_end - window_size - MIN_WINDOW_HEIGHT - MAX_WINDOW_HEIGHT + 1) + MIN_WINDOW_HEIGHT;
    }

    /**
     * Generates pipe window size.
     * @return Pipe window size.
     */
    private int generateWindowSize() {
        return r_generator.nextInt(MAX_WINDOW_SIZE - MIN_WINDOW_SIZE + 1) + MIN_WINDOW_SIZE;
    }

    /**
     * If pipe moves out of sight then place it at the back and randomize window height and size.
     */
    private void randomize_pipes() {
        PipeUp first_pipe_up = upPipes.get(0);
        PipeDown first_pipe_down = downPipes.get(0);
        if(first_pipe_up.getX() + first_pipe_up.getWidth() <= 0) {
            //Remove first elements from the list
            upPipes.remove(0);
            downPipes.remove(0);

            //Move pipes to the end of the queue
            first_pipe_up.setX(first_pipe_up.getX() + N_PIPES / 2 * DISTANCE_BETWEEN_PIPES);
            first_pipe_down.setX(first_pipe_down.getX() + N_PIPES / 2 * DISTANCE_BETWEEN_PIPES);

            //Generate pipe properties
            int r_size = generateWindowSize();
            int r_height = generateWindowHeight(r_size, handler.getHeight() - ground_height);

            //Change pipe window height and size with generated values
            first_pipe_up.setY(r_height + r_size);
            first_pipe_up.setHeight(handler.getHeight() - ground_height - r_height - r_size);
            first_pipe_down.setHeight(r_height);

            //Add pipes to the end of the list
            upPipes.add(first_pipe_up);
            downPipes.add(first_pipe_down);


            index--;
        }
    }

    /**
     * Increases speed of every moving object every few seconds.
     */
    private void increase_speed() {
        long now_time = System.nanoTime();
        long delta = now_time - last_time;
        timer += delta;
        last_time = now_time;

        //If SPEED_INCREASE_TIME has passed
        if(timer > SPEED_INCREASE_TIME) {
            //Reset timer
            timer = 0;

            //Add to speed
            float speed = upPipes.get(0).getxSpeed();
            speed += ADD_SPEED;

            //Set speed for pipes and ground
            for(PipeUp pipe : upPipes) {
                pipe.setxSpeed(speed);
            }
            for(PipeDown pipe : downPipes) {
                pipe.setxSpeed(speed);
            }
            for(Ground ground : grounds) {
                ground.setxSpeed(speed);
            }
        }
    }

    /**
     * Increments score if player gets past pipe.
     */
    private void check_score(){
        //If player has passed pipe
        if(player.getX() > upPipes.get(index).getX() + upPipes.get(index).getWidth()) {
            scoreboard.score++;
            index++;
        }
    }

    /**
     * Renders images in the background.
     * @param g Graphics object.
     */
    private void render_background(Graphics g) {
        g.drawImage(Assets.sky, 0, 0, handler.getWidth(), handler.getHeight() - 3 * ground_height, null);
        g.drawImage(Assets.background, 0, handler.getHeight() - 3 * ground_height, handler.getWidth(), 2 * ground_height,null);
    }

    //Getters and setters

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
