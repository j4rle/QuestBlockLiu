package menus;


import game.HighScore;
import gamestate.GameStateControl;
import gamestate.MenuState;
import tiles.Background;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class VictoryMenu extends MenuState {

    public VictoryMenu(GameStateControl gsc) {
        super(gsc);
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

        scoreboard = new String[3];
        gameStateControl.sortHighscores();
        for (int i = 0; i < 3; i++) {
            String temp = "";
            temp += i+1+ ": ";
            temp += gameStateControl.getName(i) + " - ";
            temp += gameStateControl.getScore(i);

            scoreboard[i] = temp;
        }
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
