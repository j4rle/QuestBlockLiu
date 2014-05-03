package GameState;


import GameState.GameState;
import Tiles.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    protected Background bg;
    protected Font font;
    protected int currentChoice = 0;
    protected String[] options;


    public void init() {
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
    }

    public void select(){

    }


    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE) {
            select();
        }
        if (k == KeyEvent.VK_ESCAPE) {
            if(gsm.getPaused() != 0) {
                gsm.setGameState(gsm.getPaused());
            }
        }
        if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W) {
            currentChoice--;
            if(currentChoice <= -1){
                currentChoice = options.length -1;
            }

        }
        if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S) {
            currentChoice++;
            if(currentChoice >= options.length){
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {
    }

}
