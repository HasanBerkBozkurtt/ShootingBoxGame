package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Panel;

public class Enemy extends GameObject{

	private int fireCount = 0;
	
	public Enemy(Panel gp) {
		
		super(gp);
		
		create();
		
		addToEnemies(gp);
		
		this.start();
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.RED);
		
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
	
	public void addToEnemies(Panel gp) {
		
		gp.enemies.add(this);
		
	}	
}