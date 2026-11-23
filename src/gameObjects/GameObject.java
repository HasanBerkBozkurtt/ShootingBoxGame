package gameObjects;

import java.awt.Graphics2D;
import java.util.Random;

import main.Collidable;
import main.Panel;

public abstract class GameObject extends Thread implements Collidable{

	public int x;
	public int y;
	public boolean isAlive;
	public boolean hitWall = false;
	public int lastDirection = -1;
	public final int SPEED = 10;
	public final int TILE_SIZE = 10;
	Random random = new Random();
	Panel gp;
	
	public void draw(Graphics2D g) {
		
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
	
	}
	
	public GameObject(Panel gp) {
		this.gp = gp;
	}
	
	public void addToCollidables() {
		
		gp.collidables.add((Collidable) this);
		
	}
	
	public void kill() {
		
		this.isAlive = false;
		this.interrupt();
		
	}
	
	public void movement() {
		
		int newX = this.x;
		int newY = this.y;
		
		if (hitWall) {
			turn(lastDirection);
			hitWall = false; 
			return;
		}
		
		int randomNum = random.nextInt(4);
		
		if ((randomNum == 0) && (newY != 490))	// DOWN
			newY += SPEED;
		else if ((randomNum == 1) && (newY != 0))	// UP
			newY -= SPEED;
		else if ((randomNum == 2) && (newX != 0))	// LEFT
			newX -= SPEED;
		else if ((randomNum == 3) && (newX != 490))	// RIGHT
			newX += SPEED;
		else {
			hitWall = true;
			lastDirection = randomNum;
			return;
		}
			
		this.x = newX; 
		this.y = newY;
		
	}
	
	public void turn(int direction) {
		
		switch (direction) {
		
		case 0:
			this.y -= SPEED;
			break;
		case 1:
			this.y += SPEED;
			break;
		case 2:
			this.x += SPEED;
			break;
		case 3:	
			this.x -= SPEED;
			break;
		}
	}
	
	public void create() {
		
		int newX;
		int newY;
		boolean flag;
		
		do {

			flag = false;
			newX = random.nextInt(50) * 10;
			newY = random.nextInt(50) * 10;
			
			for (Collidable obj : gp.collidables) {
				if (obj instanceof GameObject) {
					GameObject other = (GameObject) obj;
					if ((other != this) && (newX == other.x) && (newY == other.y)) {
						flag = true;
						break;
					}
				}
			}
			
		} while (flag);
		
		this.x = newX;
		this.y = newY;
		this.isAlive = true;
		addToCollidables();
	}	
}