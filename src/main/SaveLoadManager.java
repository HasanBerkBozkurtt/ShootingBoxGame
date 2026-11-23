package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import gameObjects.Friend;
import gameObjects.Enemy;
import gameObjects.GameObject;
import gameObjects.Bullet;
import gameObjects.Player;

public class SaveLoadManager {

	public static void saveGame(Panel gp) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("savegame.txt"))) {

			writer.write("LEVEL," + gp.currentLevel + "\n");

			if (gp.player != null && gp.player.isAlive) {
				writer.write("PLAYER," + gp.player.x + "," + gp.player.y + "\n");
			}

			for (Collidable c : gp.collidables) {
				if (c instanceof Friend) {
					Friend f = (Friend) c;
					if (f.isAlive) writer.write("FRIEND," + f.x + "," + f.y + "\n");
				} else if (c instanceof Enemy) {
					Enemy e = (Enemy) c;
					if (e.isAlive) writer.write("ENEMY," + e.x + "," + e.y + "\n");
				}
			}

			if (gp.bulletThread != null) {
				for (Bullet b : gp.bulletThread.bullets) {
					String ownerType = "PLAYER";
					if (b.getOwner() instanceof Friend) 
						ownerType = "FRIEND";
					if (b.getOwner() instanceof Enemy) 
						ownerType = "ENEMY";

					writer.write("BULLET," + ownerType + "," + b.x + "," + b.y + "," + b.direction + "\n");
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadGame(Panel gp) {
		try (BufferedReader reader = new BufferedReader(new FileReader("savegame.txt"))) {
			String line;

			for (Collidable c : gp.collidables) {
				if (c instanceof GameObject) {
					((GameObject) c).kill();
				}
			}
			gp.collidables.clear();
			gp.friends.clear();
			gp.enemies.clear();
			gp.bulletThread.clearAllBullets();

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				String type = data[0];
				
				if (type.equals("LEVEL")) {
					gp.currentLevel = Integer.parseInt(data[1]);
				} 
				else if (type.equals("PLAYER")) {
					gp.player.x = Integer.parseInt(data[1]);
					gp.player.y = Integer.parseInt(data[2]);
					gp.player.isAlive = true;
					gp.collidables.add(gp.player);
				} 
				else if (type.equals("FRIEND")) {
					Friend f = new Friend(gp);
					f.x = Integer.parseInt(data[1]);
					f.y = Integer.parseInt(data[2]);
				} 
				else if (type.equals("ENEMY")) {
					Enemy e = new Enemy(gp);
					e.x = Integer.parseInt(data[1]);
					e.y = Integer.parseInt(data[2]);
				} 
				else if (type.equals("BULLET")) {
					String ownerType = data[1];
					int bx = Integer.parseInt(data[2]);
					int by = Integer.parseInt(data[3]);
					int bDir = Integer.parseInt(data[4]);

					GameObject bulletOwner = gp.player;
					if (ownerType.equals("FRIEND") && !gp.friends.isEmpty()) {
						bulletOwner = gp.friends.get(0);
					} else if (ownerType.equals("ENEMY") && !gp.enemies.isEmpty()) {
						bulletOwner = gp.enemies.get(0);
					}
					
					Bullet b = new Bullet(gp, bulletOwner, bx, by, bDir);
					gp.bulletThread.addBullet(b);
				}
			}
		} catch (IOException e) {
			System.out.println("Kayıt dosyası bulunamadı.");
		}
	}
}