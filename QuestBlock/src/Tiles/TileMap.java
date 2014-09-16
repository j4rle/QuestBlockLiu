package tiles;

import game.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * The class responsible for reading maps from files.
 */
@SuppressWarnings("MagicNumber")
//"magic numbers" are color codes.

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
    private double randomizeChanges;
    private double randomizeInterval;

    //for randomized map
    private boolean randomized;
    private boolean newRandomize;
    private int randomizeCounter;

	private int tileSize;
	private int[][] map;
	private int mapWidth; //width of the map as read from the map file
	private int mapHeight; //height of the map as read from the map file
    private int[] tileTypes;

    /**
     *
     * @param s file path for tilemap
     * @param tileSize size of the tiles in the level
     */
	public TileMap(String s, int tileSize){

		this.tileSize = tileSize;
        this.randomized = false;
        this.tileTypes = new int[]{1,2};
        this.map = null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(s)));

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
		} catch (IOException e) {
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

	public void setX(int x) {
		this.x = x;
		fixBounds();
	}

	public void setY(int y) {
		this.y = y;
		fixBounds();
	}


    public void setRandomized(boolean tempBool, double interval, double changes){
        this.randomizeInterval = interval;
        this.randomizeChanges = changes;
        randomized = tempBool;
    }

	private void fixBounds() { //keeps the camera locked inside the level
		if(x<xmin) x = xmin;
		if(y<ymin) y = ymin;
		if(x>xmax) x = xmax;
		if(y>ymax) y = ymax;
	}

    private void randomize(){
        newRandomize = (randomizeCounter >= randomizeInterval);
        if(newRandomize) {
            Random rnd = new Random();

            for (int i = 0; i < randomizeChanges; i++) {
                int rndX = rnd.nextInt(mapWidth-2)+1;
                int rndY = rnd.nextInt(mapHeight-2)+1;
                int rndTile = rnd.nextInt(tileTypes.length);
                map[rndY][rndX] = rndTile;
            }
            randomizeCounter = 0;
        }
        else{
            randomizeCounter++;
        }
    }

    public void update(){
        if(randomized) {
            randomize();
        }
    }

	public void draw(Graphics2D g){

		//iterate over rows and columns
		Color block1Color = new Color(255,255,255,90);
		Color outsideColor = new Color(87,87,87, 90);

		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {

				int rc = map[row][col]; //current position

				if(rc == 0){
					g.setColor(block1Color); //this is a type 1 tile
				}
				if(rc ==1){ //no tile to be drawn
                    continue;
				}
				if(rc == 2){
					g.setColor(outsideColor); //border surrounding map
				}

				//tile
				g.fillRect(x + col * tileSize, y + row * tileSize, tileSize,tileSize);
				//tile border
				g.setColor(Color.WHITE);
				g.drawRect(x + col * tileSize, y + row * tileSize, tileSize,tileSize);
			}
		}
	}

}
