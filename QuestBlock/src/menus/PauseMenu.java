package menus;

import gamestate.GameStateControl;
import gamestate.LevelState;
import gamestate.MenuState;
import levels.LevelType;
import tiles.Background;

import java.awt.*;


/**
 * Pause menu for the game
 */
@SuppressWarnings("RefusedBequest") //overrides methods in superclass on purpose
public class PauseMenu extends MenuState {

    /**
     * Pause menu for the game
     * @param gameStateControl1 game state controller associated with the menu
     */
    public PauseMenu(GameStateControl gameStateControl1) {
        super(gameStateControl1);
        init();
    }

    @Override
    public void init(){
        final int fontsize = 24;
        this.currentChoice = 0;
        this.background = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, fontsize);
        this.options = new String[] {"Resume","Restart","Main Menu"};
    }

    @Override
    public void select() {

        final int resume = 0;
        final int restart = 1;
        final int mainmenu = 2;

        switch (currentChoice){
            case resume:
                gameStateControl.setGameState(gameStateControl.getPaused());
                break;
            case restart:
                LevelType currentPausedState = gameStateControl.getPaused();
                gameStateControl.setPaused(LevelType.MAINMENU);
                gameStateControl.setGameState(currentPausedState);
                break;
            case mainmenu:
                gameStateControl.setGameState(LevelType.MAINMENU);
                break;
        }
    }

}
