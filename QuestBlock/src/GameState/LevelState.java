package GameState;

import Entities.Player;
import Game.GamePanel;
import Tiles.Background;
import Tiles.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelState extends GameState{

    protected TileMap tileMap;
    protected Player player;
    protected Background bg;

    protected int xmax;
    protected int ymax;
    protected int xmin;
    protected int ymin;

    protected int tileSize;
    protected int playerSize;


    public void init(){

    }

    public void update() {
        player.update();

        tileMap.setFPS(GamePanel.FPS);
        if(player.getX() < xmax || player.getX() > xmin){
            tileMap.setX((int) (GamePanel.WIDTH / 2 - player.getX()));}
        if(player.getY() > GamePanel.HEIGHT / 2 && player.getY() != 0) {
            tileMap.setY((int) (GamePanel.HEIGHT / 2 - player.getY()));
        }
    }


    public void draw(Graphics2D g) {
        bg.draw(g);
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
            gsm.setPaused(gsm.getGameState()); //this allows us to "save" the current state of the game
            gsm.setGameState(GameStateManager.PAUSESTATE); //Pause screen
        }
        if(key == KeyEvent.VK_F){
            tileMap.setShowFPS(!tileMap.ShowFPS());
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
