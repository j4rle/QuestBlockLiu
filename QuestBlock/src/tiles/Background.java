package tiles;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Creates a background-object from a file
 */
public class Background {

	private BufferedImage image;

	private double x;
	private double y;
	private double dx;
	private double dy;

	public Background(String s){
        this.image = null;
		try{
			this.image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void draw(Graphics2D g){ //Draws background.
		g.drawImage(image, (int)x,(int)y,null);
		if(x < 0){
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y,null);
		}
		if(x > 0){
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
