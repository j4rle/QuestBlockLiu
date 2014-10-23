package menus;

import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

@SuppressWarnings({"RefusedBequest", "HardcodedFileSeparator"})
//magic numbers used as the game doesn't scale with monitor size.
//Overriding methods in superclass is intentional
//File.separator doesn't work for getResourceAsStream.


/**
 * The menu for selecting level
 */
public class LevelSelectMenu extends MenuState {

    /**
     *
     * @param gsc game state control for the menu
     */
    public LevelSelectMenu(GameStateControl gsc) {
        super(gsc);
        init();
    }

    @Override
    public void init(){
        final int fontsize = 24;
        this.currentChoice = 0;
        background = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, fontsize);
        this.options = new String[] {"Level 1","Level 2","Randomizer","Main Menu"};
    }

    @Override
    public void update() {
        background.update();
    }



    @Override
    public void select() {
        final int LEVEL1 = 0;
        final int LEVEL2 = 1;
        final int RANDOMIZER = 2;
        final int MAINMENU = 3;

        switch (currentChoice){
            case LEVEL1:
                gameStateControl.setGameState(GameStateControl.LEVEL1STATE);
                break;
            case LEVEL2:
                gameStateControl.setGameState(GameStateControl.LEVEL2STATE);
                break;
            case RANDOMIZER:
                gameStateControl.setGameState(GameStateControl.RANDOMIZERSTATE);
                break;
            case MAINMENU:
                gameStateControl.setGameState(GameStateControl.MAINMENUSTATE);
        }
    }
}
