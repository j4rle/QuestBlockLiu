package game;

import gamestate.GameStateControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEngine extends KeyAdapter {
    private GameStateControl gsc;
    private GamePanel gamePanel;

    protected static final int LOGIC_DELAY_IN_MS = 15;

    public GameEngine(GamePanel gamePanel) {
        this.gsc = new GameStateControl();
        this.gamePanel = gamePanel;
        init();
    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        gsc.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        gsc.keyReleased(e.getKeyCode());
    }


    public void init(){
        final Action doOneLogic = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                render();
                gamePanel.draw();
            }
        };


        Timer gameLogicTimer = new Timer(LOGIC_DELAY_IN_MS, doOneLogic);

        gameLogicTimer.start();
    }

    private void update(){
        gsc.update();
    }
    private void render(){
        gamePanel.render(gsc);
    }

}

