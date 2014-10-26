package game;

import java.util.*;

public class HighScore implements Comparable<HighScore>{
    private String name;
    private int score;

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
        return name.compareTo(highScore.getName());
    }


    public String toString(){
        return name + " : " + score;
    }

    public static class ScoreComparator implements Comparator<HighScore>{
        public int compare(HighScore h1, HighScore h2){
            int score1 = h1.getScore();
            int score2 = h2.getScore();

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
