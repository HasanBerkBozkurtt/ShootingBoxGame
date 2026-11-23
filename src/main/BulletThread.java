package main;

import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.Bullet;

public class BulletThread extends Thread{

	private Panel gp;
	public CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
	private boolean isRunning = true;
	
	public BulletThread(Panel gp) {
		this.gp = gp;
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		
		while (isRunning) {

			if (gp.frame.state == gp.frame.playState) {
				
				long now = System.nanoTime();
				double deltaTime = (now - lastTime) / 1000000000.0;
				lastTime = now;

				for (Bullet b : bullets) {
					b.move(deltaTime);

					if (b.x < 0 || b.x > gp.SCREEN_WIDTH) {
						bullets.remove(b);
					}
				}
			} else {
				lastTime = System.nanoTime();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				isRunning = false;
			}
		}
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void clearAllBullets() {
		bullets.clear();
	}
	
	public void shutdown() {
		this.isRunning = false;
		this.interrupt();
	}
}