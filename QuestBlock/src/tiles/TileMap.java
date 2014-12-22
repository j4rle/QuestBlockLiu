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

public class TileMap {

	//Offsets
	private int x;
	private int y;
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;


	private int tileSize;
	private TileType[][] map;
    private Tile[][] tiles = null;
	private int mapWidth = 0; //width of the map as read from the map file
	private int mapHeight = 0; //height of the map as read from the map file

    private int victoryRow = 0;
    private int victoryCol = 0;
    private boolean victory = false;
    private Random rnd;

    /**
     *
     * @param s file path for tile map
     * @param tileSize size of the tiles in the level
     */
	public TileMap(String s, int tileSize){

		this.tileSize = tileSize;
        this.map = null;
        initTileMap(s);
        initTiles();
        rnd = new Random();

    }

    private void initTileMap(String s) {
        InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream(s));
        try (BufferedReader br = new BufferedReader(inputStreamReader)) {
            mapWidth = Integer.parseInt(br.readLine()); //reads first line
            mapHeight = Integer.parseInt(br.readLine()); //reads second line

            int height = mapHeight * tileSize;
            int width = mapWidth * tileSize;

            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;

            map = new TileType[mapHeight][mapWidth]; //map represented as 2d array
            tiles = new Tile[mapHeight][mapWidth]; //tiles represented in 2d array


            String delimiter = " "; //separator for information in our file

            //Reading the rest of the map file:
            TileType tileType = TileType.NONE;
            for (int row = 0; row < mapHeight; row++) { //loop over rows (number of lines in map.txt)
                String line = br.readLine();
                String[] tokens = line.split(delimiter); //Splits the read line with the delimiter
                for (int col = 0; col < mapWidth; col++) { //loop over columns
                    int numTile = Integer.parseInt(tokens[col]); //inserts the parsed int array into the correct column
                    switch (numTile) {
                        case 0:
                            tileType = TileType.TYPE1;
                            break;
                        case 1:
                            tileType = TileType.NONE;
                            break;
                        case 2:
                            tileType = TileType.OUTSIDE;
                            break;
                        case 3:
                            tileType = TileType.FALLTILE;
                            break;
                        case 4:
                            tileType = TileType.VICTORYTILE;
                            break;
                        case 5:
                            tileType = TileType.WATERTILE;
                            break;
                    }
                    map[row][col] = tileType; //insert TileType-Enum in map-array for every tile
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initTiles() {
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {

                TileType rc = map[row][col]; //current position
                tiles[row][col] = new Tile(tileSize, col * tileSize, y + row * tileSize, rc); //create new tile with given properties and place in array rc is Enum TileType
                if(rc == TileType.VICTORYTILE){
                    victoryRow = row;
                    victoryCol = col;
                }
            }
        }
    }

    public void randomize(){
        //choose a random row/column
//        int rndRow = rnd.nextInt(mapHeight-2)+1;
//        int rndCol = rnd.nextInt(mapWidth-2)+1;
        int rndRow = 10;
        int rndCol = 10;

        //randomize tile
        TileType tileType = TileType.getRandom();
        //insert tile
        if(tileType != TileType.VICTORYTILE && tileType != TileType.OUTSIDE){
            map[rndRow][rndCol] = tileType;
            tiles[rndRow][rndCol] = new Tile(tileSize, rndCol * tileSize, y + rndRow * tileSize, tileType);
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

	public Tile getTile(int row, int col){
		return tiles[row][col]; //return Tile at this position
	}

	public int getTileSize(){
		return tileSize;
	}

	public void setX(int x) {
		this.x = x;
		fixBounds();
	}

    public boolean isVictory() {
        return victory;
    }

    public void setY(int y) {
		this.y = y;
		fixBounds();
	}

	private void fixBounds() { //keeps the camera locked inside the level
		if(x<xmin) x = xmin;
		if(y<ymin) y = ymin;
		if(x>xmax) x = xmax;
		if(y>ymax) y = ymax;
	}


    public void update(){
        if(tiles[victoryRow][victoryCol].isSteppedOn()){
            victory = true;
        }
    }

	public void draw(Graphics2D g){

        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                tiles[row][col].draw(g,x,y);
            }
        }
	}
}