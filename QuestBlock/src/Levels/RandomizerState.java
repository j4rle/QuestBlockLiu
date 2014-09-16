package levels;

import entities.Player;
import game.GamePanel;
import gamestate.GameStateControl;
import tiles.Background;
import tiles.TileMap;
import gamestate.LevelState;

@SuppressWarnings({"RefusedBequest", "HardcodedFileSeparator"})
/**
 * A randomized level
 */
public class RandomizerState extends LevelState {


    /**
     *
     * @param gsc game state control for the level
     */
    public RandomizerState(GameStateControl gsc) {
        super(gsc);
        init();
    }

    @Override
    public void init() {
        if(gameStateControl.getPaused() != GameStateControl.RANDOMIZERSTATE) {
            //map design
            this.background = new Background("/lvl1background.png");
            this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
            this.tileMap = new TileMap("/level2.txt", tileSize);
            this.tileMap.setRandomized(true, 1, 100);

            //player properties
            this.playerSize = tileSize - PLAYER_SIZE_OFFSET;
            this.player = new Player(tileMap, playerSize);
            this.player.setX(START_X);
            this.player.setY(START_Y);
            this.player.setFlying(true);
            this.player.setSlidingSpeed(5);

            //camera boundary properties
            this.xmax = tileMap.getXmax();
            this.xmin = tileMap.getXmin();
            this.ymax = tileMap.getYmax();
            this.ymin = tileMap.getYmin();
        }
    }

    @Override
    public void update() {
        player.update();
        tileMap.update();

        if(player.getX() < xmax || player.getX() > xmin){
            tileMap.setX((int) (GamePanel.WIDTH / 2 - player.getX()));}
        if(player.getY() > GamePanel.HEIGHT / 2 && player.getY() != 0) {
            tileMap.setY((int) (GamePanel.HEIGHT / 2 - player.getY()));
        }
    }
}
