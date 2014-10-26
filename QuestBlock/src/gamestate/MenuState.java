package gamestate;


import game.GamePanel;
import tiles.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Menu superclass for the different types of menus
 */
public class MenuState implements GameState {

    protected GameStateControl gameStateControl;

    protected Background background;
    protected Font font;
    protected int currentChoice = 0;
    protected String[] options;
    protected String headline = "";
    protected String[] scoreboard = new String[0];

    public MenuState(GameStateControl gsc) {
        this.gameStateControl = gsc;
        this.background = null;
        this.font = null;
        this.options = null;
    }

    public void init() {
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        final int optioncoordinate = 50;
        final int optionscale = 30;
        final int scoreboardy = 200;
        //font properties
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        //background
        this.background.draw(g);


        //headline
        g.setColor(Color.yellow);
        g.drawString(headline, optioncoordinate,100);


        //options

        for (int i = 0; i < options.length; i++) {
            if(i == currentChoice){
                g.setColor(Color.white);
            }
            else{
                g.setColor(Color.GRAY);
            }
            g.drawString(options[i], optioncoordinate, GamePanel.HEIGHT - 100 + i * optionscale);
        }


        //scoreboard
        g.setColor(Color.white);
        for (int i = 0; i < scoreboard.length; i++) {
            g.drawString(scoreboard[i], optioncoordinate, scoreboardy + i * optionscale);
        }
    }

    public void select(){

    }


    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE) {
            select();
        }
        if (k == KeyEvent.VK_ESCAPE) {
            if(gameStateControl.getPaused() != 0) {
                gameStateControl.setGameState(gameStateControl.getPaused());
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
