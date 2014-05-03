package Menus;


import Game.GamePanel;
import GameState.GameStateManager;
import GameState.MenuState;
import Tiles.Background;

import java.awt.*;

public class MainMenu extends MenuState {

    private boolean help; //toggles help

    //strings needed for main menu
	private String[] helpText = {"Use A and D for movement","Use Space or W for jump", "- By Cian and Jarle"};


	public MainMenu(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {
        this.currentChoice = 0;
        this.options = new String[] {"Start","Help","Quit"};
        bg = new Background("/background.png");
        bg.setVector(-0.3,0);
		this.font = new Font("Century Gothic", Font.PLAIN, 24);
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {
		//Background
		bg.draw(g);

		//Menu options
		g.setFont(font);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
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

	public void select() {
		if(currentChoice == 0){
            gsm.setPaused(GameStateManager.MAINMENUSTATE);
			gsm.setGameState(GameStateManager.LEVELSELECT);
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
