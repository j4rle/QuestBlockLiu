package tiles;


import java.awt.*;

@SuppressWarnings("MagicNumber") //"Magic numbers" here are color codes in RGB

/**
 * Tiles class for the game. These are the tiles that the player will interact with when playing. Constructed with a number of properties.
 *
 */
public class Tile {
    private int tileSize;
    private TileType tileType;
    private Color tileColor = null;
    private int tileScaleX;
    private int tileScaleY;

    private boolean hidden = false;
    private boolean collidable = true;
    private boolean steppedOn = false;
    private boolean water = false;
    private int steppedOnTimer = 0;
    private int resetCount = 0;

    private static final int STEPPEDONMAXCOUNT = 25;
    private static final int RESETMAXCOUNT = 150;

    /**
     * Constructs and initializes a Tile with the size tileSize
     * @param tileSize tile size
     * @param tileScaleX tile scaling in x-direction
     * @param tileScaleY tile scaling in y-direction
     * @param tileType type of tile
     */


    public Tile(int tileSize, int tileScaleX, int tileScaleY, TileType tileType) {
        this.tileScaleX = tileScaleX;
        this.tileScaleY = tileScaleY;
        this.tileSize = tileSize;
        this.tileType = tileType;

        switch(tileType){
            case TYPE1:
                this.tileColor = new Color(255,255,255,90);
                break;
            case OUTSIDE:
                this.tileColor = new Color(87,87,87, 90);
                break;
            case NONE:
                this.collidable = false;
                this.hidden = true;
                break;
            case FALLTILE:
                this.tileColor = new Color(255,153,0, 90);
                break;
            case VICTORYTILE:
                this.tileColor = new Color(0,153,0,90);
                break;
            case WATERTILE:
                this.tileColor = new Color(0,0,153,90);
                collidable = false;
                water = true;
                break;
        }
    }

    public boolean isCollidable() {
        return collidable;
    }

    public boolean isWater() {
        return water;
    }

    public void steppedOn(){
        steppedOn = true;
    }

    public boolean isSteppedOn() {
        return steppedOn;
    }


    private void resetTile(){
        if(resetCount >= RESETMAXCOUNT){
            hidden = false;
            collidable = true;
            resetCount = 0;
        }

    }

    private void hideTile(){
        if(steppedOnTimer >= STEPPEDONMAXCOUNT){
            hidden = true;
            collidable = false;
            steppedOnTimer = 0;
            steppedOn = false;
        }
    }


    public void draw(Graphics2D graphics,int tileXCoordinate,int tileYCoordinate){
        if(!hidden){
            graphics.setColor(tileColor);
            graphics.fillRect(tileXCoordinate + tileScaleX, tileYCoordinate + tileScaleY, tileSize, tileSize);
            //tile border
            graphics.setColor(Color.WHITE);
            graphics.drawRect(tileXCoordinate + tileScaleX, tileYCoordinate + tileScaleY, tileSize, tileSize);
        }
        if(tileType == TileType.FALLTILE){
            if(!steppedOn){
                resetCount++;
                resetTile();
            }
            else{
                steppedOnTimer++;
                hideTile();
            }
        }
    }
}
