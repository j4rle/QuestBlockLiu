package levels;

import entities.Player;
import game.GamePanel;
import gamestate.GameStateControl;
import gamestate.LevelState;
import tiles.Background;
import tiles.TileMap;

@SuppressWarnings("HardcodedFileSeparator")
//File.separator doesn't work for getResourceAsStream.


/**
 * The state for level 2
 */
public class Level2State extends LevelState {

    /**
     * Level 2
     * @param gsc game state control for the level
     */
    public Level2State(GameStateControl gsc) {
        super(gsc);
        init();
    }

    @Override
    public void init() {
        if(gameStateControl.getPaused() != GameStateControl.LEVEL2STATE) {
            //map design
            this.background = new Background("/lvl1background.png");
            this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
            this.tileMap = new TileMap("/level2.txt", tileSize);

            //player properties
            this.playerSize = tileSize - PLAYER_SIZE_OFFSET;
            this.player = new Player(tileMap, playerSize);
            this.player.setX(START_X);
            this.player.setY(START_Y);
            this.player.setFlying(true);

            //camera boundary properties
            this.xmax = tileMap.getXmax();
            this.xmin = tileMap.getXmin();
            this.ymax = tileMap.getYmax();
            this.ymin = tileMap.getYmin();
        }
    }
}
