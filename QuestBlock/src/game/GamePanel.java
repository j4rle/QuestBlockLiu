package game;

import gamestate.GameStateControl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The panel where the game is drawn
 *
 */
public class GamePanel extends JPanel{

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private BufferedImage image;
	private Graphics2D g;

	public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
        this.image = null;
        this.g = null;
        init();
	}

    public void init(){
		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.g = (Graphics2D) image.getGraphics();
        GameEngine gameEngine = new GameEngine(this);
        addKeyListener(gameEngine);
	}


	public void render(GameStateControl gameStateControl){
		gameStateControl.draw(g);
	}

	public void draw(){
		Graphics g2 = getGraphics();
		g2.drawImage(this.image, 0, 0, null);
		g2.dispose();
	}


}
