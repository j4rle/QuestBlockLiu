package Menus;

import Game.GamePanel;
import GameState.GameStateManager;
import GameState.MenuState;
import Tiles.Background;

import java.awt.*;

public class PauseMenu extends MenuState {

    public PauseMenu(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }
    public void init(){
        this.currentChoice = 0;
        this.bg = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, 24);
        this.options = new String[] {"Resume","Restart","Main Menu"};
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        //background
        this.bg.draw(g);

        //options
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        for (int i = 0; i < options.length; i++) {
            if(i == currentChoice){
                g.setColor(Color.white);
            }
            else{
                g.setColor(Color.GRAY);
            }
            g.drawString(options[i], 50, GamePanel.HEIGHT - 100 + i * 30);
        }
    }

    public void select() {
        if(currentChoice == 0){
            gsm.setGameState(gsm.getPaused());
        }
        if(currentChoice == 1){
            int foo = gsm.getPaused();
            gsm.setPaused(0);
            gsm.setGameState(foo);
        }
        if(currentChoice == 2){
            gsm.setGameState(GameStateManager.MAINMENUSTATE);
        }
    }

    public void keyReleased(int k) {
    }
}
