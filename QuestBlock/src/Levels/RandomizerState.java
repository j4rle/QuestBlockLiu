package Levels;

import Entities.Player;
import Game.GamePanel;
import GameState.GameStateManager;
import Tiles.Background;
import Tiles.TileMap;
import GameState.LevelState;

public class RandomizerState extends LevelState {

    public RandomizerState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        if(gsm.getPaused() != GameStateManager.RANDOMIZERSTATE) {
            //map design
            this.bg = new Background("/lvl1background.png");
            this.tileSize = GamePanel.HEIGHT / 12;
            this.tileMap = new TileMap("/level2.txt", 40);
            this.tileMap.setRandomized(true, 1, 10);

            //player properties
            this.playerSize = tileSize - 15;
            this.player = new Player(tileMap, playerSize);
            this.player.setX(55);
            this.player.setY(55);
            this.player.setFlying(true);
            this.player.setSlidingSpeed(5);

            //camera boundary properties
            this.xmax = tileMap.getXmax();
            this.xmin = tileMap.getXmin();
            this.ymax = tileMap.getYmax();
            this.ymin = tileMap.getYmin();
        }
    }

    public void update() {
        player.update();
        tileMap.setPlayer(player.getX(),player.getY());
        tileMap.update();

        tileMap.setFPS(GamePanel.FPS);
        if(player.getX() < xmax || player.getX() > xmin){
            tileMap.setX((int) (GamePanel.WIDTH / 2 - player.getX()));}
        if(player.getY() > GamePanel.HEIGHT / 2 && player.getY() != 0) {
            tileMap.setY((int) (GamePanel.HEIGHT / 2 - player.getY()));
        }
    }
}
