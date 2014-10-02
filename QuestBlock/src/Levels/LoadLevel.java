package levels;

import entities.Player;
import game.GamePanel;
import gamestate.GameStateControl;
import gamestate.LevelState;
import tiles.Background;
import tiles.TileMap;

public class LoadLevel extends LevelState{
    protected LevelType levelChoice;

    public LoadLevel(GameStateControl gameStateControl, LevelType levelChoice) {
        super(gameStateControl);
        this.levelChoice = levelChoice;
        init();
    }

    @Override
    public void init(){
        loadAssets(levelChoice);
    }

    private void cameraBounds(){
        this.xmax = tileMap.getXmax();
        this.xmin = tileMap.getXmin();
        this.ymax = tileMap.getYmax();
        this.ymin = tileMap.getYmin();
    }

    private void initPlayer(){
        this.playerSize = tileSize - PLAYER_SIZE_OFFSET;
        this.player = new Player(tileMap, playerSize);
        this.player.setX(START_X);
        this.player.setY(START_Y);
        this.player.setFlying(false);
    }

    private void loadAssets(LevelType level){

        switch (level){
            case RANDOMIZER:
                if(gameStateControl.getPaused() != GameStateControl.RANDOMIZERSTATE) {
                    initRandomizer();
                }
            case LEVEL1:
                if(this.gameStateControl.getPaused() != GameStateControl.LEVEL1STATE) {
                    initLevel1();
                }
                break;
            case LEVEL2:
                if(gameStateControl.getPaused() != GameStateControl.LEVEL2STATE) {
                    initLevel2();
                }
        }
    }


    private void initLevel1(){
        this.background = new Background("/lvl1background.png");
        this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
        this.tileMap = new TileMap("/level1.txt", tileSize);

        initPlayer();
        cameraBounds();
    }

    private void initLevel2(){
        this.background = new Background("/lvl1background.png");
        this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
        this.tileMap = new TileMap("/level2.txt", tileSize);

        initPlayer();
        cameraBounds();
    }

    private void initRandomizer(){
        this.background = new Background("/background.png");
        this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
        this.tileMap = new TileMap("/level2.txt", tileSize);
        this.tileMap.setRandomized(true, 1, 100);

        initPlayer();
        cameraBounds();

        this.player.setFlying(true);
        this.player.setSlidingSpeed(5);
    }
}
