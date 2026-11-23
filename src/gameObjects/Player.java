package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Keyboard;
import main.Panel;

public class Player extends GameObject{

	Keyboard key;
	
	public Player(Panel gp, Keyboard key) {
		
		super(gp);
		this.key = key;
		setToCenter();
		addToCollidables();
		
	}
	
	public void setToCenter() {
		
		x = gp.SCREEN_WIDTH / 2;
		y = gp.SCREEN_HEIGHT / 2;
		
	}
	
	public void moveUp() {
		if (y != 0) y -= SPEED;
	}
	
	public void moveDown() {
		if (y != 490) y += SPEED;
	}
	
	public void moveLeft() {
		if (x != 0) x -= SPEED;
	}
	
	public void moveRight() {
		if (x != 490) x += SPEED;
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		Color purple = new Color(191,0,255);
		g.setColor(purple);
		super.draw(g);
		
	}
}