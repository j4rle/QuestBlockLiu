package menus;


import game.GamePanel;
import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

@SuppressWarnings("ALL")
//all "magic numbers" are explained through variable name
//Overriding methods in superclass is intentional
//File.separator doesn't work for getResourceAsStream.


/**
 * The main menu of the game
 */
public class MainMenu extends MenuState {

    private boolean help; //toggles help

    //strings needed for main menu
	private String[] helpText = {"Reach the green block in","as little time as possible", "without drowning!"};

    /**
     * The main menu of the game
     * @param gameStateControl1 game state controller associated with the game
     */
	public MainMenu(GameStateControl gameStateControl1) {
		super(gameStateControl1);
		init();
	}

    @Override
	public void init() {
        final int fontsize = 24;
        final double backgroundSpeed = -0.3;
        this.currentChoice = 0;
        this.options = new String[] {"Start","Help","Quit"};
        background = new Background("/background.png");
        background.setVector(backgroundSpeed,0);
		this.font = new Font("Century Gothic", Font.PLAIN, fontsize);
	}



    @Override
	public void update() {
		background.update();
	}

    @Override
	public void draw(Graphics2D graphics) {
        final int optionscoordinate = 50;
        final int optionscale = 30;

		//Background
		background.draw(graphics);

		//Menu options
		graphics.setFont(font);
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice){
				graphics.setColor(Color.white);
			}
			else{
				graphics.setColor(Color.GRAY);
			}
			graphics.drawString(options[i], optionscoordinate, GamePanel.HEIGHT - 100 + i * optionscale);
		}

		//help instructions
		if(help) {
			for (int i = 0; i < helpText.length; i++) {
				graphics.setColor(Color.white);
				graphics.drawString(helpText[i], GamePanel.WIDTH/3,  GamePanel.HEIGHT - 100 + i * optionscale);
			}
		}
	}

    @Override
	public void select() {
        final int START = 0;
        final int HELP = 1;
        final int QUIT = 2;

        switch (currentChoice){
            case START:
                gameStateControl.setPaused(GameStateControl.MAINMENUSTATE);
                gameStateControl.setGameState(GameStateControl.LEVELSELECT);
                break;
            case HELP:
                help = !help;
                break;
            case QUIT:
                System.exit(0);
        }
	}
}
