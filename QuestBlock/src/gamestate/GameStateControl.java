package gamestate;

import levels.LevelType;
import levels.LoadLevel;
import menus.*;

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
    private long score = 0;

	public static final int MAINMENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int LEVELSELECT = 2;
	public static final int LEVEL1STATE = 3;
	public static final int LEVEL2STATE = 4;
	public static final int RANDOMIZERSTATE = 5;
	public static final int VICTORYSTATE = 6;
	public static final int DEATHSTATE = 7;

	public GameStateControl(){
		gameStates = new ArrayList<>();

        //menus
		gameStates.add(new MainMenu(this));
        gameStates.add(new PauseMenu(this));
        gameStates.add(new LevelSelectMenu(this));

        //levels
		gameStates.add(new LoadLevel(this, LevelType.LEVEL1)); //level 1
        gameStates.add(new LoadLevel(this, LevelType.LEVEL2)); //level 2
        gameStates.add(new LoadLevel(this, LevelType.RANDOMIZER)); //randomization-level

        //victory screen
        gameStates.add(new VictoryMenu(this));
        gameStates.add(new DeathMenu(this));
	}

    public void setScore(long score) {
        this.score = score;
    }

    public long getScore() {
        return score;
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
