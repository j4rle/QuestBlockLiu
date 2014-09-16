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
    protected static final int RENDER_DELAY_IN_MS = 15;

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
            }
        };

        final Action drawOneFrame = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.draw();
            }
        };

        Timer gameLogicTimer = new Timer(LOGIC_DELAY_IN_MS, doOneLogic);
        Timer drawTimer = new Timer(RENDER_DELAY_IN_MS, drawOneFrame);

        gameLogicTimer.start();
        drawTimer.start();
    }

    private void update(){
        gsc.update();
    }
    private void render(){
        gamePanel.render(gsc);
    }

}

