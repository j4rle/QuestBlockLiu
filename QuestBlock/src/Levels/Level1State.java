package Levels;

import Entities.Player;
import Game.GamePanel;
import GameState.GameStateManager;
import GameState.LevelState;
import Tiles.Background;
import Tiles.TileMap;

public class Level1State extends LevelState {


	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		if(!(gsm.getPaused() == GameStateManager.LEVEL1STATE)) {
            //map design
			this.bg = new Background("/lvl1background.png");
			this.tileSize = GamePanel.HEIGHT / 12;
			this.tileMap = new TileMap("/level1.txt", 40);

            //player properties
            this.playerSize = tileSize - 15;
			this.player = new Player(tileMap, playerSize);
			this.player.setX(55);
			this.player.setY(55);
            this.player.setFlying(false);

            //camera boundary properties
			this.xmax = tileMap.getXmax();
			this.xmin = tileMap.getXmin();
			this.ymax = tileMap.getYmax();
			this.ymin = tileMap.getYmin();
		}
	}
}
