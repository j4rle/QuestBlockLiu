package Tiles;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Background {

	private BufferedImage image;
	private double moveScale;

	private double x;
	private double y;
	private double dx;
	private double dy;

	public Background(String s, double ms){
		try{
			image = ImageIO.read(getClass().getResourceAsStream(s)
			);
			this.moveScale = ms;
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g){ //Draws background based on which direction the player is moving.
		g.drawImage(image, (int)x,(int)y,null);
		if(x < 0){
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y,null);
		}
		if(x>0){
			g.drawImage(image,(int)x-GamePanel.WIDTH,(int)y,null);
		}
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}



	public void update(){
		x += dx;
		y += dy;
	}

}