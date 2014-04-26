package Game;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private Thread thread;
	private boolean running;

	private BufferedImage image;
	private Graphics2D g;

	public static int FPS = 0;

	private GameStateManager gsm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public int getFPS() {
		return FPS;
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}

	public void run() {

		init();

		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;

			while(delta >= 1){
				update();
				delta -= 1;
				shouldRender = true;
			}

			if(shouldRender){
				frames++;
				render();
				draw();
			}

			if(System.currentTimeMillis() - lastTimer >= 1000){
				FPS = frames;

				lastTimer += 1000;
				frames = 0;
			}

		}
	}


	public void init(){

		running = true;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
	}

	private void update(){
		gsm.update();
	}

	private void render(){
		gsm.draw(g);
	}

	private void draw(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key){

	}

	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}


}
