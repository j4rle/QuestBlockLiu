package GameState;

import Entities.Player;
import Game.GamePanel;
import Tiles.Background;
import Tiles.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Player player;
	private int xmax;
	private int ymax;
	private int xmin;
	private int ymin;
	private boolean wasPaused = false;
	private int tileSize;
	private int playerSize;
	private Background bg;

	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
		if(!wasPaused) {
			bg = new Background("/lvl1background.png", 0);
			this.tileSize = GamePanel.HEIGHT / 12;
			this.playerSize = tileSize - 15;
			tileMap = new TileMap("testmap.txt", 40);
			player = new Player(tileMap, playerSize);
			player.setX(55);
			player.setY(55);

			this.xmax = tileMap.getXmax();
			this.xmin = tileMap.getXmin();
			this.ymax = tileMap.getYmax();
			this.ymin = tileMap.getYmin();
		}

	}

	public void update() {
		player.update();
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
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE){
			player.setJumping(true);
		}
		if(key == KeyEvent.VK_W){
			player.setSprint(true);
		}
		if(key == KeyEvent.VK_ESCAPE){
			wasPaused = true; //this allows us to "save" the current state of the game
			gsm.setGameState(GameStateManager.MENUSTATE);
		}
	}

	public void keyReleased(int key) {

		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
	}
}
