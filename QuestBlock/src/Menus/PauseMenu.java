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
        if(currentChoice == 0){
            gameStateControl.setGameState(gameStateControl.getPaused());
        }
        if(currentChoice == 1){
            int currentPausedInt = gameStateControl.getPaused();
            gameStateControl.setPaused(0);
            gameStateControl.setGameState(currentPausedInt);
        }
        if(currentChoice == 2){
            gameStateControl.setGameState(GameStateControl.MAINMENUSTATE);
        }
    }

}
