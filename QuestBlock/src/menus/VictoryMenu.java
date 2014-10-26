package menus;


import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

/**
 * menu that appears when player finished a level
 */
@SuppressWarnings("RefusedBequest") //overrides methods in superclass on purpose
public class VictoryMenu extends MenuState {

    /**
     *
     * @param gameStateControl1 game state controller associated with the menu
     */
    public VictoryMenu(GameStateControl gameStateControl1) {
        super(gameStateControl1);
        init();
    }

    @Override
    public void init() {
        final int fontsize = 24;
        this.currentChoice = 0;
        this.background = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, fontsize);
        this.headline = "VICTORY! Your score was: " + gameStateControl.getScore();
        this.options = new String[]{"Restart level", "Main Menu"};

        if(gameStateControl.getHighscoreCount()>=3){
            scoreboard = new String[3];
            gameStateControl.sortHighscores();
            for (int i = 0; i < 3; i++) {
                scoreboard[i] = (i + 1) + ": " + gameStateControl.getName(i) + " - " + gameStateControl.getScore(i);
            }
        }

    }

    @Override
    public void select() {

        final int restart = 0;
        final int mainMenu = 1;

        switch (currentChoice){

            case restart:
                int currentPausedInt = gameStateControl.getPaused();
                gameStateControl.setPaused(0);
                gameStateControl.setGameState(currentPausedInt);
                break;
            case mainMenu:
                gameStateControl.setGameState(GameStateControl.MAINMENUSTATE);
                break;
        }
    }
}
