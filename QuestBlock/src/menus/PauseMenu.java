package menus;

import gamestate.GameStateControl;
import gamestate.MenuState;
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
