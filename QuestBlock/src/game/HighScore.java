package game;

import java.util.*;

/**
 * Class that stores game scores as objects
 */
public class HighScore implements Comparable<HighScore>{
    private String name;
    private int score;

    /**
     * @param name name of player
     * @param score score achieved
     */

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }


    public int compareTo(HighScore highScore){
        return name.compareTo(highScore.name);
    }

    public String toString(){
        return name + " : " + score;
    }

    public static class ScoreComparator implements Comparator<HighScore>{
        public int compare(HighScore highScore1, HighScore highScore2){
            int score1 = highScore1.getScore();
            int score2 = highScore2.getScore();

            if(score1 == score2){
                return 0;
            }
            else if(score1 > score2){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

}
