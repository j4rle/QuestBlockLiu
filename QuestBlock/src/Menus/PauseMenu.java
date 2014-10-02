package menus;

import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

@SuppressWarnings("RefusedBequest")
//Overriding methods in superclass is intentional
//File.separator doesn't work for getResourceAsStream.

/**
 * Pause menu for the game
 */
public class PauseMenu extends MenuState {


    /**
     *
     * @param gsc game state control for the menu
     */
    public PauseMenu(GameStateControl gsc) {
        super(gsc);
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

        final int RESUME = 0;
        final int RESTART = 1;
        final int MAINMENU = 2;

        switch (currentChoice){
            case RESUME:
                gameStateControl.setGameState(gameStateControl.getPaused());
                break;
            case RESTART:
                int currentPausedInt = gameStateControl.getPaused();
                gameStateControl.setPaused(0);
                gameStateControl.setGameState(currentPausedInt);
                break;
            case MAINMENU:
                gameStateControl.setGameState(GameStateControl.MAINMENUSTATE);
                break;
        }
    }

}
