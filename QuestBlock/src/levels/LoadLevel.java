package levels;

import entities.Player;
import game.GamePanel;
import game.HighScore;
import gamestate.GameStateControl;
import gamestate.LevelState;
import tiles.Background;
import tiles.TileMap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Loads a level based on what level was chosen in the menu
 */
@SuppressWarnings("RefusedBequest") //overrides method in superclass on purpose
public class LoadLevel extends LevelState{
    protected LevelType levelChoice;

    /**
     *
     * @param gameStateControl game state controller associated with the game
     * @param levelChoice level choice the player made
     */
    public LoadLevel(GameStateControl gameStateControl, LevelType levelChoice) {
        super(gameStateControl);
        this.levelChoice = levelChoice;
        init();
    }

    @Override
    public void init(){
        loadAssets(levelChoice);
        startTime = System.currentTimeMillis();
    }

    private void cameraBounds(){
        this.xmax = tileMap.getXmax();
        this.xmin = tileMap.getXmin();
        this.ymax = tileMap.getYmax();
        this.ymin = tileMap.getYmin();
    }

    private void initPlayer(){
        this.playerSize = tileSize - PLAYER_SIZE_OFFSET;
        this.player = new Player(tileMap, playerSize);
        this.player.setX(START_X);
        this.player.setY(START_Y);
        this.player.setFlying(false);
    }

    private void loadAssets(LevelType level){

        switch (level){
            case RANDOMIZER:
                if(gameStateControl.getPaused() != LevelType.RANDOMIZER) {
                    initRandomizer();
                    break;
                }
                break;
            case LEVEL1:
                if(this.gameStateControl.getPaused() != LevelType.LEVEL1) {
                    initLevel1();
                }
                break;
            case LEVEL2:
                if(gameStateControl.getPaused() != LevelType.LEVEL2) {
                    initLevel2();
                }
                break;
        }
    }

    private void addHighScoreEntry(String combination){
        String delimiter = ":";
        String name = combination.split(delimiter)[0];
        int score = Integer.parseInt(combination.split(delimiter)[1]);

        HighScore tempScore = new HighScore(name, score);
        gameStateControl.addHighscore(tempScore);

    }

    private void loadHighScores(LevelType level){
        gameStateControl.clearHighscores();
        ClassLoader classloader = getClass().getClassLoader();

        switch (level){
            case LEVEL1:
                filename = "lvl1hs.txt";
                break;
            case LEVEL2:
                filename = "lvl2hs.txt";
                break;
            case RANDOMIZER:
                filename = "rndmhs.txt";
                break;
            default:
                System.out.println("no highscore file found");
        }

        URL url = classloader.getResource(filename);
        if(url == null){
            System.out.println("Error loading file "+filename);
        }
        else{
            File file = new File(url.getFile());
            try(Scanner scanner = new Scanner(file)){
                while(scanner.hasNext()){
                    addHighScoreEntry(scanner.next());
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    private void initLevel1(){
        this.background = new Background("/lvl1background.png");
        this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
        this.tileMap = new TileMap("/level1.txt", tileSize);

        loadHighScores(LevelType.LEVEL1);
        initPlayer();
        cameraBounds();
    }

    private void initLevel2(){
        this.background = new Background("/lvl1background.png");
        this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
        this.tileMap = new TileMap("/level2.txt", tileSize);

        loadHighScores(LevelType.LEVEL2);
        initPlayer();
        cameraBounds();
    }
    private void initRandomizer(){
        this.background = new Background("/lvl1background.png");
        this.tileSize = GamePanel.HEIGHT / TILE_SCALE;
        this.tileMap = new TileMap("/level2.txt", tileSize);
        this.randomize = true;

        loadHighScores(LevelType.RANDOMIZER);
        initPlayer();
        cameraBounds();
    }
}
