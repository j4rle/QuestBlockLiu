package menus;


import gamestate.EndType;
import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;

/**
 * Menu that appears when the player has finished a game
 */
@SuppressWarnings("RefusedBequest")//Intentional as we want to override functions in superclass
public class EndMenu extends MenuState{
    private EndType endType;

    /**
     * Creates end menu based on the outcome of a game
     * @param gameStateControl1 game state control associated with game
     * @param endType if the player finished the level or died
     */
    public EndMenu(GameStateControl gameStateControl1, EndType endType) {
        super(gameStateControl1);
        this.endType = endType;
        init();
    }

    public void init(){
        switch (endType){
            case VICTORY:
                victoryInit();
                break;
            case DEATH:
                deathInit();
                break;
            default:
                deathInit();
                break;
        }

    }

    private void victoryInit(){
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

    private void deathInit(){
        final int fontsize = 24;
        this.currentChoice = 0;
        this.background = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, fontsize);
        this.headline = "You died! Try avoiding the water next time.";
        this.options = new String[] {"Restart level","Main Menu"};
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
