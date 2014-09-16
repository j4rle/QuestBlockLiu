package levels;

import entities.Player;
import game.GamePanel;
import gamestate.GameStateControl;
import gamestate.LevelState;
import tiles.Background;
import tiles.TileMap;

@SuppressWarnings("HardcodedFileSeparator")//File.separator doesn't work for getResourceAsStream.

/**
 * Level 1 of the game
 */
public class Level1State extends LevelState {

    /**
     * level 1
     * @param gsc game state control for the level
     */

	public Level1State(GameStateControl gsc) {
		super(gsc);
		init();
	}

	public void init() {
		if(this.gameStateControl.getPaused() != GameStateControl.LEVEL1STATE) {
            //map design
            String background = "/lvl1background.png";
			this.background = new Background(background);
			this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
			this.tileMap = new TileMap("/level1.txt", tileSize);

            //player properties
            this.playerSize = tileSize - PLAYER_SIZE_OFFSET;
			this.player = new Player(tileMap, playerSize);
			this.player.setX(START_X);
			this.player.setY(START_Y);
            this.player.setFlying(false);

            //camera boundary properties
			this.xmax = tileMap.getXmax();
			this.xmin = tileMap.getXmin();
			this.ymax = tileMap.getYmax();
			this.ymin = tileMap.getYmin();
		}
	}
}
