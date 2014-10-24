package menus;


import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

public class DeathMenu extends MenuState {

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
        this.headline = "You died!";
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
