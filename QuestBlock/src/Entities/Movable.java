package Entities;

import Tiles.TileMap;

import java.awt.*;

public abstract class Movable {

	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	protected int width;
	protected int height;
	protected Color playerColor;

	protected boolean left;
	protected boolean right;
	protected boolean jumping;
	protected boolean falling;
	protected boolean sprinting;
	protected boolean sliding;

	protected double moveSpeed;
	protected double maxSpeed;
	protected double maxFallingSpeed;
	protected double stopSpeed;
	protected double jumpStart;
	protected double gravity;

	protected TileMap tileMap;

	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;

	public abstract void update();
	public abstract void draw(Graphics2D g);

}
