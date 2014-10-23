package tiles;


import java.awt.*;

public class Tile {
    private int TILESIZE;
    private TileType tileType;
    private Color tileColor;
    private int tileScaleX;
    private int tileScaleY;

    private boolean hidden = false;
    private boolean collidable = true;
    private boolean steppedOn = false;
    private int steppedOnTimer = 0;
    private int resetCount = 0;

    private static final int STEPPEDONMAXCOUNT = 25;
    private static final int RESETMAXCOUNT = 150;


    public Tile(int tilesize, int tileScaleX, int tileScaleY, TileType tileType) {
        this.tileScaleX = tileScaleX;
        this.tileScaleY = tileScaleY;
        this.TILESIZE = tilesize;
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
        }
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void steppedOn(){
        steppedOn = true;
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

    public void draw(Graphics2D g,int x,int y){
        if(!hidden){
            g.setColor(tileColor);
            g.fillRect(x + tileScaleX, y + tileScaleY, TILESIZE, TILESIZE);
            //tile border
            g.setColor(Color.WHITE);
            g.drawRect(x + tileScaleX, y + tileScaleY, TILESIZE, TILESIZE);
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
