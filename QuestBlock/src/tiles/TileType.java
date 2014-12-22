package tiles;

/**
 * Different types of tiles in the game
 */
public enum TileType {
    /**
     * Type 1 tile
     */
    TYPE1,
    /**
     * Outside tile
     */
    OUTSIDE,
    /**
     * falling tile
     */
    FALLTILE,
    /**
     * no tile
     */
    NONE,
    /**
     * tile that causes victory when stepped on
     */
    VICTORYTILE,
    /**
     * water tile
     */
    WATERTILE;

    //Returns a random tile type
    public static TileType getRandom(){
        return values()[(int)(Math.random() * values().length)];
    }

}
