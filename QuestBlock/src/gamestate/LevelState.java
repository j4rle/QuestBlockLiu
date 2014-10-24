package gamestate;

import entities.Player;
import game.GamePanel;
import tiles.Background;
import tiles.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

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
    protected long score = -1;
    protected long startTime = -1;
    protected long finishTime = -1;

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

    public void init(){
    }

    public void update() {
        player.update();
        tileMap.update();
        if(tileMap.isVictory()){
            finishTime = System.currentTimeMillis();
            score =  finishTime - startTime;;
            gameStateControl.setScore(score);
            System.out.println("Score: "+ score);
            gameStateControl.setPaused(gameStateControl.getGameState());
            gameStateControl.setGameState((GameStateControl.VICTORYSTATE));
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

    public void draw(Graphics2D g) {
        background.draw(g);
        tileMap.draw(g);
        player.draw(g);

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
