package Menus;

import Game.GamePanel;
import GameState.GameStateManager;
import GameState.MenuState;
import Tiles.Background;

import java.awt.*;

public class LevelSelectMenu extends MenuState {

    public LevelSelectMenu(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init(){
        this.currentChoice = 0;
        bg = new Background("/lvl1background.png");
        this.font = new Font("Century Gothic", Font.PLAIN, 24);
        this.options = new String[] {"Level 1","Level 2","Randomizer","Main Menu"};
    }

    public void update() {
        bg.update();
    }

    public void draw(Graphics2D g) {
        //Background
        bg.draw(g);

        //Menu options
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
            gsm.setGameState(GameStateManager.LEVEL1STATE);
        }
        if(currentChoice == 1){
            gsm.setGameState(GameStateManager.LEVEL2STATE);
        }
        if(currentChoice == 2){
            gsm.setGameState(GameStateManager.RANDOMIZERSTATE);
        }
        if(currentChoice == 3){
            gsm.setGameState(GameStateManager.MAINMENUSTATE);
        }
    }

}
