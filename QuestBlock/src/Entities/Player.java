package entities;

import tiles.TileMap;
import tiles.TileType;

import java.awt.*;

@SuppressWarnings({"AssignmentToSuperclassField", "MagicNumber"})
//"magic numbers" are actually values for a color
//Assignments to fields in super class used to
/**
 * The main player in the game. Controlled by keyboard.
 */
public class Player extends Movable {

    protected boolean left;
    protected boolean right;
    protected boolean jumping;
    protected boolean falling;
    protected boolean sprinting;
    protected boolean sliding;
    protected boolean flying;
    protected long lastJump = 0;


    /**
     *
     * @param tm tileMap that should be used
     * @param size Size of the player
     */
	public Player(TileMap tm, int size) {
        //player properties
		this.tileMap = tm;
        this.playerColor = new Color(255,0,0,90);

        //size
		this.width = size;
		this.height = size;

        //movement
		this.moveSpeed = 0.5;
        this.maxSpeed = 5.1;
        this.maxFallingSpeed = 100;
        this.stopSpeed = 0.40;
        this.jumpStart = -8.0;
        this.gravity = 0.30;
        this.slidingSpeed = 3.5;

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

    public void setSlidingSpeed(double x){
        slidingSpeed = x;
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
        if(!flying){
            if(dx > 0) {
                calculateCorners(x + 7, y);
                sliding = (topRight || topLeft || bottomRight || bottomLeft);
            }
            else if(dx < 0) {
                calculateCorners(x - 7, y);
                sliding = (topRight || topLeft || bottomRight || bottomLeft);
            }
            else {
                calculateCorners(x + 7, y);
                sliding = (topRight || topLeft);
                if(!sliding){
                    calculateCorners(x - 7, y);
                    sliding = (topRight || topLeft);
                }
            }
        }
    }

	public void calculateCorners(double x, double y){ //rectangular hit-detection
		int leftTile = tileMap.getColTile((int) x - width / 2);
		int rightTile = tileMap.getColTile(((int) (x + width / 2)) - 1);
		int topTile = tileMap.getRowTile((int)(y - height / 2));
		int bottomTile = tileMap.getRowTile((int)(y + height / 2) - 1);


		topLeft = tileMap.getTile(topTile, leftTile).isCollidable();

		topRight = tileMap.getTile(topTile,rightTile).isCollidable();

		bottomLeft = tileMap.getTile(bottomTile, leftTile).isCollidable();


		bottomRight = tileMap.getTile(bottomTile, rightTile).isCollidable();

        tileMap.getTile(bottomTile, leftTile).steppedOn();
        tileMap.getTile(bottomTile, rightTile).steppedOn(); //indicate that we have stepped on these tiles
	}

	public void update(){
        if(sprinting){
            maxSpeed = 10;
            moveSpeed = 0.6;
        }
        if(!sprinting){
            maxSpeed = 5.1;
            moveSpeed = 0.5;
        }
        if(!sliding){
            maxFallingSpeed = 100;
        }
		if(sliding){
			maxFallingSpeed = slidingSpeed;
		}
		else{
			maxFallingSpeed = 100;
		}
		//calculate next position
		if(left){//accelerates/moves left
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		} //accelerates/moves right
		else if(right){
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed;
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

		if(falling){ //mid jump or if falling down
            checkSliding();
			dy += gravity;
			if(dy > maxFallingSpeed){
				dy = maxFallingSpeed;
			}
		}
		else{ //standing still
			dy = 0;
		}


		//collision detection

		int currentColumn = tileMap.getColTile((int)x);
		int currentRow = tileMap.getRowTile((int)y);

		double nextX = x + dx;
		double nextY = y + dy;

		double tempX = x;
		double tempY = y;

		calculateCorners(x, nextY); //calculate for next y
		if(dy < 0){ //accelerating upwards
			if(topLeft || topRight){
				dy = 0;
			}
			else{ //no collision, can continue
				tempY += dy;
			}
		}
		if(dy > 0){ //accelerating towards ground
			if(bottomLeft || bottomRight){
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
			if(topLeft || bottomLeft){
				dx = 0;
				tempX = currentColumn * tileMap.getTileSize() + width / 2.0;

			}
			else{ //no collision
				tempX += dx;
			}
		}
		if(dx > 0){ //to the right
			if(topRight || bottomRight){
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
			if(!bottomLeft && !bottomRight){
				falling = true;
            }
		}
		x = tempX;
		y = tempY;


	}

	public void draw(Graphics2D g){

		int tx = tileMap.getX();
		int ty = tileMap.getY();



		g.setColor(playerColor);
		g.fillRect(((int) (tx + x - width / 2))+1, ((int) (ty + y - height / 2))+1, width-3, height-3);
		g.setColor(Color.WHITE);
		g.drawRect(((int) (tx + x - width / 2))+1, ((int) (ty + y - height / 2))+1, width-3, height-3);
	}

}
