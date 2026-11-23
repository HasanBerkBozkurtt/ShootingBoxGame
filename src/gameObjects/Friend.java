package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObjects.Bullet;

import main.Panel;

public class Friend extends GameObject{
	
	private int fireCount = 0;
	
	public Friend(Panel gp) {
		
		super(gp);
		
		create();
		
		addToFriends(gp);
		
		this.start();
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.GREEN);
		
		super.draw(g);
		
	}
	
	public void run() {

		while(isAlive) {
			
			try {
				
				Thread.sleep(1000);
				
				if (gp.frame.state == gp.frame.playState) {
					
					movement();
					
					fireCount++;
					
					if (fireCount >= 2) {

						Bullet leftB = new Bullet(gp, this, this.x - 5, this.y + 2, 0);
						Bullet rightB = new Bullet(gp, this, this.x + 10, this.y + 2, 1);
						
						gp.bulletThread.addBullet(leftB);
						gp.bulletThread.addBullet(rightB);
						fireCount = 0;
					
					}
					
				} else {
					
					continue;
					
				}
							
			} catch (InterruptedException e) {
				
				isAlive = false;

			}
		}
	}
	
	public void addToFriends(Panel gp) {
		
		gp.friends.add(this);
		
	}	
}