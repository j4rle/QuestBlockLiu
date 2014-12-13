package game;

import gamestate.GameStateControl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Panel used to draw game.
 */
public class GamePanel extends JPanel{

    /**
     * width of the window
     */
	public static final int WIDTH = 640;
    /**
     * height of the window
     */
	public static final int HEIGHT = 480;

	private BufferedImage image;
	private Graphics2D graphics;

    /**
     * Set some standard values and call the init() where game engine and key listener are initialized.
     */

	public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
        this.image = null;
        this.graphics = null;
        init();
	}

    public void init(){
		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) image.getGraphics();
        GameEngine gameEngine = new GameEngine(this);
        addKeyListener(gameEngine);
	}


	public void render(GameStateControl gameStateControl){
		gameStateControl.draw(graphics);
	}

	public void draw(){
		Graphics g2 = getGraphics();
		g2.drawImage(this.image, 0, 0, null);
		g2.dispose();
	}


}
