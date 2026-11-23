package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import gameObjects.Bullet;
import gameObjects.Enemy;
import gameObjects.Friend;
import gameObjects.GameObject;
import gameObjects.Player;

public class Panel extends JPanel implements Runnable {

	public final int SCREEN_WIDTH = 500;
	public final int SCREEN_HEIGHT = 500;
	
	public final int FPS = 60;
	
	public int currentLevel = 1;
	
	public MainFrame frame;
	Keyboard key;
	Thread mainThread; 
	//public ArrayList<Collidable> collidables = new ArrayList<>();
	public CopyOnWriteArrayList<Collidable> collidables = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Friend> friends = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();

	public BulletThread bulletThread;
	public Player player;
	public MouseListener mouse;
	
	public Panel(MainFrame frame) {
		
		this.frame = frame;
		this.key = new Keyboard(frame, this);
		this.player = new Player(this, key);
		this.bulletThread = new BulletThread(this);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		mouse = new MouseListener(this);
		this.addMouseListener(mouse);

	}

	public void startMainThread() {
		
		if(mainThread == null) {
			
			mainThread = new Thread(this);
			mainThread.start();	
		
		}
		
		if(bulletThread != null && !bulletThread.isAlive()) {
			bulletThread.start();
		}
		
	}
	
	@Override
	public void run() {
	
		double deger = 1000000000 / FPS;
		double delta = 0;
		double son = System.nanoTime();
		long simdi;
		
		while(mainThread != null) {
			
			simdi = System.nanoTime();
			delta += (simdi - son) / deger;			
			son = simdi;
			
			if(delta >= 1) {

				if (frame.state == frame.playState) {
					updateGame();
					repaint();
				}
				delta--;
			}
		}
	}
	
	public void updateGame() {

		if (frame.state == frame.playState) {
				
			checkCollision();
			
			checkBulletCollision();
			
			if (player == null || !player.isAlive) {
				
				currentLevel = 1;
				
				LevelHandler.loadLevel(this, currentLevel);
				return;
				
			}
			
			if (enemies.isEmpty()) {
			
				currentLevel++;
				
				LevelHandler.loadLevel(this, currentLevel); 
			
			}
			
		}	
	}
	
		public void checkBulletCollision() {

				for (Bullet b : bulletThread.bullets) {

					if (player != null && player.isAlive && b.owner != player) {

						if (b.x >= player.x && b.x <= player.x + 10 && b.y >= player.y && b.y <= player.y + 10) {

							if (b.owner instanceof Friend) {
								continue;
							}

							if (b.owner instanceof Enemy) {
								player.kill();
								collidables.remove(player);
								bulletThread.bullets.remove(b);
							}
						}
					}

					for (Collidable c : collidables) {
						if (c instanceof GameObject && c != player) {
							GameObject target = (GameObject) c;
							
							if (target.isAlive && b.owner != target) {

								if (b.x >= target.x && b.x <= target.x + 10 && b.y >= target.y && b.y <= target.y + 10) {

									if ((b.owner instanceof Friend && target instanceof Friend) || 
										(b.owner instanceof Enemy && target instanceof Enemy) ||
										(b.owner instanceof Player && target instanceof Friend)) {
										
										continue;
									}

									target.kill();
									collidables.remove(target);
									bulletThread.bullets.remove(b);
									
									if (target instanceof Enemy) 
										enemies.remove(target);
									if (target instanceof Friend) 
										friends.remove(target);

								}
							}
						}
					}
				}
			}
	
	@Override
	public void paintComponent(Graphics g) {
			
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		if (player != null && player.isAlive)
			player.draw(g2);
			
		for (Collidable obj : collidables) {
				
			if (obj instanceof GameObject && obj != player) {
				
				GameObject go = (GameObject) obj;
				if (go.isAlive) {
					go.draw(g2);
				}
			}
		}
		
		if (bulletThread != null) {
			for (Bullet b : bulletThread.bullets) {
				b.draw(g2);
			}
		}
		
	}
	
	public void checkCollision() {
		
		for (int i = 0; i < collidables.size(); i++) {
			for (int j = i + 1; j < collidables.size(); j++) {
				GameObject object1 = (GameObject) collidables.get(i);
				GameObject object2 = (GameObject) collidables.get(j);
											
				if (((object1.x == object2.x) && (object1.y == object2.y)) && control(object1, object2)) {

					object1.kill();
					object2.kill();
					collidables.remove(object1);
					collidables.remove(object2);
					
					if (object1 instanceof Enemy) 
						enemies.remove(object1);
					if (object2 instanceof Enemy) 
						enemies.remove(object2);
					if (object1 instanceof Friend) 
						friends.remove(object1);
					if (object2 instanceof Friend) 
						friends.remove(object2);
					
				}
			}
		}
	}
	
	public boolean control(GameObject obj1, GameObject obj2) {
		
		if ((obj1 instanceof Friend && obj2 instanceof Enemy) || (obj2 instanceof Friend && obj1 instanceof Enemy) || (obj1 instanceof Player && obj2 instanceof Enemy) || (obj2 instanceof Player && obj1 instanceof Enemy))
			return true;
		else
			return false;
	}
	
}