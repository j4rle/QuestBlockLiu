package game;

import gamestate.GameStateControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 * Game engine for the game. Run with a Timer.
 */
public class GameEngine extends KeyAdapter {
    private GameStateControl gameStateControl;
    private GamePanel gamePanel;

    protected static final int LOGIC_DELAY_IN_MS = 15;

    /**
     * Game engine for the game. Writes to a panel at a frequent rate
     * @param gamePanel The panel the game engine draws to
     */
    public GameEngine(GamePanel gamePanel) {
        this.gameStateControl = new GameStateControl();
        this.gamePanel = gamePanel;
        init();
    }

    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        gameStateControl.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        gameStateControl.keyReleased(e.getKeyCode());
    }


    public void init(){

        try(Scanner reader = new Scanner(System.in)){
            System.out.println("Enter your name:");
            String playerName = reader.next();
            gameStateControl.setPlayerName(playerName);
        }


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
        gameStateControl.update();
    }
    private void render(){
        gamePanel.render(gameStateControl);
    }

}

