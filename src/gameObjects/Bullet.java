package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Panel;

public class Bullet{

	public int x;
	public int y;
	public final int BULLET_SIZE = 5;
	public int direction;	// left 0 - right 1
	public int speed;
	public GameObject owner;
	Panel gp;
	
	public Bullet(Panel gp, GameObject owner, int x, int y, int direction) {
		
		this.gp = gp;
		
		this.owner = owner;
		
		this.x = x;
		
		this.y = y;
		
		this.direction = direction;
	
		this.speed = 120;
	
	}
	
	public void move(double time) {

		double moveAmount = speed * time;
		
		if (direction == 0) {
			x -= (int) moveAmount;
		} else if (direction == 1) {
			x += (int) moveAmount;
		}
	}
	
	public void draw(Graphics2D g) {

		if (owner instanceof Player) {
			g.setColor(Color.YELLOW);
		} else if (owner instanceof Friend) {
			g.setColor(Color.BLUE);
		} else if (owner instanceof Enemy) {
			g.setColor(Color.ORANGE);
		}
		
		g.fillRect(x, y, BULLET_SIZE, BULLET_SIZE);
	}

	public GameObject getOwner() {
		return this.owner;
	}	
}