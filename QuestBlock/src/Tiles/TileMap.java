package Tiles;

import Game.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class TileMap {

	//Offsets
	private int x;
	private int y;
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	private int height;
	private int width;
	private int FPS;
	private Font font;
	private boolean showFPS;

	private int tileSize;
	private int[][] map;
	private int mapWidth; //width of the map as read from the mapfile
	private int mapHeight; //height of the map as read from the mapfile


	public TileMap(String s, int tileSize){

		this.tileSize = tileSize;
		showFPS = true;

		try{
			BufferedReader br = new BufferedReader(new FileReader(s));

			mapWidth = Integer.parseInt(br.readLine()); //reads first line
			mapHeight = Integer.parseInt(br.readLine()); //reads second line

			height = mapHeight * tileSize;
			width = mapWidth * tileSize;

			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT-height;
			ymax = 0;

			map = new int[mapHeight][mapWidth]; //map represented as array


			String delimiter = " "; //separator for information in our file

			//Reading the rest of the map file:
			for (int row = 0; row < mapHeight; row++) { //loop over rows (number of lines in map.txt)
				String line = br.readLine();
				String[] tokens = line.split(delimiter); //Splits the read line with the delimiter
				for (int col = 0; col < mapWidth; col++) { //loop over columns
					map[row][col] = Integer.parseInt(tokens[col]); //inserts the parsed int array into the correct column
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getXmin() {
		return xmin;
	}

	public int getYmin() {
		return ymin;
	}

	public int getXmax() {
		return xmax;
	}

	public int getYmax() {
		return ymax;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getColTile(int x){
		return x / tileSize;
	}
	public int getRowTile(int y){
		return y / tileSize;
	}

	public int getTile(int row, int col){
		return map[row][col]; //return value of map at this position
	}

	public int getTileSize(){
		return tileSize;
	}

	public boolean ShowFPS() {
		return showFPS;
	}

	public void setShowFPS(boolean b){
		showFPS = b;
	}

	public void setX(int x) {
		this.x = x;
		fixBounds();
	}

	public void setY(int y) {
		this.y = y;
		fixBounds();
	}

	public void setFPS(int FPS){
		this.FPS = FPS;
	}

	private void fixBounds() { //keeps the camera locked inside the level
		if(x<xmin) x = xmin;
		if(y<ymin) y = ymin;
		if(x>xmax) x = xmax;
		if(y>ymax) y = ymax;
	}

	public void draw(Graphics2D g){

		//iterate over rows and columns
		Color block1Color = new Color(255,255,255,90);
		Color outsideColor = new Color(87,87,87, 90);
		font = new Font("Arial", Font.PLAIN, 10);

		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {

				int rc = map[row][col]; //currentpos

				if(rc == 0){
					g.setColor(block1Color);
				}
				if(rc ==1){ //no tile to be drawn
					continue;
				}
				if(rc == 2){
					g.setColor(outsideColor);
				}

				//tile
				g.fillRect(x + col * tileSize, y + row * tileSize, tileSize,tileSize);
				//tile border
				g.setColor(Color.WHITE);
				g.drawRect(x + col * tileSize, y + row * tileSize, tileSize,tileSize);

				//FPS
				if(showFPS) {
					g.setFont(font);
					g.setColor(Color.WHITE);
					g.drawString("FPS: " + Integer.toString(FPS), 5, 10);
				}
			}
		}
	}

}
