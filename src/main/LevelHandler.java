package main;

import gameObjects.Enemy;
import gameObjects.Friend;

public class LevelHandler {

    public static void loadLevel(Panel gp, int currentLevel) {

        if (gp.bulletThread != null) {
            gp.bulletThread.clearAllBullets();
        }

        if (gp.player != null) {
            gp.player.isAlive = true;
            gp.player.setToCenter();

            if (!gp.collidables.contains(gp.player)) {
                gp.collidables.add(gp.player);
            }
        }

        for (main.Collidable c : gp.collidables) {
            if (c instanceof gameObjects.GameObject && c != gp.player) {
                ((gameObjects.GameObject) c).kill();
            }
        }
        gp.collidables.clear();
        gp.friends.clear();
        gp.enemies.clear();

        gp.collidables.add(gp.player);

        int targetFriendCount = currentLevel;
        int targetEnemyCount = currentLevel * 2;

        for (int i = 0; i < targetFriendCount; i++) {
            new Friend(gp);
        }
        
        for (int i = 0; i < targetEnemyCount; i++) {
            new Enemy(gp);
        }
    }
}