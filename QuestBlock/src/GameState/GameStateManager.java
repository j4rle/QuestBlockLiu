package GameState;

import Levels.Level1State;
import Levels.Level2State;
import Levels.RandomizerState;
import Menus.LevelSelectMenu;
import Menus.MainMenu;
import Menus.PauseMenu;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState>gameStates;
	private int currentState = 0;
    private int currentPaused = 0;

	public static final int MAINMENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int LEVELSELECT = 2;
	public static final int LEVEL1STATE = 3;
	public static final int LEVEL2STATE = 4;
	public static final int RANDOMIZERSTATE = 5;

	public GameStateManager(){
		gameStates = new ArrayList<>();

        //Menus
		gameStates.add(new MainMenu(this));
        gameStates.add(new PauseMenu(this));
        gameStates.add(new LevelSelectMenu(this));

        //Levels
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
