package entities;

import tiles.TileMap;

import java.awt.*;

/**
 * Abstract class for any movable entity in the game
 */
public abstract class Movable {

	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	protected int width;
	protected int height;
	protected int drowningCounter;
	protected int drowningTimer;
	protected Color playerColor = null;

	protected double moveSpeed;
	protected double maxSpeed;
	protected double maxFallingSpeed;
	protected double stopSpeed;
	protected double jumpStart;
	protected double gravity;
    protected double slidingSpeed;

	protected TileMap tileMap = null;

	protected boolean topLeftBool;
	protected boolean topRightBool;
	protected boolean bottomLeftBool;
	protected boolean bottomRightBool;

    public abstract void update();
    public abstract void draw(Graphics2D g);


    protected Movable(TileMap tileMap, Color playerColor, int width, int height, int drowningCounter, int drowningTimer, double slidingSpeed, double gravity, double jumpStart, double stopSpeed, double maxFallingSpeed, double maxSpeed, double moveSpeed) {
        this.tileMap = tileMap;
        this.playerColor = playerColor;
        this.width = width;
        this.height = height;
        this.drowningCounter = drowningCounter;
        this.drowningTimer = drowningTimer;
        this.slidingSpeed = slidingSpeed;
        this.gravity = gravity;
        this.jumpStart = jumpStart;
        this.stopSpeed = stopSpeed;
        this.maxFallingSpeed = maxFallingSpeed;
        this.maxSpeed = maxSpeed;
        this.moveSpeed = moveSpeed;
    }
}
