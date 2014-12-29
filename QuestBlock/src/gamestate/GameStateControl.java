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
public class GameStateControl {

    //containers
    private TreeMap<LevelType, GameState> gameStates = new TreeMap<>(); //map of the different game states in the game
	private LevelType currentState = LevelType.MAINMENU; //keep track of the current active level
    private LevelType currentPaused = LevelType.MAINMENU; //keep track of paused level
    private List<HighScore> highScoreList = new ArrayList<>();

    //player info
    private long score = 0;
    private String playerName ="default";

	public GameStateControl(){

        //menus
        gameStates.put(LevelType.MAINMENU, new MainMenu(this));
        gameStates.put(LevelType.PAUSE, new PauseMenu(this));
        gameStates.put(LevelType.LEVELSELECT, new LevelSelectMenu(this));

        //levels
        gameStates.put(LevelType.LEVEL1, new LoadLevel(this, LevelType.LEVEL1)); //level 1
        gameStates.put(LevelType.LEVEL2, new LoadLevel(this, LevelType.LEVEL2)); //level 2
        gameStates.put(LevelType.RANDOMIZER, new LoadLevel(this, LevelType.RANDOMIZER)); //randomize-level

        //victory screen
        gameStates.put(LevelType.VICTORY, new EndMenu((this), LevelType.VICTORY));
        gameStates.put(LevelType.DEATH, new EndMenu((this), LevelType.DEATH));
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

    public LevelType getGameState(){
        return currentState;
    }

    public LevelType getPaused(){
        return currentPaused;
    }

	public void setGameState(LevelType state){
		currentState = state;
		gameStates.get(currentState).init();
	}

    public void setPaused(LevelType state){
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
