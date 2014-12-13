package gamestate;

import entities.Player;
import game.GamePanel;
import game.HighScore;
import tiles.Background;
import tiles.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * The superclass for different level states
 */
public class LevelState implements GameState{

    protected GameStateControl gameStateControl;

    protected TileMap tileMap;
    protected Player player;
    protected Background background;


    protected int xmax;
    protected int ymax;
    protected int xmin;
    protected int ymin;

    protected int playerSize;
    protected int score = -1;
    protected long startTime = -1;
    protected long finishTime = -1;
    protected String filename = "";
    protected boolean randomize = false;

    protected static final int TILE_SCALE = 12;
    protected int tileSize;
    protected static final int PLAYER_SIZE_OFFSET = 15;
    protected static final int START_X = 55;
    protected static final int START_Y = 55;

    public LevelState(GameStateControl gameStateControl) {
        this.gameStateControl = gameStateControl;
        this.tileMap = null;
        this.player = null;
        this.background = null;
    }

    private void victoryActions(){
        finishTime = System.currentTimeMillis();
        score = (int)(finishTime - startTime);
        gameStateControl.setScore(score);


        HighScore playerScore = new HighScore(gameStateControl.getPlayerName(),score);
        gameStateControl.addHighscore(playerScore);

        gameStateControl.setPaused(gameStateControl.getGameState());
        gameStateControl.setGameState((GameStateControl.VICTORYSTATE));


        ClassLoader classloader = getClass().getClassLoader();
        URL url = classloader.getResource(filename);
        assert url != null;
        try(FileWriter fileWriter = new FileWriter(url.getFile(), true)){
                fileWriter.write(playerScore.getName()+":"+playerScore.getScore()+"\n"); //append score to text file
        }catch (IOException ioe){
            System.err.println("IOException: "+ ioe.getMessage());
        }
    }

    public void init(){
    }

    public void update() {
        player.update();
        tileMap.update();
        if(randomize){
            tileMap.randomize();
        }
        if(tileMap.isVictory()){
            victoryActions();
        }
        if(player.isDead()){
            gameStateControl.setPaused(gameStateControl.getGameState());
            gameStateControl.setGameState((GameStateControl.DEATHSTATE));
        }

        if(player.getX() < xmax || player.getX() > xmin){
            tileMap.setX((int) (GamePanel.WIDTH / 2 - player.getX()));}
        if(player.getY() > GamePanel.HEIGHT / 2 && player.getY() != 0) {
            tileMap.setY((int) (GamePanel.HEIGHT / 2 - player.getY()));
        }
    }

    public void draw(Graphics2D graphics) {
        background.draw(graphics);
        tileMap.draw(graphics);
        player.draw(graphics);

    }

    public void keyPressed(int key) {
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            player.setLeft(true);
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            player.setRight(true);
        }
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP){
            player.setJumping(true);
        }
        if(key == KeyEvent.VK_SHIFT){
            player.setSprint(true);
        }
        if(key == KeyEvent.VK_ESCAPE){
            gameStateControl.setPaused(gameStateControl.getGameState()); //this allows us to "save" the current state of the game
            gameStateControl.setGameState(GameStateControl.PAUSESTATE); //Pause screen
        }
    }

    public void keyReleased(int key) {

        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            player.setLeft(false);
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            player.setRight(false);
        }
        if(key == KeyEvent.VK_SHIFT){
            player.setSprint(false);
        }
    }
}
