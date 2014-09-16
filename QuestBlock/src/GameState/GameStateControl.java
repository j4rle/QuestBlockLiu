package gamestate;

import levels.Level1State;
import levels.Level2State;
import levels.RandomizerState;
import menus.LevelSelectMenu;
import menus.MainMenu;
import menus.PauseMenu;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Keeps track on which state the game is in. Can be several states, where the biggest difference is between the various menustates and levelstates.
 */
//The different states don't need further documentation
public class GameStateControl {

	private List<GameState> gameStates;
	private int currentState = 0;
    private int currentPaused = 0;

	public static final int MAINMENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int LEVELSELECT = 2;
	public static final int LEVEL1STATE = 3;
	public static final int LEVEL2STATE = 4;
	public static final int RANDOMIZERSTATE = 5;

	public GameStateControl(){
		gameStates = new ArrayList<>();

        //menus
		gameStates.add(new MainMenu(this));
        gameStates.add(new PauseMenu(this));
        gameStates.add(new LevelSelectMenu(this));

        //levels
		gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new RandomizerState(this));
	}

    public int getGameState(){
        return currentState;
    }

    public int getPaused(){
        return currentPaused;
    }

	public void setGameState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}

    public void setPaused(int state){
        currentPaused = state;
    }

	public void update(){
		gameStates.get(currentState).update();
	}
	public void draw(Graphics2D g){
		gameStates.get(currentState).draw(g);
	}

	public void keyPressed(int k){
		gameStates.get(currentState).keyPressed(k);
	}
	public void keyReleased(int k){
		gameStates.get(currentState).keyReleased(k);
	}

}
