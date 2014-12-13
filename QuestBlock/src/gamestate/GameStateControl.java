package gamestate;

import game.HighScore;
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
    private String playerName ="";
    private java.util.List<HighScore> highScoreList = new ArrayList<>();
    /**
     * main menu of the game, index 0 in array
     */
	public static final int MAINMENUSTATE = 0;
    /**
     * pause menu of the game, index 1 in array
     */
	public static final int PAUSESTATE = 1;
    /**
     * level select menu of the game, index 2 in array
     */
	public static final int LEVELSELECT = 2;
    /**
     * level 1 of the game, index 3 in array
     */
	public static final int LEVEL1STATE = 3;
    /**
     * level 2 of the game, index 4 in array
     */
	public static final int LEVEL2STATE = 4;
    /**
     * randomizing level of the game, index 5 in array
     */
	public static final int RANDOMIZERSTATE = 5;
    /**
     * victory menu of the game, index 6 in array
     */
	public static final int VICTORYSTATE = 6;
    /**
     * death menu of the game, index 7 in array
     */
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
        gameStates.add(new EndMenu((this), EndType.VICTORY));
        gameStates.add(new EndMenu((this), EndType.DEATH));
	}

    public int getHighscoreCount(){
        return highScoreList.size();
    }

    public void sortHighscores(){
        Collections.sort(highScoreList, new HighScore.ScoreComparator());
    }

    public void addHighscore(HighScore highScore){
        highScoreList.add(highScore);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getName(int index){
        return highScoreList.get(index).getName();
    }

    public int getScore(int index){
        return highScoreList.get(index).getScore();
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void clearHighscores(){
        highScoreList.clear();
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
	public void draw(Graphics2D graphics){
		gameStates.get(currentState).draw(graphics);
	}

	public void keyPressed(int key){
		gameStates.get(currentState).keyPressed(key);
	}
	public void keyReleased(int key){
		gameStates.get(currentState).keyReleased(key);
	}

}
