package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gameObjects.Bullet;

public class MouseListener extends MouseAdapter {

	Panel gp;
	
	public MouseListener(Panel gp) {
		this.gp = gp;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1 && gp.frame.state == gp.frame.playState) {
			if (gp.player != null && gp.player.isAlive) {
				
				Bullet leftBullet = new Bullet(gp, gp.player, gp.player.x - 5, gp.player.y + 2, 0);
				
				Bullet rightBullet = new Bullet(gp, gp.player, gp.player.x + 10, gp.player.y + 2, 1);
				
				gp.bulletThread.addBullet(leftBullet);
				gp.bulletThread.addBullet(rightBullet);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gp.requestFocusInWindow();
	}
}