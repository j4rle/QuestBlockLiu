package GameState;


import Game.GamePanel;
import Tiles.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{


	private int currentChoice;
	private String[] options = {
			"Start","Help","Quit"
	};
	private String[] helpText = {
			"Use A and D for movement","Use Space or W for jump", "- By Cian and Jarle"
	};

	private Background bg;
	private Font font;
	private boolean help;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		bg = new Background("/background.png", 0.1);
		bg.setVector(-0.3,0);

		font = new Font("Century Gothic", Font.PLAIN, 24);
	}

	public void init() {
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {
		//Background
		bg.draw(g);

		//Menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice){
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.GRAY);
			}
			g.drawString(options[i], 50, GamePanel.HEIGHT - 100 + i * 30);
		}

		//help instructions
		if(help) {
			for (int i = 0; i < helpText.length; i++) {
				g.setColor(Color.white);
				g.drawString(helpText[i], GamePanel.WIDTH/3,  GamePanel.HEIGHT - 100 + i * 30);
			}
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE) {
			select();
		}
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setGameState(GameStateManager.LEVEL1STATE);
		}
		if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W) {
			currentChoice--;
			if(currentChoice <= -1){
				currentChoice = options.length -1;
			}

		}
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S) {
			currentChoice++;
			if(currentChoice >= options.length){
				currentChoice = 0;
			}
		}
	}

	private void select() {
		if(currentChoice == 0){
			gsm.setGameState(GameStateManager.LEVEL1STATE);
		}
		if(currentChoice == 1){
			help = !help;

		}
		if(currentChoice == 2){
			System.exit(0);
		}
	}

	public void keyReleased(int k) {

	}
}
