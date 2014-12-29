package entities;

import tiles.Tile;
import tiles.TileMap;

import java.awt.*;

/**
 * The main player in the game. Controlled by keyboard.
 */
@SuppressWarnings("MagicNumber") //Magic numbers are actually RGB colors
public class Player extends Movable {

    protected boolean left;
    protected boolean right;
    protected boolean jumping;
    protected boolean falling;
    protected boolean sprinting;
    protected boolean sliding;
    protected boolean flying;
    protected boolean dead = false;
    protected long lastJump = 0;


    /**
     * Player that moves around on the map
     * @param tm which tileMap that should be used
     * @param size Size of the player
     */
	public Player(TileMap tm, int size) {
        super(tm,new Color(255,0,0,90),size,size,0,100,3.5,0.3,-8.0,0.4,100,5.1,0.5);

	}

    public boolean isDead() {
        return dead;
    }

    public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}


	public void setLeft(boolean tempBool){
		left = tempBool;
	}

	public void setRight(boolean tempBool){
		right = tempBool;

	}
	public void setFlying(boolean tempBool){
		flying = tempBool;
	}

	public void setJumping(boolean tempBool){
		double nextX;

        checkSliding();
		if(dx > 0){
			nextX = x + 5;
		}
		else{
			nextX = x - 5;
		}
		calculateCorners(nextX, y);
		if(!falling || sliding && System.currentTimeMillis() - lastJump > 500){ //player can jump from walls
			jumping = tempBool;
            lastJump = System.currentTimeMillis();
		}
	}

	public void setSprint(boolean tempBool){
		sprinting = tempBool;
	}

    public void checkSliding(){
		final double SLIDE_PADDING = 3;
        if(!flying){
            if(dx > 0) {
                calculateCorners(x + SLIDE_PADDING, y);
                sliding = (topRightBool || topLeftBool || bottomRightBool || bottomLeftBool);
            }
            else if(dx < 0) {
                calculateCorners(x - SLIDE_PADDING, y);
                sliding = (topRightBool || topLeftBool || bottomRightBool || bottomLeftBool);
            }
            else {
                calculateCorners(x + SLIDE_PADDING, y);
                sliding = (topRightBool || topLeftBool);
                if(!sliding){
                    calculateCorners(x - SLIDE_PADDING, y);
                    sliding = (topRightBool || topLeftBool);
                }
            }
        }
    }


	public void calculateCorners(double x, double y){ //rectangular hit-detection
		int leftTile = tileMap.getColTile((int) x - width / 2);
		int rightTile = tileMap.getColTile(((int) (x + width / 2)) - 1);
		int topTile = tileMap.getRowTile((int)(y - height / 2));
		int bottomTile = tileMap.getRowTile((int)(y + height / 2) - 1);

        //check what tiles surrounds the player
        Tile topLeft = tileMap.getTile(topTile, leftTile);
        Tile topRight = tileMap.getTile(topTile,rightTile);
        Tile bottomLeft = tileMap.getTile(bottomTile, leftTile);
        Tile bottomRight = tileMap.getTile(bottomTile, rightTile);

        //checking every corner for collision
		topLeftBool = topLeft.isCollidable();
		topRightBool = topRight.isCollidable();
		bottomLeftBool = bottomLeft.isCollidable();
		bottomRightBool = bottomRight.isCollidable();

        tileMap.getTile(bottomTile, leftTile).steppedOn();
        tileMap.getTile(bottomTile, rightTile).steppedOn(); //indicate that we have stepped on these tiles

        if(topLeft.isWater() || topRight.isWater() || bottomLeft.isWater() || bottomRight.isWater()){
            drowningCounter++;
        }
        else{
            drowningCounter = 0;
        }
    }

	// Keeps tile map updated(useful when randomizing level)
	public void updateMap(TileMap tileMap){
		this.tileMap = tileMap;

	}

	private void updatePlayerPosition(){
		if(drowningCounter > drowningTimer){ //player has drowned
			dead = true;
		}

		if(sprinting){ //player is sprinting
			maxSpeed = 10;
			moveSpeed = 0.6;
		}
		else{ //player not sprinting
			maxSpeed = 5.1;
			moveSpeed = 0.5;
		}

		if(sliding){ //player sliding
			maxFallingSpeed = slidingSpeed;
		}
		else{//player not sliding
			maxFallingSpeed = 100;
		}

		//calculate next position
		if(left){//accelerates/moves left
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed; //stops player acceleration if maxSpeed is reached
			}
		}
		else if(right){ //accelerates/moves right
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed; //stops player acceleration if maxSpeed is reached
			}
		}
		else { //slows down to a stop
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			}
			else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if(jumping){ //start of jump
			dy = jumpStart;
			falling = true;
			jumping = false;
		}

		else if(falling){ //mid jump or if falling down
			checkSliding();
			dy += gravity;
			if(dy > maxFallingSpeed){
				dy = maxFallingSpeed;
			}
		}
		else{ //standing still
			dy = 0;
		}
	}

	private void handleCollisions(){
		//collision detection

		int currentColumn = tileMap.getColTile((int)x);
		int currentRow = tileMap.getRowTile((int)y);

		double nextX = x + dx;
		double nextY = y + dy;

		double tempX = x;
		double tempY = y;

		calculateCorners(x, nextY); //calculate for next y
		if(dy < 0){ //accelerating upwards
			if(topLeftBool || topRightBool){
				dy = 0;
			}
			else{ //no collision, can continue
				tempY += dy;
			}
		}
		if(dy > 0){ //accelerating towards ground
			if(bottomLeftBool || bottomRightBool){
				dy = 0;
				falling = false;
				tempY = (currentRow + 1) * tileMap.getTileSize() - height / 2.0;
			}
			else{ //no collision
				tempY += dy;
			}
		}

		calculateCorners(nextX, y); //calculate for x
		if(dx < 0){ //to the left
			if(topLeftBool || bottomLeftBool){
				dx = 0;
				tempX = currentColumn * tileMap.getTileSize() + width / 2.0;

			}
			else{ //no collision
				tempX += dx;
			}
		}
		if(dx > 0){ //to the right
			if(topRightBool || bottomRightBool){
				sliding = true;
				dx = 0;
				tempX = (currentColumn + 1) * tileMap.getTileSize() - width / 2.0;
			}
			else{ //no collision
				tempX += dx;
			}
		}

		if(!falling){ //checks below the player to decide if the player is falling
			calculateCorners(x, y + 1.5);
			if(!bottomLeftBool && !bottomRightBool){
				falling = true;
			}
		}
		x = tempX;
		y = tempY;
	}

	//update player movement and handle collisions
	public void update(){
		updatePlayerPosition();
		handleCollisions();
	}

	public void draw(Graphics2D g){

		int tx = tileMap.getX();
		int ty = tileMap.getY();



		g.setColor(playerColor);
		g.fillRect(((int) (tx + x - width / 2))+1, ((int) (ty + y - height / 2))+1, width-3, height-3); //player fill
		g.setColor(Color.WHITE);
		g.drawRect(((int) (tx + x - width / 2))+1, ((int) (ty + y - height / 2))+1, width-3, height-3); //player white outline
	}

}
