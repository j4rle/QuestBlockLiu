package gamestate;

import java.awt.*;

/**
 * The abstract class for every GameState
 * Passes through graphics and key presses
 */
public interface GameState {

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
