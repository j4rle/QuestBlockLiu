package menus;


import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

/**
 * Death menu for the game
 */
@SuppressWarnings("RefusedBequest") //overrides methods in superclass on purpose
public class DeathMenu extends MenuState {

    /**
     * Menu that shows up when player dies
     * @param gsc game state controller associated with the menu
     */
    public DeathMenu(GameStateControl gsc) {
        super(gsc);
        init();
    }

    @Override
    public void init(){
        final int fontsize = 24;
        this.currentChoice = 0;
        this.background = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, fontsize);
        this.headline = "You died! Try avoiding the water next time.";
        this.options = new String[] {"Restart level","Main Menu"};
    }

    @Override
    public void select() {

        final int RESTART = 0;
        final int MAINMENU = 1;

        switch (currentChoice){

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
